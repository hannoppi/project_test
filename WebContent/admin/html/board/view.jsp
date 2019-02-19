<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>일반 게시판 관리 &lt; 게시판 관리 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body>
	<%@ include file="/admin/html/session/authentication.jsp" %>
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<section id="contents">
			<%@ include file="/admin/html/board/include/hgroup.jsp" %>
			
			<table class="table_global style_tableView">
				<caption>일반 게시판의 등록된 게시물의 번호, 등록일, 조회수, 아이디, 작성자, 카테고리, 제목, 내용을 확인할 수 있습니다.</caption>
				
				<colgroup>
					<col style="width:10%;" />
					<col />
				</colgroup>
				
				<tbody>
					<tr>
						<th scope="row">번호</th>
						<td><c:out value="${boardView.number }" /></td>
					</tr>
					<tr>
						<th scope="row">등록일</th>
						<td><fmt:formatDate value="${boardView.regdate }" type="date"/></td>
					</tr>
					<tr>
						<th scope="row">조회수</th>
						<td><c:out value="${boardView.count }" /></td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td><c:out value="${boardView.id }" /></td>
					</tr>
					<tr>
						<th scope="row">작성자</th>
						<td><c:out value="${boardView.name }" /></td>
					</tr>
					<tr>
						<th scope="row">카테고리</th>
						<td><c:out value="${boardView.category }" /></td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td><c:out value="${boardView.subject }" /></td>
					</tr>
					
					<c:if test="${not empty boardView.uri }">
					<tr class="row_video">
						<th scope="row">유튜브<br />동영상<br />미리보기</th>
						<td><iframe class="youtube_player" width="100%" height="100%" src="https://www.youtube.com/embed/<c:out value="${boardView.uri }" />" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></td>
					</tr>
					</c:if>
					<c:if test="${not empty boardView.thumbnail }">
					<tr class="row_thumbnail">
						<th scope="row">유튜브<br />썸네일<br />미리보기</th>
						<td>
							<ul class="thumbnail_list">
							  <li>
							    <span class="box_thumbnail">
							    	<img src="<c:out value="${boardView.thumbnail }" />" alt="<c:out value="${boardView.subject }" />" />
							    </span>
							  </li>
							</ul>
						</td>
					</tr>
					</c:if>
					
					<tr class="row_local">
						<th scope="row">내용</th>
						<td>${boardView.content }</td>
					</tr>
				</tbody>
			</table><!-- // table_global -->
			
			<div class="board_gravity">
				<div class="inner_gravity">
					<a href="/admin/board/reply.do?number=<c:out value="${boardView.number }" />" class="button_global">댓글</a>
					<a href="/admin/board/modify.do?number=<c:out value="${boardView.number }" />" class="button_global">수정</a>
					<a href="javascript:UI.callBoardDelete('/admin/board/delete.action?number=<c:out value="${boardView.number }" />')" class="button_global button_delete">삭제</a>
					<a href="/admin/board/list.do" class="button_global">목록</a>
				</div><!-- // inner_gravity -->
			</div><!-- // board_gravity -->
			
			<%@ include file="/admin/html/include/comment.jsp" %>
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>