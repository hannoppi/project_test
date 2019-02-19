<%@page import="java.util.List"%>
<%@page import="dto.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>포트폴리오 게시판 관리 &lt; 사이트 관리 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body>
	<%@ include file="/admin/html/session/authentication.jsp" %>
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<section id="contents">
			<%@ include file="/admin/html/portfolio/include/hgroup.jsp" %>
			
			<div class="paging">	</div><!-- // paging -->
			
			<div class="board_gravity">
				<div class="inner_gravity">
					<a href="/admin/portfolio/write.do" class="button_global">등록</a>
				</div><!-- // inner_gravity -->
			</div><!-- // board_gravity -->
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>