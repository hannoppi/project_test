package action.member.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;

public class MemberChangePasswordAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		System.out.println("[MemberChangePasswordAction.java] id: " + id);
		
		String password = request.getParameter("password");
		System.out.println("[MemberChangePasswordAction.java] password: " + password);
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		Boolean result = mDao.changePassword(id, password);
		System.out.println("[MemberChangePasswordAction.java] result: " + result);
		
		return null;
	}
}