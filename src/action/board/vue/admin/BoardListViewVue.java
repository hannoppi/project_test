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
import dto.BoardVO;

public class BoardListViewVue implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("utf-8");
		
		BoardDAO bDao = BoardDAO.getInstance();
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		int currentPage = 1; // 최초 시작 페이지입니다.
		int limit = 10; // 화면에 노출할 게시물 개수입니다.
		
		String keyword = request.getParameter("keyword");
		System.out.println("[BoardListViewVue.java] keyword: " + keyword);
		
		String option = request.getParameter("option");
		System.out.println("[BoardListViewVue.java] option: " + option);
		
		System.out.println("[BoardListViewVue.java] request.getParameter(\"page\"): " + request.getParameter("page"));
		
		if (request.getParameter("page") != null) {
			if (Integer.parseInt(request.getParameter("page")) != 0) currentPage = Integer.parseInt(request.getParameter("page")); // 현재 페이지를 설정합니다.
		}
		
		System.out.println("[BoardListViewVue.java] currentPage : " + currentPage);
		
		int listCount = bDao.getListCount(keyword, option); // 게시물의 전체 개수를 추출합니다.
		System.out.println("[BoardListViewVue.java] listCount : " + listCount);
		
		boardList = bDao.getBoardList(currentPage, limit, keyword, option); // 목록을 추출합니다.
		System.out.println("[BoardListViewVue.java] boardList: " + boardList);
		
		JSONObject obj= new JSONObject();
		JSONArray jArray = new JSONArray();
		
		SimpleDateFormat regdate = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		for (int i = 0; i < boardList.size(); i++) {
			JSONObject data = new JSONObject();
			System.out.println("[BoardListViewVue.java] boardList.get(i).getCategory(): " + boardList.get(i).getCategory());
			
			data.put("number", boardList.get(i).getNumber());
			data.put("category", boardList.get(i).getCategory());
			data.put("subject", boardList.get(i).getSubject());
			data.put("name", boardList.get(i).getName());
			data.put("content", boardList.get(i).getContent());
			data.put("address", boardList.get(i).getAddress());
			data.put("count", boardList.get(i).getCount());
			data.put("reply_reference", boardList.get(i).getReplyReference());
			data.put("reply_depth", boardList.get(i).getReplyDepth());
			data.put("reply_sequence", boardList.get(i).getReplySequence());
			data.put("regdate", regdate.format(boardList.get(i).getRegdate()));
			
			jArray.add(i, data);
		}
		
		System.out.println("[BoardListViewVue.java] jArray: " + jArray);
		obj.put("result", jArray);
		System.out.println("[BoardListViewVue.java] obj: " + obj);
		
		// 전체 페이지 개수를 설정합니다.
		int maxPage = (int) ((double) listCount / limit + 0.95);
		
		// 현재 페이지에 노출할 시작 페이지 개수입니다. (1, 11, 21)
		int startPage = (((int) ((double) currentPage / 10 + 0.9)) - 1) * 10 + 1;
		
		// 현재 페이지에 노출할 마지막 페이지 개수입니다. (10, 20, 30) 10 페이지씩
		int endPage = startPage + 10 - 1;
		System.out.println("[BoardListViewVue.java] endPage: " + endPage);
		
		if (endPage > maxPage) endPage = maxPage;
		System.out.println("[BoardListViewVue.java] endPage: " + endPage);
		
		System.out.println("[BoardListViewVue.java] 게시물의 목록을 추출하였습니다.");
		
		response.getWriter().write(obj.toString());
		
		return null;
	}
}
