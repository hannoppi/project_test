package action.notice.vue.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.NoticeDAO;

public class NoticeDeleteActionVue implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[NoticeDeleteAction.java] number : " + number);
		
		String id = request.getParameter("id");
		System.out.println("[NoticeDeleteAction.java] id : " + id);
		
		NoticeDAO nDao = NoticeDAO.getInstance();
		int result = nDao.noticeDelete(number, id);
		
		if (result > 0) {
			System.out.println("[NoticeDeleteAction.java] 게시물을 삭제하였습니다.");
		} else {
			System.out.println("[NoticeDeleteAction.java] 게시물을 삭제하는데 실패하였습니다.");
		}
		
		return null;
	}
}