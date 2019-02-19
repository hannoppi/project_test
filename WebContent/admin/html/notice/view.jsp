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
			
			<table class="table_global style_tableView">
				<caption>공지사항 게시판의 등록된 게시물의 번호, 등록일, 조회수, 아이디, 작성자, 카테고리, 제목, 내용을 확인할 수 있습니다.</caption>
				
				<colgroup>
					<col style="width:10%;" />
					<col />
				</colgroup>
				
				<tbody>
					<tr>
						<th scope="row">번호</th>
						<td><c:out value="${noticeView.number }" /></td>
					</tr>
					<tr>
						<th scope="row">등록일</th>
						<td><fmt:formatDate value="${noticeView.regdate }" type="date"/></td>
					</tr>
					<tr>
						<th scope="row">조회수</th>
						<td><c:out value="${noticeView.count }" /></td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td><c:out value="${noticeView.id }" /></td>
					</tr>
					<tr>
						<th scope="row">작성자</th>
						<td><c:out value="${noticeView.name }" /></td>
					</tr>
					<tr>
						<th scope="row">카테고리</th>
						<td><c:out value="${noticeView.category }" /></td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td><c:out value="${noticeView.subject }" /></td>
					</tr>
					<tr class="row_local">
						<th scope="row">내용</th>
						<td>${noticeView.content }</td>
					</tr>
				</tbody>
			</table><!-- // table_global -->
			
			<div class="board_gravity">
				<div class="inner_gravity">
					<a href="/admin/notice/modify.do?number=<c:out value="${noticeView.number }" />" class="button_global">수정</a>
					<a href="javascript:UI.callBoardDelete('/admin/notice/delete.action?number=<c:out value="${noticeView.number }" />')" class="button_global">삭제</a>
					<a href="/admin/notice/list.do" class="button_global">목록</a>
				</div><!-- // inner_gravity -->
			</div><!-- // board_gravity -->
			
			<%@ include file="/admin/html/include/comment.jsp" %>
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
	
	<!--
	<script type="text/javascript">
		$(function () {
			$('.button_comment').on('click', function () {
				var page = '${boardView.number }';
				console.log("(button_comment) page: ", page);
				
				var query = {
					board: $('input[name = "board"]').val(),
					number: $('input[name = "number"]').val(),
					id: $('input[name = "id"]').val(),
					name: $('input[name = "name"]').val(),
					content: $('textarea[name = "comment"]').val(),
					reference: $('input[name = "reference"]').val(),
					level: $('input[name = "level"]').val(),
					sequence: $('input[name = "sequence"]').val(),
					together: $('input[name = "together"]').val()
				};
				
				$.ajax({
					type: 'POST',
					url: '/admin/board/comment.action',
					data: query,
					error :function(xhr) {
						console.log("(button_comment) xhr: ", xhr);
			            console.log("(button_comment) xhr.statusText: ", xhr.statusText);
			        },
					success: function (data) {
						data = JSON.parse(data);
						console.log("(button_comment) data: ", data);
						
						var $comment = $('.comment_list'),
						result = data.result,
						html = "",
						i,
						j,
						together;
						
						if (result !== undefined) {
							for (i = 0; i < result.length; i++) {
								html += '<li class="item">';
								
								for (j = 0; j < result[i].value.length; j++) {
									togerther = result[j].value[j].together;
									console.log("togerther: ", togerther);
									
									html += '<div>board: <em class="commentBoard">' + result[j].value[j].board + '</em></div>';
									html += '<div>number: <em class="commentNumber">' + result[j].value[j].number + '</em></div>';
									html += '<div>id: <em class="commentId">' + result[j].value[j].id + '</em></div>';
									html += '<div>name: <em class="commentName">' + result[j].value[j].name + '</em></div>';
									html += '<div>content: <em class="commentContent">' + result[j].value[j].content + '</em></div>';
									html += '<div>reference: <em class="commentReference">' + result[j].value[j].reference + '</em></div>';
									html += '<div>level: <em class="commentLevel">' + result[j].value[j].level + '</em></div>';
									html += '<div>sequence: <em class="commentSequence">' + result[j].value[j].sequence + '</em></div>';
									html += '<div>together: <em class="commentTogether">' + result[j].value[j].together + '</em></div>';
									html += '<div>regdate: <em class="commentRegdate">' + result[j].value[j].regdate + '</em></div>';
									html += '<button type="button" class="button_activate">답변</button>';
									html += '<hr />';
									html += '<div class="wrap_activate"></div>';
								}
								html += '</li>';
							}
							
							console.log("(button_comment) html: ", html);
							$comment.append(html);
							$comment.find('.item').attr('class', 'item' + togerther);
						}
					}
				});
			});
			
			$(document).on('click', '.button_activate', function () {
				var html = "";
				
				html += '<ul>';
				html += '<li>board <input type="text" name="comment_board" value="' + $(this).closest('li').find('.commentBoard').text() + '" /></li>';
				html += '<li>number <input type="text" name="comment_number" value="' + $(this).closest('li').find('.commentNumber').text() + '" /></li>';
				html += '<li>id <input type="text" name="comment_id" value="아이디" /></li>';
				html += '<li>name <input type="text" name="comment_name" value="이름" /></li>';
				html += '<li>reference <input type="text" name="comment_reference" value="' + $(this).closest('li').find('.commentReference').text() + '" /></li>';
				html += '<li>level <input type="text" name="comment_level" value="' + (parseInt($(this).closest('li').find('.commentLevel').text()) + 1) + '" /></li>';
				html += '<li>sequence <input type="text" name="comment_sequence" value="' + $(this).closest('.comment_list').find('.item1').length + '" /></li>';
				html += '<li>content <textarea name="comment_reply" name="textfield_global">내용</textarea></li>';
				html += '</ul>';
				html += '<button type="button" class="button_reply">대댓글 등록</button>';
				
				$(this).siblings('.wrap_activate').append(html);
			});
			
			$(document).on('click', '.button_reply', function () {
				var page = '${boardView.number }';
				console.log("(button_reply) page: ", page);
				
				var query = {
					board: $('input[name = "comment_board"]').val(),
					number: $('input[name = "comment_number"]').val(),
					id: $('input[name = "comment_id"]').val(),
					name: $('input[name = "comment_name"]').val(),
					content: $('textarea[name = "comment_reply"]').val(),
					reference: $('input[name = "comment_reference"]').val(),
					level: $('input[name = "comment_level"]').val(),
					sequence: $('input[name = "comment_sequence"]').val()
				};
				
				$.ajax({
					type: 'POST',
					url: '/admin/board/comment_reply.action',
					data: query,
					error :function(xhr) {
						console.log("(button_reply) xhr: ", xhr);
			            console.log("(button_reply) xhr.statusText: ", xhr.statusText);
			        },
					success: function (data) {
						data = JSON.parse(data);
						console.log("(button_reply) data: ", data);
						
						var $comment = $('.comment_list'),
						result = data.result,
						html = "",
						i,
						j;
						
						if (result !== undefined) {
							for (i = 0; i < result.length; i++) {
								html += '<li>';
								
								for (j = 0; j < result[i].value.length; j++) {
									html += '<div>' + result[i].value[j].number + '</div>';
									html += '<div>' + result[i].value[j].name + '</div>';
									html += '<div>' + result[i].value[j].content + '</div>';
									html += '<div>' + result[i].value[j].regdate + '</div>';
								}
								html += '</li>';
							}
							
							console.log("(button_reply) html: ", html);
							$comment.append(html);
						}
					}
				});
			});
		});
	</script>
	-->
</body>
</html>