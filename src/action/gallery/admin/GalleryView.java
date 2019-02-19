package action.gallery.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.GalleryDAO;
import dto.GalleryVO;

public class GalleryView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url ="";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[GalleryView.java] number : " + number);
		
		GalleryDAO gDao = GalleryDAO.getInstance();
		GalleryVO gVo = new GalleryVO();
		
		gDao.countUpdate(number);
		gVo = gDao.getGalleryView(number);
		
		if (gVo != null) {
			System.out.println("[GalleryView.java] 게시물을 호출합니다.");
			
			request.setAttribute("galleryView", gVo);
			
			url = "/admin/html/gallery/view.jsp";
		} else {
			System.out.println("[GalleryView.java] 게시물 호출에 실패하였습니다.");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}
