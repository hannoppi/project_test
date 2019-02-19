<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="dto.MemberVO" %>
<%
	MemberVO user = (MemberVO) session.getAttribute("user");
	System.out.println("[header.jsp] user : " + user);
	
	if (user == null) {
%>
		<script type="text/javascript">
			alert("비정상적인 접근입니다. 로그인해주세요.");
			
			location.href = '/admin/login.do';
		</script>
<%
		response.sendRedirect("/admin/login.do");
	}
%>