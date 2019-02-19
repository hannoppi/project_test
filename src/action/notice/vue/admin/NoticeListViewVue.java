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
	
	/*
	public String getJSON(List<NoticeVO> cVo, int count, int current, int max, int start, int end) {
		List<NoticeVO> list = cVo;
		
		StringBuffer result = new StringBuffer();
		result.append("{\"paging\": {");
		result.append("\"listCount\": \"" + count + "\",");
		result.append("\"currentPage\": \"" + current + "\",");
		result.append("\"maxPage\": \"" + max + "\",");
		result.append("\"startPage\": \"" + start + "\",");
		result.append("\"endPage\": \"" + end + "\"");
		result.append("},");
		
		result.append("\"result\": [");
		
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) result.append(",");
			
			// System.out.println("[BoardListViewVue.java] (getJSON) list.get(i).getRegdate(): " + list.get(i).getRegdate());
			
			SimpleDateFormat regdate = new SimpleDateFormat("yyyy년 MM월 dd일");
			// System.out.println("[BoardListViewVue.java] (getJSON) regdate.format(list.get(i).getRegdate()): " + regdate.format(list.get(i).getRegdate()));
			
			result.append("{\"number\": \"" + list.get(i).getNumber() + "\",");
			result.append("\"category\": \"" + list.get(i).getCategory() + "\",");
			result.append("\"subject\": \"" + list.get(i).getSubject() + "\",");
			result.append("\"content\": \"" + list.get(i).getContent().replace("\"", "'") + "\",");
			result.append("\"address\": \"" + list.get(i).getAddress() + "\",");
			result.append("\"count\": \"" + list.get(i).getCount() + "\",");
			result.append("\"reply_reference\": \"" + list.get(i).getReplyReference() + "\",");
			result.append("\"reply_depth\": \"" + list.get(i).getReplyDepth() + "\",");
			result.append("\"reply_sequence\": \"" + list.get(i).getReplySequence() + "\",");
			result.append("\"regdate\": \"" + regdate.format(list.get(i).getRegdate()) + "\"}");
		}
		
		result.append("]}");
		
		System.out.println("[BoardListViewVue.java] (getJSON) result: " + result);
		return result.toString();
	}
	*/

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		NoticeDAO nDao = NoticeDAO.getInstance();
		List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
		
		int currentPage = 1; // 최초 시작 페이지입니다.
		int limit = 10; // 화면에 노출할 게시물 개수입니다.
		
		String keyword = request.getParameter("keyword");
		System.out.println("[NoticeListView.java] keyword: " + keyword);
		
		String option = request.getParameter("option");
		System.out.println("[NoticeListView.java] option: " + option);
		
		System.out.println("[NoticeListView.java] request.getParameter(\"page\"): " + request.getParameter("page"));

		System.out.println("[NoticeListView.java] request.getParameter(\"page\"): " + request.getParameter("page"));
		
		if (request.getParameter("page") != null) {
			if (Integer.parseInt(request.getParameter("page")) != 0) currentPage = Integer.parseInt(request.getParameter("page")); // 현재 페이지를 설정합니다.
		}
		
		System.out.println("[NoticeListView.java] currentPage : " + currentPage);
		
		int listCount = nDao.getListCount(keyword, option); // 게시물의 전체 개수를 추출합니다.
		System.out.println("[NoticeListView.java] listCount : " + listCount);
		
		noticeList = nDao.getNoticeList(currentPage, limit, keyword, option); // 목록을 추출합니다.
		System.out.println("[NoticeListView.java]: " + noticeList);
		
		JSONObject obj= new JSONObject();
		JSONArray jArray = new JSONArray();
		
		SimpleDateFormat regdate = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		for (int i = 0; i < noticeList.size(); i++) {
			JSONObject data = new JSONObject();
			System.out.println("[NoticeListView.java] noticeList.get(i).getCategory(): " + noticeList.get(i).getCategory());
			
			data.put("number", noticeList.get(i).getNumber());
			data.put("category", noticeList.get(i).getCategory());
			data.put("subject", noticeList.get(i).getSubject());
			data.put("name", noticeList.get(i).getName());
			data.put("content", noticeList.get(i).getContent());
			data.put("address", noticeList.get(i).getAddress());
			data.put("count", noticeList.get(i).getCount());
			data.put("reply_reference", noticeList.get(i).getReplyReference());
			data.put("reply_depth", noticeList.get(i).getReplyDepth());
			data.put("reply_sequence", noticeList.get(i).getReplySequence());
			data.put("regdate", regdate.format(noticeList.get(i).getRegdate()));
			
			jArray.add(i, data);
		}
		
		System.out.println("[NoticeListView.java] jArray: " + jArray);
		obj.put("result", jArray);
		System.out.println("[NoticeListView.java] obj: " + obj);
		
		// 전체 페이지 개수를 설정합니다.
		int maxPage = (int) ((double) listCount / limit + 0.95);
		
		// 현재 페이지에 노출할 시작 페이지 개수입니다. (1, 11, 21)
		int startPage = (((int) ((double) currentPage / 10 + 0.9)) - 1) * 10 + 1;
		
		// 현재 페이지에 노출할 마지막 페이지 개수입니다. (10, 20, 30) 10 페이지씩
		int endPage = startPage + 10 - 1;
		System.out.println("[NoticeListView.java] endPage: " + endPage);
		
		if (endPage > maxPage) endPage = maxPage;
		System.out.println("[NoticeListView.java] endPage: " + endPage);
		
		/*
		if (noticeList != null) {
			System.out.println("[NoticeListView.java] 게시물의 목록을 추출하였습니다.");
			
			response.getWriter().write(getJSON(noticeList, listCount, currentPage, maxPage, startPage, endPage));
		} else {
			System.out.println("[NoticeListView.java] 게시물의 목록을 추출하는데 실패하였습니다.");
		}
		*/
		
		System.out.println("[NoticeListView.java] 게시물의 목록을 추출하였습니다.");
		
		response.getWriter().write(obj.toString());
		
		return null;
	}
}