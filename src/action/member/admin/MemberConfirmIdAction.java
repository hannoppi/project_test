package action.member.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;

public class MemberConfirmIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "/admin/html/member/confirmId.jsp";
		System.out.println("[MemberConfirmIdAction.java] url: " + url);
		
		String id = request.getParameter("id");
		System.out.println("[MemberConfirmIdAction.java] id: " + id);
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		int result = mDao.confirmId(id);
		
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		
		System.out.println("[MemberConfirmIdAction.java] 성공적으로 처리되었습니다.");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}