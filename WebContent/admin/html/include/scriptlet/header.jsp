<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.MemberVO" %>
<%
	MemberVO user = (MemberVO) session.getAttribute("user");
	System.out.println("[header.jsp] user : " + user);
	
	if (user != null) {
		response.sendRedirect("/admin/index.do");
	} else {
		response.sendRedirect("/admin/login.do");
	}
%>
<div id="wrapper">
	<header id="header">
		<h1><a href="/admin/login.do">홈페이지</a></h1>
		
		<%@ include file="/admin/html/include/gnb.jsp" %>
	</header>