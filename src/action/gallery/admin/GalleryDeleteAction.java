package action.gallery.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.GalleryDAO;
import dto.MemberVO;

public class GalleryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[GalleryDeleteAction.java] number : " + number);
		
		HttpSession session = request.getSession();
		
		MemberVO user = (MemberVO) session.getAttribute("user");
		System.out.println("[GalleryDeleteAction.java] user : " + user);
		
		String id = user.getId();
		System.out.println("[GalleryDeleteAction.java] id : " + id);
		
		GalleryDAO gDao = GalleryDAO.getInstance();
		int result = gDao.galleryDelete(number, id);
		
		if (result > 0) {
			System.out.println("[GalleryDeleteAction.java] 게시물을 삭제하였습니다.");
			
			url = "/admin/gallery/list.do";
		} else {
			System.out.println("[GalleryDeleteAction.java] 게시물을 삭제하는데 실패하였습니다.");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(url);
		
		return forward;
	}
}
