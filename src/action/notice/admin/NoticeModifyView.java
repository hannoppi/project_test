package action.notice.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.NoticeDAO;
import dto.NoticeVO;

public class NoticeModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[NoticeModifyView.java] number : " + number);
		
		NoticeDAO nDao = NoticeDAO.getInstance();
		NoticeVO nVo = new NoticeVO();

		nVo = nDao.getNoticeView(number);
		System.out.println("[NoticeModifyView.java] nVo" + nVo);
		
		if (nVo != null) {
			System.out.println("[NoticeModifyView.java] 게시물을 호출합니다.");
			
			request.setAttribute("noticeModify", nVo);
			request.setAttribute("smarteditor", true);
			
			url = "/admin/html/notice/modify.jsp";
		} else {
			System.out.println("[NoticeModifyView.java] 게시물 호출에 실패하였습니다.");
			
			url = "/admin/html/notice/list.jsp";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath(url);
		
		return forward;
	}
}
