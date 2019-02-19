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

public class MemberRegisterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String url = "";
		
		String id = request.getParameter("confirmId");
		System.out.println("[MemberRegisterAction.java] id: " + id);
		
		String password = request.getParameter("password");
		// System.out.println("[MemberJoinAction.java] password: " + password);
		
		String quiz = request.getParameter("quiz");
		System.out.println("[MemberRegisterAction.java] quiz: " + quiz);
		
		String name = request.getParameter("name");
		System.out.println("[MemberRegisterAction.java] name: " + name);
		
		// String birthday = request.getParameter("birthday1") + "년" + " " + request.getParameter("birthday2") + "월" + " " + request.getParameter("birthday3") +  "일";
		String birthday = request.getParameter("birthday1") + request.getParameter("birthday2") + request.getParameter("birthday3");
		System.out.println("[MemberRegisterAction.java] birthday : " + birthday);
		
		String service = request.getParameter("service");
		System.out.println("[MemberRegisterAction.java] service: " + service);
		
		// String tel = request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3");
		String tel = request.getParameter("tel1") + request.getParameter("tel2") + request.getParameter("tel3");
		System.out.println("[MemberRegisterAction.java] tel : " + tel);
		
		// String phone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-" + request.getParameter("phone3");
		String phone = request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");
		System.out.println("[MemberRegisterAction.java] phone : " + phone);
		
		String postcode = request.getParameter("postcode");
		System.out.println("[MemberRegisterAction.java] postcode : " + postcode);
		
		String address = request.getParameter("address");
		System.out.println("[MemberRegisterAction.java] address : " + address);
		
		String email = request.getParameter("email1") + "@" + request.getParameter("email2");
		System.out.println("[MemberRegisterAction.java] email : " + email);
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = new MemberVO();
		
		mVo.setId(id);
		mVo.setPassword(password);
		mVo.setQuiz(quiz);
		mVo.setName(name);
		mVo.setBirthday(birthday);
		mVo.setPostcode(postcode);
		mVo.setTel(tel);
		mVo.setPhone(phone);
		mVo.setEmail(email);
		mVo.setAddress(address);
		
		Boolean result = mDao.insert(mVo);
		System.out.println("[MemberRegisterAction.java] result: " + result);
		
		if (result) {
			System.out.println("[MemberRegisterAction.java] 회원가입에 성공하였습니다.");
			
			mVo = mDao.inquiry(id, password);
			System.out.println("[MemberJoinAction.java] user: " + mVo);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", mVo);
			
			url = "/admin/member/result.do";
		} else {
			System.out.println("[MemberRegisterAction.java] 회원가입에 실패하였습니다.");
			
			url = "/admin/member/result.do";
		}
		
		response.sendRedirect(url);
		
		return null;
	}
}