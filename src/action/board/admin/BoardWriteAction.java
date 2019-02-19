package action.board.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;
import dto.BoardVO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		String id = request.getParameter("id");
		System.out.println("[BoardWriteAction.java] id : " + id);
		
		String name = request.getParameter("name");
		System.out.println("[BoardWriteAction.java] name : " + name);
		
		String category = request.getParameter("category");
		System.out.println("[BoardWriteAction.java] category: " + category);
		
		String subject = request.getParameter("subject");
		System.out.println("[BoardWriteAction.java] subject: " + subject);
		
		String content = request.getParameter("content");
		System.out.println("[BoardWriteAction.java] content: " + content);
		
		String uri = request.getParameter("uri");
		System.out.println("[BoardWriteAction.java] uri: " + uri);
		
		String thumbnail = ( request.getParameter("thumbnail") == null) ? "" : request.getParameter("thumbnail");
		System.out.println("[BoardWriteAction.java] thumbnail: " + thumbnail);
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = new BoardVO();
		
		bVo.setId(id);
		bVo.setName(name);
		bVo.setCategory(category);
		bVo.setSubject(subject);
		bVo.setContent(content);
		bVo.setUri(uri);
		bVo.setThumbnail(thumbnail);
		
		bVo = bDao.setBoardWirte(bVo);
		System.out.println("bVo : " + bVo);
		
		if (bVo != null) {
			System.out.println("[BoardWriteAction.java] 게시물 등록에 성공하였습니다.");
			
			request.setAttribute("boardView", bVo);
			
			url = "/admin/board/list.do";
		} else {
			System.out.println("[BoardWriteAction.java] 게시물 등록에 실패하였습니다.");
			
			url = "/admin/board/list.do";
		}
		
		response.sendRedirect(url);
		
		return null;
	}
}
