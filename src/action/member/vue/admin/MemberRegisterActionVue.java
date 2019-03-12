package action.member.vue.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import dto.MemberVO;

public class MemberRegisterActionVue implements Action {

	@SuppressWarnings("deprecation")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		try {
			MultipartRequest multi = null;
			
			String realPath = "";
			String savePath = "/admin/upload/temp";
			realPath = request.getRealPath(savePath);
			System.out.println("[MemberRegisterActionVue.java] realPath : " + realPath);
			
			multi = new MultipartRequest(request, realPath, "utf-8");
			
			String id = multi.getParameter("confirmId");
			System.out.println("[MemberRegisterActionVue.java] id: " + id);
			
			String password = multi.getParameter("password");
			System.out.println("[MemberJoinAction.java] password: " + password);
			
			String quiz = multi.getParameter("quiz");
			System.out.println("[MemberRegisterActionVue.java] quiz: " + quiz);
			
			String name = multi.getParameter("name");
			System.out.println("[MemberRegisterActionVue.java] name: " + name);
			
			MemberDAO mDao = MemberDAO.getInstance();
			MemberVO mVo = new MemberVO();
			
			mVo.setId(id);
			mVo.setPassword(password);
			mVo.setQuiz(quiz);
			mVo.setName(name);
			
			Boolean result = mDao.insertVue(mVo);
			System.out.println("[MemberRegisterActionVue.java] result: " + result);
			
			if (result) {
				System.out.println("[MemberRegisterActionVue.java] 회원가입에 성공하였습니다.");
			} else {
				System.out.println("[MemberRegisterActionVue.java] 회원가입에 실패하였습니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
}