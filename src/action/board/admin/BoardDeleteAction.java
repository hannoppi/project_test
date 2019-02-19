package action.board.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;
import dto.MemberVO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[BoardDeleteAction.java] number : " + number);
		
		HttpSession session = request.getSession();
		
		MemberVO user = (MemberVO) session.getAttribute("user");
		System.out.println("[BoardDeleteAction.java] user : " + user);
		
		String id = user.getId();
		System.out.println("[BoardDeleteAction.java] id : " + id);
		
		BoardDAO bDao = BoardDAO.getInstance();
		int result = bDao.boardDelete(number, id);
		
		System.out.println("[BoardDeleteAction.java] result: " + result);
		
		if (result > 0) {
			System.out.println("[BoardDeleteAction.java] 게시물을 삭제하였습니다.");
			
			url = "/admin/board/list.do";
		} else {
			System.out.println("[BoardDeleteAction.java] 게시물을 삭제하는데 실패하였습니다.");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(url);
		
		return forward;
	}
}
