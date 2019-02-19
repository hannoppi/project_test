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
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<section id="contents">
			<%@ include file="/admin/html/notice/include/hgroup.jsp" %>
			
			<table class="table_global style_tableList">
				<caption>공지사항 게시판의 등록된 게시물의 번호, 카테고리, 제목, 등록일, 조회수를 확인할 수 있습니다.</caption>
				
				<colgroup>
					<col style="width:10%;" />
					<col style="width:10%;" />
					<col />
					<col style="width:10%;" />
					<col style="width:15%;" />
					<col style="width:10%;" />
				</colgroup>
				
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">카테고리</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">등록일</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${not empty noticeList }">
							<c:forEach var="noticeList" items="${noticeList }">
								<tr>
									<td><c:out value="${noticeList.number }" /></td>
									<td><c:out value="${noticeList.category }" /></td>
									<td class="subject">
										<c:choose>
											<c:when test="${noticeList.replyDepth != 0}">
												<c:forEach var="i" begin="0" end="${noticeList.replyDepth - 1}" varStatus="status">
												*
												</c:forEach>
												답변: 
											</c:when>
											
											<c:otherwise>
											</c:otherwise>
										</c:choose>
										<a href="/admin/notice/view.do?number=<c:out value="${noticeList.number }" />"><c:out value="${noticeList.subject }" /></a>
									</td>
									<td><c:out value="${noticeList.name }" /></td>
									<td><fmt:formatDate value="${noticeList.regdate }" type="date" /></td>
									<td><c:out value="${noticeList.count }" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6">게시물이 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table><!-- // table_global -->
			
			<c:if test="${not empty noticeList }">
				<div class="paging">
					<c:choose>
						<c:when test="${currentPage <= 1 }">
							<span class="previous">이전</span>
						</c:when>
						
						<c:otherwise>
							<a href="/admin/notice/list.do?page=<c:out value="${currentPage - 1 }" />" class="previous">이전</a>
						</c:otherwise>
					</c:choose>
					
					<div class="inner_paging">
						<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1" varStatus="status">
							<c:choose>
								<c:when test="${i == currentPage }">
									<a href="/admin/notice/list.do?page=<c:out value="${i }" />" class="current"><c:out value="${i }" /><span class="invisible">현재 페이지</span></a>
								</c:when>
								
								<c:otherwise>
									<a href="/admin/notice/list.do?page=<c:out value="${i }" />"><c:out value="${i }" /></a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div><!-- // inner_paging -->
					
					<c:choose>
						<c:when test="${currentPage >= maxPage }">
							<span class="next">다음</span>
						</c:when>
						
						<c:otherwise>
							<a href="/admin/notice/list.do?page=<c:out value="${currentPage + 1 }" />" class="next">다음</a>
						</c:otherwise>
					</c:choose>
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
					<input type="submit" value="검색" class="button_global" />
				</form>
			</div><!-- // board_search -->
			
			<div class="board_gravity">
				<div class="inner_gravity">
					<a href="/admin/notice/write.do" class="button_global">등록</a>
				</div><!-- // inner_gravity -->
			</div><!-- // board_gravity -->
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>