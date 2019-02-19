package action.board.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.CommentDAO;
import dto.CommentVO;

public class BoardReply implements Action {
	public String getJSON(List<CommentVO> cVo) {
		List<CommentVO> list = cVo;
		
		StringBuffer result = new StringBuffer();
		result.append("{\"result\": [");
		
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) result.append(",");
			
			System.out.println("[BoardReply.java] (getJSON) list.get(i).getRegdate(): " + list.get(i).getRegdate());
			
			SimpleDateFormat regdate = new SimpleDateFormat("yyyy년 MM월 dd일");
			System.out.println("[BoardReply.java] (getJSON) regdate.format(list.get(i).getRegdate()): " + regdate.format(list.get(i).getRegdate()));
			
			result.append("{\"value\": [");
			result.append("{\"board\": \"" + list.get(i).getBoard() + "\",");
			result.append("\"number\": \"" + list.get(i).getNumber() + "\",");
			result.append("\"id\": \"" + list.get(i).getId() + "\",");
			result.append("\"name\": \"" + list.get(i).getName() + "\",");
			result.append("\"content\": \"" + list.get(i).getContent().replace("\"", "'") + "\",");
			result.append("\"reference\": \"" + list.get(i).getReference() + "\",");
			result.append("\"sequence\": \"" + list.get(i).getSequence() + "\",");
			result.append("\"level\": \"" + list.get(i).getLevel() + "\",");
			result.append("\"together\": \"" + list.get(i).getTogether() + "\",");
			result.append("\"regdate\": \"" + regdate.format(list.get(i).getRegdate()) + "\"}");
			result.append("]}");
		}
		
		result.append("]}");
		
		System.out.println("[BoardReply.java] (getJSON) result: " + result);
		return result.toString();
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int board = Integer.parseInt(request.getParameter("board"));
		System.out.println("[BoardComment.java] 게시물 번호(board): " + board);
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[BoardComment.java] 댓글 번호(number): " + number);
		
		String id = request.getParameter("id");
		System.out.println("[BoardComment.java] 아이디(id): " + id);
		
		String name = request.getParameter("name");
		System.out.println("[BoardComment.java] 작성자(name): " + name);
		
		String content =	request.getParameter("content");
		System.out.println("[BoardComment.java] 내용(content): " + content);
		
		int reference = Integer.parseInt(request.getParameter("reference"));
		System.out.println("[BoardComment.java] 댓글 부모(reference): " + reference);
		
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		System.out.println("[BoardComment.java] 댓글 순서(sequence): " + sequence);
		
		int level = Integer.parseInt(request.getParameter("level"));
		System.out.println("[BoardComment.java] 댓글 깊이(level): " + level);
		
		CommentDAO cDao = CommentDAO.getInstance();
		CommentVO cVo = new CommentVO();
		
		cVo.setBoard(board);
		cVo.setNumber(number);
		cVo.setId(id);
		cVo.setName(name);
		cVo.setContent(content);
		cVo.setReference(reference);
		cVo.setSequence(sequence);
		cVo.setLevel(level);
		
		cDao.reply(cVo);
		
		return null;
	}
}
