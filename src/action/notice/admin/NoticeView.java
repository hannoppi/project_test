package action.notice.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.NoticeDAO;
import dto.NoticeVO;

public class NoticeView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[NoticeView.java] number : " + number);
		
		NoticeDAO nDao = NoticeDAO.getInstance();
		NoticeVO nVo = new NoticeVO();
		
		nDao.countUpdate(number);
		nVo = nDao.getNoticeView(number);
		
		if (nVo != null) {
			System.out.println("[NoticeView.java] 게시물을 호출합니다.");
			
			request.setAttribute("noticeView", nVo);
			
			url = "/admin/html/notice/view.jsp";
		} else {
			System.out.println("[NoticeView.java] 게시물 호출에 실패하였습니다.");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}
