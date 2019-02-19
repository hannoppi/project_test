package action.member.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import dto.MemberVO;

public class MemberChangePasswordView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		String id = request.getParameter("id");
		System.out.println("[MemberChangePasswordView.java] id: " + id);
		
		String quiz = request.getParameter("quiz");
		System.out.println("[MemberChangePasswordView.java] quiz: " + quiz);
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = new MemberVO();
		
		mVo = mDao.validation(id, quiz);
		System.out.println("[MemberChangePasswordView.java] mVo: " + mVo);
		
		if (mVo != null) {
			System.out.println("[MemberChangePasswordView.java] 패스워드 변경 페이지로 이동합니다.");
			
			request.setAttribute("user", mVo);
			
			url = "/admin/html/member/find/change.jsp";
		} else {
			System.out.println("[MemberChangePasswordView.java] 패스워드 찾기에 실패하였습니다.");
			
			url = "/admin/find/password.do";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}