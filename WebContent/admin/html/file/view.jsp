<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>다운로드 게시판 관리 &lt; 게시판 관리 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body>
	<%@ include file="/admin/html/session/authentication.jsp" %>
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<section id="contents">
			<%@ include file="/admin/html/file/include/hgroup.jsp" %>
			
			<table class="table_global style_tableView">
				<caption>다운로드 게시판의 등록된 게시물의 번호, 등록일, 조회수, 아이디, 작성자, 카테고리, 제목, 내용, 첨부파일을 확인할 수 있습니다.</caption>
				
				<colgroup>
					<col style="width:10%;" />
					<col />
				</colgroup>
				
				<tbody>
					<tr>
						<th scope="row">번호</th>
						<td><c:out value="${fileView.number }" /></td>
					</tr>
					<tr>
						<th scope="row">등록일</th>
						<td><fmt:formatDate value="${fileView.regdate }" type="date"/></td>
					</tr>
					<tr>
						<th scope="row">조회수</th>
						<td><c:out value="${fileView.count }" /></td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td><c:out value="${fileView.id }" /></td>
					</tr>
					<tr>
						<th scope="row">작성자</th>
						<td><c:out value="${fileView.name }" /></td>
					</tr>
					<tr>
						<th scope="row">카테고리</th>
						<td><c:out value="${fileView.category }" /></td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td><c:out value="${fileView.subject }" /></td>
					</tr>
					<tr class="row_local">
						<th scope="row">내용</th>
						<td>${fileView.content }</td>
					</tr>
					<tr>
						<th scope="row">첨부파일</th>
						<td><a href="/admin/html/file/download.jsp?fileName=${fileView.file }"><c:out value="${fileView.file }" /></a></td>
					</tr>
				</tbody>
			</table><!-- // table_global -->
			
			<div class="board_gravity">
				<div class="inner_gravity">
					<a href="/admin/file/modify.do?number=<c:out value="${fileView.number }" />" class="button_global">수정</a>
					<a href="javascript:UI.callBoardDelete('/admin/file/delete.action?number=<c:out value="${fileView.number }" />')" class="button_global">삭제</a>
					<a href="/admin/file/list.do" class="button_global">목록</a>
				</div><!-- // inner_gravity -->
			</div><!-- // board_gravity -->
			
			<%@ include file="/admin/html/include/comment.jsp" %>
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>