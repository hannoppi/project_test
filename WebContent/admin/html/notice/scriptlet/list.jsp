<%@page import="java.util.List"%>
<%@page import="dto.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>공지사항 게시판 관리 &lt; 게시판 관리 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body>
	<%@ include file="/admin/html/session/authentication.jsp" %>
	
	<%
		int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
		int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
		int startPage = ((Integer) request.getAttribute("startPage")).intValue();
		int endPage = ((Integer) request.getAttribute("endPage")).intValue();
	%>
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<section id="contents">
			<%@ include file="/admin/html/notice/include/hgroup.jsp" %>
			
			<table class="table_global design_default" border="1" width="100%">
				<caption>○○ 게시판의 등록된 게시물의 번호, 카테고리, 내용, 등록일, 조회수를 확인할 수 있습니다.</caption>
				
				<colgroup>
					<col style="width:10%;" />
					<col style="width:10%;" />
					<col />
					<col style="width:10%;" />
					<col style="width:10%;" />
				</colgroup>
				
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">카테고리</th>
						<th scope="col">제목</th>
						<th scope="col">등록일</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${not empty noticeList }">
							<c:forEach var="noticeList" items="${noticeList }">
								<tr>
									<td>${noticeList.number }</td>
									<td>${noticeList.category }</td>
									<td class="subject"><a href="/admin/notice/view.do?number=${noticeList.number }">${noticeList.subject }</a></td>
									<td><fmt:formatDate value="${noticeList.regdate }" type="date" /></td>
									<td>${noticeList.count }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="not_result" colspan="5">게시물이 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table><!-- // table_global -->
			
			<c:if test="${not empty noticeList }">
				<div class="paging">
					<% if(currentPage <= 1) { %>
					<span class="previous">이전</span>
					<% } else { %>
					<a href="/admin/notice/list.do?page=<%= currentPage - 1 %>" class="previous">이전</a>
					<% } %>
					
					<div class="inner_paging">
						<%for(int i = startPage; i <= endPage; i++) {
						if(i == currentPage) { %>
						<a href="/admin/notice/list.do?page=<%=i %>" class="current"><%=i %><span class="invisible">현재 페이지</span></a>
						<% } else { %>
						<a href="/admin/notice/list.do?page=<%=i %>"><%=i %></a>
						<% }
						} %>
					</div><!-- // inner_paging -->
					
					<% if(currentPage >= maxPage) { %>
					<span class="next">다음</span>
					<% } else { %>
					<a href="/admin/notice/list.do?page=<%=currentPage + 1 %>" class="next">다음</a>
					<% } %>
				</div><!-- // paging -->
			</c:if>
			
			<div class="board_search">
				<form action="/admin/notice/list.do" method="get">
					<div class="design_select">
						<strong class="invisible">검색 조건 선택</strong>
						
						<label for="selectCategory">제목</label>
						
						<select id="selectCategory" name="option">
							<option value="subject" selected>제목</option>
							<option value="content">내용</option>
							<option value="write">작성자</option>
						</select>
					</div><!-- // design_select -->
					
					<span class="inputfield_outer"><input type="text" name="keyword" class="inputfield_keyword" /></span>
					<input type="submit" value="검색" class="button_global button_medium button_black" />
				</form>
			</div><!-- // board_search -->
			
			<div class="board_utility">
				<div class="inner_utility">
					<a href="/admin/notice/write.do" class="button_global button_medium button_black">등록</a>
				</div><!-- // inner_utility -->
			</div><!-- // board_utility -->
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>