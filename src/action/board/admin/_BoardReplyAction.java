package action.board.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;
import dto.BoardVO;

public class _BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[BoardReplyAction.java] number : " + number);
		
		String id = request.getParameter("id");
		System.out.println("[BoardReplyAction.java] id: " + id);
		
		String name = request.getParameter("name");
		System.out.println("[BoardReplyAction.java]  name: " +  name);
		
		String category = request.getParameter("category");
		System.out.println("[BoardReplyAction.java] category: " + category);
		
		String subject = request.getParameter("subject");
		System.out.println("[BoardReplyAction.java] subject: " + subject);
		
		String content = request.getParameter("content");
		System.out.println("[BoardReplyAction.java] content: " + content);
		
		int replyReference = Integer.parseInt(request.getParameter("reply_reference"));
		System.out.println("[BoardReplyAction.java] replyReference: " + replyReference);
		
		int replyDepth = Integer.parseInt(request.getParameter("reply_depth"));
		System.out.println("[BoardReplyAction.java] replyDepth: " + replyDepth);
		
		int replySequence = Integer.parseInt(request.getParameter("reply_sequence"));
		System.out.println("[BoardReplyAction.java] replySequence: " + replySequence);
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = new BoardVO();
		
		bVo.setId(id);
		bVo.setName(name);
		bVo.setCategory(category);
		bVo.setSubject(subject);
		bVo.setContent(content);
		bVo.setReplyReference(replyReference);
		bVo.setReplyDepth(replyDepth);
		bVo.setReplySequence(replySequence);
		
		int result =  bDao.boardReply(bVo);
		System.out.println("[BoardReplyAction.java] result: " + result);
		
		if (result == 1) {
			System.out.println("[BoardReplyAction.java] 댓글을 작성하였습니다.");
			
			request.setAttribute("boardView1", bVo);
			
			url = "/admin/board/list.do";
		} else {
			System.out.println("[BoardReplyAction.java] 댓글 작성에 실패하였습니다.");
			
			url = "/admin/board/list.do";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(url);
		
		return forward;
	}
}