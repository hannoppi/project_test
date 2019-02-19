package action.member.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import dto.MemberVO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		String id = request.getParameter("id");
		System.out.println("[MemberLoginAction.java] id : " + id);
		
		String password = request.getParameter("password");
		System.out.println("[MemberLoginAction.java] password : " + password);
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = null;	
		
		int result = mDao.login(id, password);
		System.out.println("[MemberLoginAction.java] result : " + result);
		
		if (result == 1) {
			mVo = mDao.inquiry(id, password);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", mVo);
			System.out.println("[MemberLoginAction.java] user : " + mVo);
			
			url = "/admin/notice/list.do";
		} else if(result == 0) {
			System.out.println("[MemberLoginAction.java] 패스워드가 일치하지 않습니다.");
			
			url = "/admin/login.do";
		} else {
			System.out.println("[MemberLoginAction.java] 아이디가 존재하지 않습니다.");
			
			url = "/admin/login.do";
		}
		
		response.sendRedirect(url);
		
		return null;
	}
}