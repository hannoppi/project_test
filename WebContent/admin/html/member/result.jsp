<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>회원가입 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body>
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
	
		<c:set var="user" value="${user }" scope="session" />
		
		<div class="hgroup">
		    <h3 class="title">결과</h3>
		    <p class="descript">회원님의 아이디는 <c:out value="${user.id }" />입니다.</p>
		</div><!-- // hgroup -->
		
		<a href="/admin/notice/list.do">메인 페이지로 이동</a>
		
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>