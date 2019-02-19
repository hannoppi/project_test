package action.board.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;
import dto.BoardVO;

public class _BoardReplyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url ="";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[BoardReplyView.java] number : " + number);
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = new BoardVO();
		
		bVo = bDao.getBoardView(number);
		
		if (bVo != null) {
			System.out.println("[BoardReplyView.java] 게시물을 호출합니다.");
			
			request.setAttribute("smarteditor", true);
			
			request.setAttribute("BoardReplyView", bVo);
			
			url = "/admin/html/board/reply.jsp";
		} else {
			System.out.println("[BoardReplyView.java] 게시물 호출에 실패하였습니다.");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}