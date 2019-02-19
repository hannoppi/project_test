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
			
			<form name="frm" action="/admin/board/reply.action" method="post">
				id: <input type="text" name="id" value="<c:out value="${user.id }" />" />
				name: <input type="text" name="name" value="<c:out value="${user.name }" />" />
				number: <input type="text" name="number" value="<c:out value="${BoardReplyView.number }" />" />
				reply_reference: <input type="text" name="reply_reference" value="<c:out value="${BoardReplyView.replyReference }" />" />
				reply_depth: <input type="text" name="reply_depth" value="<c:out value="${BoardReplyView.replyDepth }" />" />
				reply_sequence: <input type="text" name="reply_sequence" value="<c:out value="${BoardReplyView.replySequence }" />" />
				
				<table class="table_global">
					<caption>○○ 게시판에 등록할 게시물의 제목, 내용을 입력할 수 있습니다.</caption>
					
					<colgroup>
						<col style="width:20%;" />
						<col />
					</colgroup>
					
					<tbody>
						<tr>
							<th scope="row">카테고리</th>
							<td>
								<span class="inputfield_outer"><input type="text" name="category" class="inputfield_global" value="<c:out value="${BoardReplyView.category }" />" /></span>
							</td>
						</tr>
						<tr>
							<th scope="row">제목</th>
							<td>
								<span class="inputfield_outer"><input type="text" name="subject" class="inputfield_global" value="" /></span>
							</td>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td>
								<span class="textfield_outer"><textarea name="content" id="content" class="textfield_global"></textarea></span>
							</td>
						</tr>
					</tbody>
				</table><!-- // table_global -->
				
				<div class="board_gravity">
					<div class="inner_gravity">
						<button type="submit" class="button_smarteditor">등록</button>
						<a href="javascript:history.go(-1);" class="button_global">취소</a>
					</div><!-- // inner_gravity -->
				</div><!-- // board_gravity -->
			</form>
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>