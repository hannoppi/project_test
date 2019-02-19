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
			
			<form name="frm" action="/admin/board/write.action" method="post">
				<input type="hidden" name="id" value="<c:out value="${user.id }" />" />
				<input type="hidden" name="name" value="<c:out value="${user.name }" />" />
				
				<table class="table_global">
					<caption>일반 게시판에 등록할 게시물의 카테고리, 제목, 내용을 입력할 수 있습니다.</caption>
					
					<colgroup>
						<col style="width:10%;" />
						<col />
					</colgroup>
					
					<tbody>
						<tr>
							<th scope="row">카테고리</th>
							<td><span class="inputfield_outer"><input type="text" name="category" class="inputfield_global" value="일반" readonly /></span></td>
						</tr>
						<tr>
							<th scope="row">제목</th>
							<td><span class="inputfield_outer"><input type="text" name="subject" class="inputfield_global" value="" /></span></td>
						</tr>
						
						<tr>
							<th scope="row" rowspan="2">유튜브 동영상 주소</th>
							<td>
								<span class="inputfield_outer">
									<input type="hidden" name="uri" />
									<input type="text" class="inputfield_global youtube_url" value="https://youtu.be/kt3ymhmDfCk" />
									<button type="button" class="button_global button_TEST1">등록</button>
								</span>
							</td>
						</tr>
						<tr>
							<td>
								<div class="youtube_wrap">
									<div class="player_wrap">
										<iframe class="youtube_player" width="100%" height="100%" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
									</div><!-- // player_wrap -->
									
									<button type="button" class="button_global button_TEST2">유튜브 동영상 썸네일 추출하기</button>
									
									<ul class="thumbnail_list">
										<!--
										<li>
											<span class="box_thumbnail">
												<input type="radio" id="thumbnail#" name="thumbnail" value="https://img.youtube.com/vi/#.jpg" />
												<label for="thumbnail#">#</label>
											</span>
										</li>
										-->
									</ul>
								</div><!-- // youtube_wrap -->
							</td>
						</tr>
						
						<tr class="row_content">
							<th scope="row">내용</th>
							<td><span class="textfield_outer"><textarea name="content" id="content" class="textfield_global"></textarea></span></td>
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
	
	<script>
		var btn_youtube_priview = document.querySelector('.button_TEST1');
		var default_url = 'https://www.youtube.com/embed/';
		var youtube_player = document.querySelector('.youtube_player');
		var youtube_url = null;
		
		btn_youtube_priview.addEventListener('click', function () {
			youtube_url = document.querySelector('.youtube_url').value;
			
			if (youtube_url.indexOf('embed') < 0) {
				// console.log("없다.");
				
				youtube_player.removeAttribute('src');
				youtube_player.setAttribute('src', default_url + youtube_url.replace('https://youtu.be/', ''));
			} else {
				// console.log("있다.");
				
				youtube_player.removeAttribute('src');
				youtube_player.setAttribute(youtube_url.replace('https://youtu.be/', ''));
			}
			
			document.querySelector('input[name = "uri"]').setAttribute('value', youtube_url.replace('https://youtu.be/', ''));
		});
		
		var html = '';
		var array = ['동영상 배경 썸네일(480*360)', '동영상 시작 지점 썸네일(120*90)', '동영상 중간 지점 썸네일(120*90)', '동영상 종료 지점 썸네일(120*90)'];
		
		document.querySelector('.button_TEST2').addEventListener('click', function () {
			html = '';
			
			for (var i = 0; i < 4; i++) {
				html += '<li><span class="box_thumbnail"><input type="radio" id="thumbnail' + i + '" name="thumbnail" value="https://img.youtube.com/vi/' + youtube_url.replace('https://youtu.be/', '') + '/' + i + '.jpg" /><label for="thumbnail' + i + '" style="background-image:url(https://img.youtube.com/vi/' + youtube_url.replace('https://youtu.be/', '') + '/' + i + '.jpg)">' + array[i] + '</label></span></li>';
			};
			
			document.querySelector('.thumbnail_list').innerHTML = html;
			document.querySelector('.thumbnail_list').style.display = 'block';
		});
	</script>
</body>
</html>