package action.file.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.FileDAO;
import dto.FileVO;

public class FileView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url ="";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[FileView.java] number : " + number);
		
		FileDAO fDao = FileDAO.getInstance();
		FileVO fVo = new FileVO();
		
		fDao.countUpdate(number);
		fVo = fDao.getFileView(number);
		
		if (fVo != null) {
			System.out.println("[FileView.java] 게시물을 호출합니다.");
			request.setAttribute("fileView", fVo);
			
			url = "/admin/html/file/view.jsp";
		} else {
			System.out.println("[FileView.java] 게시물 호출에 실패하였습니다.");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}