package action.gallery.vue.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.GalleryDAO;

public class GalleryDeleteActionVue implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[GalleryDeleteAction.Vue.java] number : " + number);
		
		String id = request.getParameter("id");
		System.out.println("[GalleryDeleteAction.Vue.java] id : " + id);
		
		GalleryDAO gDao = GalleryDAO.getInstance();
		int result = gDao.galleryDelete(number, id);
		
		if (result > 0) {
			System.out.println("[GalleryDeleteAction.Vue.java] 게시물을 삭제하였습니다.");
		} else {
			System.out.println("[GalleryDeleteAction.Vue.java] 게시물을 삭제하는데 실패하였습니다.");
		}
		
		return null;
	}
}