package action.board.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;
import dto.BoardVO;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[BoardModifyAction.java] number : " + number);
		
		String id = request.getParameter("id");
		System.out.println("[BoardModifyAction.java] id: " + id);
		
		String name = request.getParameter("name");
		System.out.println("[BoardModifyAction.java] name: " + name);
		
		String category = request.getParameter("category");
		System.out.println("[BoardModifyAction.java] category: " + category);
		
		String subject = request.getParameter("subject");
		System.out.println("[BoardModifyAction.java] subject: " + subject);
		
		String content = request.getParameter("content");
		System.out.println("[BoardModifyAction.java] content: " + content);
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = new BoardVO();
		
		bVo.setId(id);
		bVo.setName(name);
		bVo.setCategory(category);
		bVo.setSubject(subject);
		bVo.setContent(content);
		
		bVo = bDao.setBoardModify(number, bVo);
		System.out.println("[BoardModifyAction.java] bVo : " + bVo);
		
		if (bVo != null) {
			System.out.println("[BoardModifyAction.java] 게시물 수정에 성공하였습니다.");
			
			request.setAttribute("boardView1", bVo);
			
			url = "/admin/board/view.do?number=" + number;
		} else {
			System.out.println("[BoardModifyAction.java] 게시물 수정에 실패하였습니다.");
			
			url = "/admin/board/list.do";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(url);
		
		return forward;
	}
}
