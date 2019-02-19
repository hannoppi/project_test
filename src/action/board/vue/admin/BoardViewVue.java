package action.board.vue.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import action.Action;
import action.ActionForward;
import dao.BoardDAO;
import dao.CommentDAO;
import dto.BoardVO;
import dto.CommentVO;

public class BoardViewVue implements Action {
	
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("utf-8");
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[BoardViewVue.java] number : " + number);
		
		BoardDAO bDao = BoardDAO.getInstance();
		List<BoardVO> bVo = new ArrayList<BoardVO>();
		
		bDao.countUpdate(number);
		bVo = bDao.getBoardViewVue(number);
		
		CommentDAO cDao = CommentDAO.getInstance();
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		
		commentList = cDao.select(number);
		System.out.println("[BoardViewVue.java] commentList: " + commentList);
		
		System.out.println("[BoardViewVue.java] commentList.size(): " + commentList.size());
		if (commentList.size() > 0) request.setAttribute("commentList", commentList);
		
		JSONObject obj= new JSONObject();
		JSONArray jArray = new JSONArray();
		
		SimpleDateFormat regdate = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		for (int i = 0; i < bVo.size(); i++) {
			JSONObject data = new JSONObject();
			System.out.println("[BoardViewVue.java] bVo.get(i).getCategory(): " + bVo.get(i).getCategory());
			
			data.put("number", bVo.get(i).getNumber());
			data.put("category", bVo.get(i).getCategory());
			data.put("subject", bVo.get(i).getSubject());
			data.put("name", bVo.get(i).getName());
			data.put("content", bVo.get(i).getContent());
			data.put("address", bVo.get(i).getAddress());
			data.put("count", bVo.get(i).getCount());
			data.put("reply_reference", bVo.get(i).getReplyReference());
			data.put("reply_depth", bVo.get(i).getReplyDepth());
			data.put("reply_sequence", bVo.get(i).getReplySequence());
			data.put("regdate", regdate.format(bVo.get(i).getRegdate()));
			
			jArray.add(i, data);
		}
		
		System.out.println("[BoardViewVue.java] jArray: " + jArray);
		obj.put("result", jArray);
		System.out.println("[BoardViewVue.java] obj: " + obj);
		
		System.out.println("[BoardViewVue.java] 게시물의 목록을 추출하였습니다.");
		
		response.getWriter().write(obj.toString());
		
		return null;
	}
}
