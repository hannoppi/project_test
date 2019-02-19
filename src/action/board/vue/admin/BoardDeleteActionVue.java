package action.board.vue.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;

public class BoardDeleteActionVue implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[BoardDeleteActionVue.java] number : " + number);
		
		String id = request.getParameter("id");
		System.out.println("[BoardDeleteActionVue.java] id : " + id);
		
		BoardDAO bDao = BoardDAO.getInstance();
		int result = bDao.boardDelete(number, id);
		
		System.out.println("[BoardDeleteActionVue.java] result: " + result);
		
		if (result > 0) {
			System.out.println("[BoardDeleteActionVue.java] 게시물을 삭제하는데 성공하였습니다.");
		} else {
			System.out.println("[BoardDeleteActionVue.java] 게시물을 삭제하는데 실패하였습니다.");
		}
		
		return null;
	}
}