package action.board.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;
import dao.CommentDAO;
import dto.BoardVO;
import dto.CommentVO;

public class BoardView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[BoardView.java] number : " + number);
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = new BoardVO();
		
		bDao.countUpdate(number);
		bVo = bDao.getBoardView(number);
		
		CommentDAO cDao = CommentDAO.getInstance();
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		
		commentList = cDao.select(number);
		
		if (commentList.size() > 0) request.setAttribute("commentList", commentList);

		if (bVo != null) {
			System.out.println("[BoardView.java] 게시물을 호출합니다.");
			
			request.setAttribute("boardView", bVo);
			
			url = "/admin/html/board/view.jsp";
		} else {
			System.out.println("[BoardView.java] 게시물 호출에 실패하였습니다.");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}
