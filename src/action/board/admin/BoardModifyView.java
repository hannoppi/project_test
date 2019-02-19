package action.board.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;
import dto.BoardVO;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[BoardModifyView.java] number : " + number);
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = new BoardVO();

		bVo = bDao.getBoardView(number);
		System.out.println("[BoardModifyView.java] bVo: " + bVo);
		
		if (bVo != null) {
			System.out.println("[BoardModifyView.java] 게시물을 호출합니다.");
			
			request.setAttribute("boardModifyView", bVo);
			request.setAttribute("smarteditor", true);
			
			url = "/admin/html/board/modify.jsp";
		} else {
			System.out.println("[BoardModifyView.java] 게시물 호출에 실패하였습니다.");
			
			url = "/admin/html/board/list.jsp";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}
