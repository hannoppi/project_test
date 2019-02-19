package action.file.vue.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.FileDAO;
import dto.FileVO;

public class FileListViewVue implements Action {
	
	public String getJSON(List<FileVO> cVo, int count, int current, int max, int start, int end) {
		List<FileVO> list = cVo;
		
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
			
			result.append("{\"listCount\": \"" + count + "\",");
			result.append("\"currentPage\": \"" + current + "\",");
			result.append("\"maxPage\": \"" + max + "\",");
			result.append("\"startPage\": \"" + start + "\",");
			result.append("\"endPage\": \"" + end + "\",");
			result.append("\"number\": \"" + list.get(i).getNumber() + "\",");
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

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		FileDAO fDao = FileDAO.getInstance();
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		int currentPage = 1; // 최초 시작 페이지
		int limit = 10; // 화면에 노출할 게시물 개수
		
		String keyword = request.getParameter("keyword");
		System.out.println("[FileListView.java] keyword: " + keyword);
		
		String option = request.getParameter("option");
		System.out.println("[FileListView.java] option: " + option);
		
		System.out.println("[FileListView.java] request.getParameter(\"page\"): " + request.getParameter("page"));
		
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page")); // 현재 페이지를 설정
			System.out.println("[FileListView.java] currentPage : " + currentPage);
		}
		
		int listCount = fDao.getListCount(keyword, option); // 게시물의 전체 개수를 추출
		System.out.println("[FileListView.java] listCount : " + listCount);
		
		fileList = fDao.getFileList(currentPage, limit, keyword, option); // 목록을 추출
		
		// 전체 페이지 개수
		int maxPage = (int) ((double) listCount / limit + 0.95);
		
		// 현재 페이지에 노출할 시작 페이지 개수(1, 11, 21)
		int startPage = (((int) ((double) currentPage / 10 + 0.9)) - 1) * 10 + 1;
		
		// 현재 페이지에 노출할 마지막 페이지 수(10, 20, 30) 5 페이지씩
		int endPage = startPage + 10 - 1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		if (fileList != null) {
			System.out.println("[FileListView.java] 게시물의 목록을 추출하는데 성공하였습니다.");
			
			response.getWriter().write(getJSON(fileList, listCount, currentPage, maxPage, startPage, endPage));
		} else {
			System.out.println("[FileListView.java] 게시물의 목록을 추출하는데 실패하였습니다.");
		}
		
		return null;
	}
}