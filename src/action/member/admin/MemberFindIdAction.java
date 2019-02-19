package action.member.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import dto.MemberVO;

public class MemberFindIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		String name = request.getParameter("name");
		System.out.println("[MemberFindIdAction.java] name: " + name);
		
		String birthday = request.getParameter("birthday");
		System.out.println("[MemberFindIdAction.java] birthday: " + birthday);
		
		String service = request.getParameter("service");
		System.out.println("[MemberFindIdAction.java] service: " + service);
		
		String phone = request.getParameter("phone");
		System.out.println("[MemberFindIdAction.java] phone: " + phone);
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = new MemberVO();
		
		mVo = mDao.findId(name, birthday, phone);
		System.out.println("[MemberFindIdAction.java] mVo: " + mVo);
		
		if (mVo != null) {
			System.out.println("[MemberFindIdAction.java] 아이디 찾기에 성공하였습니다.");
			
			HttpSession session = request.getSession();
			session.setAttribute("user", mVo);
			
			url = "/admin/member/result.do";
		} else {
			System.out.println("[MemberFindIdAction.java] 아이디 찾기에 실패하였습니다.");
			
			url = "/admin/find/id.do";
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(url);
		
		return forward;
	}
}