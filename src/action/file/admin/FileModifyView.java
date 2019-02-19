package action.file.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.FileDAO;
import dto.FileVO;

public class FileModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String url ="";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[FileModifyView.java] number : " + number);
		
		FileDAO gDao = FileDAO.getInstance();
		FileVO gVo = new FileVO();

		gVo = gDao.getFileView(number);
		System.out.println("[FileModifyView.java] gVo: " + gVo);
		
		if (gVo != null) {
			System.out.println("[FileModifyView.java] 게시물을 호출합니다.");
			
			request.setAttribute("FileModifyView", gVo);
			request.setAttribute("smarteditor", true);
			
			url = "/admin/html/file/modify.jsp";
		} else {
			System.out.println("[FileModifyView.java] 게시물 호출에 실패하였습니다.");
			
			url = "/admin/html/file/list.jsp";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}