package action.member.admin;

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
		// String savePath = "/admin/upload/member/profile";
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
		
		if (result == 1) {
			System.out.println("[MemberLoginActionVue.java] 로그인이 정상적으로 처리되었습니다.");
			mVo = mDao.inquiryVue(id, password);
			
			Algorithm algorithm = Algorithm.HMAC256("secret");
			System.out.println("[MemberLoginActionVue.java] algorithm: " + algorithm);
			
			String token = JWT.create().withClaim("id", mVo.get(0).getId()).withClaim("name", mVo.get(0).getName()).withClaim("level", mVo.get(0).getLevel()).withIssuer("auth0").sign(algorithm);
			System.out.println("[MemberLoginActionVue.java] token: " + token);
			
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
			DecodedJWT jwt = verifier.verify(token);
			
			System.out.println("[MemberLoginActionVue.java] id: " + jwt.getClaim("id").asString() + " / password : " + jwt.getClaim("password").asString());
			// String token = "WinOS";
			
			JSONObject obj= new JSONObject();
			JSONArray jArray = new JSONArray();
			JSONObject data = new JSONObject();
			
			for (int i = 0; i < mVo.size(); i++) {
				data.put("id", mVo.get(i).getId());
				data.put("name", mVo.get(i).getName());
				data.put("accessToken", token);
				
				jArray.add(i, data);
			}
			
			System.out.println("jArray: " + jArray);
			
			obj.put("result", jArray);
			
			System.out.println("obj: " + obj);
			
			response.getWriter().write(obj.toString());
		} else if(result == 0) {
			System.out.println("[MemberLoginActionVue.java] 패스워드가 일치하지 않습니다.");
		} else {
			System.out.println("[MemberLoginActionVue.java] 아이디가 존재하지 않습니다.");
		}
		
		return null;
	}
}