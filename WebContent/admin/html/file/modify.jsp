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
			
			<form name="frm" action="/admin/file/modify.action" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="<c:out value="${user.id }" />" />
				<input type="hidden" name="name" value="<c:out value="${user.name }" />" />
				<input type="hidden" name="number" value="<c:out value="${fileModifyView.number }" />" />
				
				<table class="table_global">
					<caption>다운로드 게시판에 등록된 게시물의 카테고리, 제목, 내용, 첨부파일을 수정할 수 있습니다.</caption>
					
					<colgroup>
						<col style="width:10%;" />
						<col />
					</colgroup>
					
					<tbody>
						<tr>
							<th scope="row">카테고리</th>
							<td><span class="inputfield_outer"><input type="text" name="category" class="inputfield_global" value="<c:out value="${fileModifyView.category }" />" readonly /></span></td>
						</tr>
						<tr>
							<th scope="row">제목</th>
							<td><span class="inputfield_outer"><input type="text" name="subject" class="inputfield_global" value="<c:out value="${fileModifyView.subject }" />" /></span>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td><span class="textfield_outer"><textarea name="content" id="content" class="textfield_global"><c:out value="${fileModifyView.content }" /></textarea></span>
						</tr>
						<tr>
							<th scope="row">첨부파일</th>
							<td>
								<button type="button" class="button_addition">추가</button>
								
								<ul class="list_files">
									<c:forEach var="files" items="${fileModifyView.file }" varStatus="status">
									<li>
										<input type="text" name="existingFile" value="<c:out value="${files }" />" />
										<c:out value="${files }" />
										<input type="file" name="file${status.index }" />
									</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
					</tbody>
				</table><!-- // table_global -->

				<div class="board_gravity">
					<div class="inner_gravity">
						<button type="submit" class="button_smarteditor">수정</button>
						<a href="javascript:history.go(-1);" class="button_global">취소</a>
					</div><!-- // inner_gravity -->
				</div><!-- // board_gravity -->
			</form>
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
	
	<script>
		var count = $('input[type = "file"]').length;
		console.log("[file] [modify.jsp] count: ", count);
		
		$('.file-add').on('click', function () {
			$('.file-list').append('<li><input type="hidden" name="existingFile" /><input type="file" name="file'+count+'" /></li>');
			count++;
		});
	</script>
</body>
</html>