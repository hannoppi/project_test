package action.notice.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.NoticeDAO;
import dto.NoticeVO;

public class NoticeModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		int number = Integer.parseInt(request.getParameter("number"));
		System.out.println("[NoticeModifyAction.java] number : " + number);
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		NoticeDAO nDao = NoticeDAO.getInstance();
		NoticeVO nVo = new NoticeVO();
		
		nVo.setId(id);
		nVo.setName(name);
		nVo.setCategory(category);
		nVo.setSubject(subject);
		nVo.setContent(content);
		
		nVo = nDao.setNoticeModify(number, nVo);
		System.out.println("[NoticeModifyAction.java] nVo : " + nVo);
		
		if (nVo != null) {
			System.out.println("[NoticeModifyAction.java] 게시물 수정에 성공하였습니다.");
			
			request.setAttribute("noticeModify", nVo);
			
			url = "/admin/notice/view.do?number=" + number;
		} else {
			System.out.println("[NoticeModifyAction.java] 게시물 수정에 실패하였습니다.");
			
			url = "/admin/notice/list.do";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(url);
		
		return forward;
	}
}
