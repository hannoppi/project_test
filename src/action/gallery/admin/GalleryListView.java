package action.gallery.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.GalleryDAO;
import dto.GalleryVO;

public class GalleryListView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		GalleryDAO gDao = GalleryDAO.getInstance();
		List<GalleryVO> galleryList = new ArrayList<GalleryVO>();
		
		int currentPage = 1; // 최초 시작 페이지입니다.
		int limit = 12; // 화면에 노출할 게시물 개수입니다.
		
		String keyword = (request.getParameter("keyword") != null) ? request.getParameter("keyword") : "";
		System.out.println("[GalleryListView.java] keyword: " + keyword);
		
		String option = (request.getParameter("option") != null) ? request.getParameter("option") : "";
		System.out.println("[GalleryListView.java] option: " + option);
		
		System.out.println("[GalleryListView.java] request.getParameter(\"page\"): " + request.getParameter("page"));
		if (request.getParameter("page") != null) currentPage = Integer.parseInt(request.getParameter("page")); // 현재 페이지를 설정합니다.
		System.out.println("[GalleryListView.java] currentPage : " + currentPage);
		
		int listCount = gDao.getListCount(keyword, option); // 게시물의 전체 개수를 추출합니다.
		System.out.println("[GalleryListView.java] listCount : " + listCount);
		
		galleryList = gDao.getGalleryList(currentPage, limit, keyword, option); // 목록을 추출합니다.
		System.out.println("[GalleryListView.java] galleryList: " + galleryList);
		
		// 전체 페이지 개수합니다.
		int maxPage = (int) ((double) listCount / limit + 0.95);
		
		// 현재 페이지에 노출할 시작 페이지 개수입니다. (1, 11, 21)
		int startPage = (((int) ((double) currentPage / 10 + 0.9)) - 1) * 10 + 1;
		
		// 현재 페이지에 노출할 마지막 페이지 개수입니다. (10, 20, 30) 10 페이지씩
		int endPage = startPage + 10 - 1;
		System.out.println("[GalleryListView.java] endPage: " + endPage);
		
		if (endPage > maxPage) endPage = maxPage;
		System.out.println("[GalleryListView.java] endPage: " + endPage);
		
		if (galleryList != null) {
			System.out.println("[GalleryListView.java] 게시물의 목록을 추출하는데 성공하였습니다.");
			
			request.setAttribute("galleryList", galleryList);
			request.setAttribute("listCount", listCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			url = "/admin/html/gallery/list.jsp";
		} else {
			System.out.println("[GalleryListView.java] 게시물의 목록을 추출하는데 실패하였습니다.");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}
