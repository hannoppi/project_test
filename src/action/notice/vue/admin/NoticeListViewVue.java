package action.notice.vue.admin;

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
import dao.NoticeDAO;
import dto.NoticeVO;

public class NoticeListViewVue implements Action {
	
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("utf-8");
		
		NoticeDAO nDao = NoticeDAO.getInstance();
		List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
		
		int currentPage = 1; // 최초 시작 페이지입니다.
		int limit = 10; // 화면에 노출할 게시물 개수입니다.
		int listCount = -1; // 게시물의 전체 개수를 추출합니다.
		
		int maxPage = -1; // 전체 페이지 개수를 설정합니다.
		int startPage = -1; // 현재 페이지에 노출할 시작 페이지 개수입니다. (1, 11, 21)
		int endPage = -1; // 현재 페이지에 노출할 마지막 페이지 개수입니다.
		int viewPage = -1; // 현재 페이지에 노출할 마지막 페이지 개수입니다. (10, 20, 30) 10 페이지씩
		
		JSONObject result= new JSONObject();
		JSONArray arr = new JSONArray();
		// JSONObject list = new JSONObject();
		JSONObject paging = new JSONObject();
		
		SimpleDateFormat regdate = null;
		
		String keyword = request.getParameter("keyword");
		System.out.println("[NoticeListViewVue.java] keyword: " + keyword);
		
		String option = request.getParameter("option");
		System.out.println("[NoticeListViewVue.java] option: " + option);
		
		System.out.println("[NoticeListViewVue.java] request.getParameter(\"page\"): " + request.getParameter("page"));
		
		if (request.getParameter("page") != null) {
			if (Integer.parseInt(request.getParameter("page")) != 0) currentPage = Integer.parseInt(request.getParameter("page")); // 현재 페이지를 설정합니다.
		}
		
		System.out.println("[NoticeListViewVue.java] currentPage : " + currentPage);
		
		listCount = nDao.getListCount(keyword, option); // 게시물의 전체 개수를 추출합니다.
		System.out.println("[NoticeListViewVue.java] listCount : " + listCount);
		
		noticeList = nDao.getNoticeList(currentPage, limit, keyword, option); // 목록을 추출합니다.
		System.out.println("[NoticeListViewVue.java] noticeList: " + noticeList);
		
		regdate = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		for (int i = 0; i < noticeList.size(); i++) {
			JSONObject list = new JSONObject();
			
			list.put("number", noticeList.get(i).getNumber());
			list.put("category", noticeList.get(i).getCategory());
			list.put("subject", noticeList.get(i).getSubject());
			list.put("name", noticeList.get(i).getName());
			list.put("content", noticeList.get(i).getContent());
			list.put("address", noticeList.get(i).getAddress());
			list.put("count", noticeList.get(i).getCount());
			list.put("reply_reference", noticeList.get(i).getReplyReference());
			list.put("reply_depth", noticeList.get(i).getReplyDepth());
			list.put("reply_sequence", noticeList.get(i).getReplySequence());
			list.put("regdate", regdate.format(noticeList.get(i).getRegdate()));
			
			arr.add(i, list);
		}
		
		System.out.println("[NoticeListViewVue.java] array: " + arr);
		
		maxPage = (int) ((double) listCount / limit + 0.95);
		System.out.println("[NoticeListViewVue.java] maxPage: " + maxPage);
		
		startPage = (((int) ((double) currentPage / 10 + 0.9)) - 1) * 10 + 1;
		System.out.println("[NoticeListViewVue.java] startPage: " + startPage);
		
		viewPage = startPage + 10 - 1; // 현재 페이지에 노출할 마지막 페이지 개수입니다. (10, 20, 30) 10 페이지씩
		System.out.println("[NoticeListViewVue.java] viewPage: " + viewPage);
		
		if (viewPage > maxPage) endPage = maxPage;
		System.out.println("[NoticeListViewVue.java] endPage: " + endPage);
		
		paging.put("max", maxPage);
		paging.put("start", startPage);
		paging.put("end", endPage);
		paging.put("total", listCount);
		
		result.put("list", arr);
		result.put("paging", paging);
		
		System.out.println("result: " + result);
		
		response.getWriter().write(result.toString());
		
		System.out.println("[NoticeListViewVue.java] 게시물의 목록을 추출하였습니다.");
		
		return null;
	}
}
