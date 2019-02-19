package action.gallery.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.GalleryDAO;
import dto.GalleryVO;

public class GalleryModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url ="";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[GalleryModifyView.java] number : " + number);
		
		GalleryDAO gDao = GalleryDAO.getInstance();
		GalleryVO gVo = new GalleryVO();

		gVo = gDao.getGalleryView(number);
		System.out.println("[GalleryModifyView.java] gVo: " + gVo);
		
		if (gVo != null) {
			System.out.println("[GalleryModifyView.java] 게시물을 호출합니다.");
			
			request.setAttribute("galleryModify", gVo);
			request.setAttribute("smarteditor", true);
			
			url = "/admin/html/gallery/modify.jsp";
		} else {
			System.out.println("[GalleryModifyView.java] 게시물 호출에 실패하였습니다.");
			
			url = "/admin/html/gallery/list.jsp";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}
