package action.notice.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.NoticeDAO;
import dto.NoticeVO;

public class NoticeWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		String id = request.getParameter("id");
		System.out.println("[NoticeWriteAction.java] id : " + id);
		
		String name = request.getParameter("name");
		System.out.println("[NoticeWriteAction.java] name : " + name);
		
		String category = request.getParameter("category");
		System.out.println("[NoticeWriteAction.java] category : " + category);
		
		String subject = request.getParameter("subject");
		System.out.println("[NoticeWriteAction.java] subject : " + subject);
		
		String content = request.getParameter("content");
		System.out.println("[NoticeWriteAction.java] content : " + content);
		
		NoticeDAO nDao = NoticeDAO.getInstance();
		NoticeVO nVo = new NoticeVO();
		
		nVo.setId(id);
		nVo.setName(name);
		nVo.setCategory(category);
		nVo.setSubject(subject);
		nVo.setContent(content);
		
		nVo = nDao.setNoticeWirte(nVo);
		System.out.println("nVo : " + nVo);
		
		if (nVo != null) {
			System.out.println("[NoticeWriteAction.java] 게시물 등록에 성공하였습니다.");
			
			request.setAttribute("noticeView", nVo);
			
			url = "/admin/notice/list.do";
		} else {
			System.out.println("[NoticeWriteAction.java] 게시물 등록에 실패하였습니다.");
			
			url = "/admin/notice/list.do";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(url);
		
		return forward;
	}
}
