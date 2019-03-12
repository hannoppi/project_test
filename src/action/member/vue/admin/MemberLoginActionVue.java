package action.member.vue.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import dto.MemberVO;

public class MemberLoginActionVue implements Action {
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("utf-8");
		
		MultipartRequest multi = null;
		
		String realPath = "";
		String savePath = "";
		int maxFileSize = 5 * 1024 * 1024;
		
		realPath = request.getRealPath(savePath);
		System.out.println("[MemberLoginActionVue.java] realPath : " + realPath);
		
		multi = new MultipartRequest(request, realPath, maxFileSize, "utf-8", new DefaultFileRenamePolicy());
		
		String id = multi.getParameter("id");
		System.out.println("[MemberLoginActionVue.java] id : "+id);
		
		String password = multi.getParameter("password");
		System.out.println("[MemberLoginActionVue.java] password : "+password);
		
		MemberDAO mDao = MemberDAO.getInstance();
		List<MemberVO> mVo = new ArrayList<MemberVO>();
		
		int result = mDao.login(id, password);
		System.out.println("[MemberLoginActionVue.java] result : " + result);
		
		JSONObject obj= new JSONObject();
		
		if (result == 1) {
			System.out.println("[MemberLoginActionVue.java] 로그인이 정상적으로 처리되었습니다.");
			mVo = mDao.inquiryVue(id, password);
			
			Algorithm algorithm = Algorithm.HMAC256("secret");
			System.out.println("[MemberLoginActionVue.java] algorithm: " + algorithm);
			
			String token = JWT.create().withClaim("id", mVo.get(0).getId()).withClaim("name", mVo.get(0).getName()).withClaim("level", mVo.get(0).getLevel()).withIssuer("auth0").sign(algorithm);
			System.out.println("[MemberLoginActionVue.java] token: " + token);
			
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
			DecodedJWT jwt = verifier.verify(token);
			
			System.out.println("[MemberLoginActionVue.java] jwt.getClaim(\"id\").asString(): " + jwt.getClaim("id").asString());
			
			for (int i = 0; i < mVo.size(); i++) {
				obj.put("id", mVo.get(i).getId());
				obj.put("name", mVo.get(i).getName());
				obj.put("accessToken", token);
			}
		} else {
			String message = "";
			
			if (result > -1) {
				message = "패스워드가 일치하지 않습니다.";
			} else {
				message = "아이디가 존재하지 않습니다.";
			}
			
			System.out.println("[MemberLoginActionVue.java] " + message + " 로그인에 실패하였습니다.");
			
			obj.put("result", result);
		}
		
		System.out.println("obj: " + obj);
		
		response.getWriter().write(obj.toString());
		
		System.out.println("\n");
		
		return null;
	}
}