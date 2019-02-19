package action.file.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.FileDAO;
import dto.FileVO;

public class FileListView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		FileDAO fDao = FileDAO.getInstance();
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		int currentPage = 1; // 최초 시작 페이지입니다.
		int limit = 10; // 화면에 노출할 게시물 개수입니다.
		
		String keyword = request.getParameter("keyword");
		System.out.println("[FileListView.java] keyword: " + keyword);
		
		String option = request.getParameter("option");
		System.out.println("[FileListView.java] option: " + option);
		
		System.out.println("[FileListView.java] request.getParameter(\"page\"): " + request.getParameter("page"));
		
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page")); // 현재 페이지를 설정합니다.
			System.out.println("[FileListView.java] currentPage : " + currentPage);
		}
		
		int listCount = fDao.getListCount(keyword, option); // 게시물의 전체 개수를 추출합니다.
		System.out.println("[FileListView.java] listCount : " + listCount);
		
		fileList = fDao.getFileList(currentPage, limit, keyword, option); // 목록을 추출합니다.
		
		// 전체 페이지 개수합니다.
		int maxPage = (int) ((double) listCount / limit + 0.95);
		
		// 현재 페이지에 노출할 시작 페이지 개수입니다. (1, 11, 21)
		int startPage = (((int) ((double) currentPage / 10 + 0.9)) - 1) * 10 + 1;
		
		// 현재 페이지에 노출할 마지막 페이지 개수입니다. (10, 20, 30) 10 페이지씩
		int endPage = startPage + 10 - 1;
		System.out.println("[FileListView.java] endPage: " + endPage);
		
		if (endPage > maxPage) endPage = maxPage;
		System.out.println("[FileListView.java] endPage: " + endPage);
		
		if (fileList != null) {
			System.out.println("[FileListView.java] 게시물의 목록을 추출하는데 성공하였습니다.");
			
			request.setAttribute("fileList", fileList);
			request.setAttribute("listCount", listCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			url = "/admin/html/file/list.jsp";
		} else {
			System.out.println("[FileListView.java] 게시물의 목록을 추출하는데 실패하였습니다.");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}