<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>이미지 게시판 관리 &lt; 게시판 관리 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body>
	<%@ include file="/admin/html/session/authentication.jsp" %>
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<section id="contents">
			<%@ include file="/admin/html/gallery/include/hgroup.jsp" %>
			
			<table class="table_global style_tableView">
				<caption>이미지 게시판의 등록된 게시물의 번호, 등록일, 조회수, 아이디, 작성자, 카테고리, 제목, 내용, 첨부파일을 확인할 수 있습니다.</caption>
				
				<colgroup>
					<col style="width:10%;" />
					<col />
				</colgroup>
				
				<tbody>
					<tr>
						<th scope="row">번호</th>
						<td><c:out value="${galleryView.number }" /></td>
					</tr>
					<tr>
						<th scope="row">등록일</th>
						<td><fmt:formatDate value="${galleryView.regdate }" type="date"/></td>
					</tr>
					<tr>
						<th scope="row">조회수</th>
						<td><c:out value="${galleryView.count }" /></td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td><c:out value="${galleryView.id }" /></td>
					</tr>
					<tr>
						<th scope="row">작성자</th>
						<td><c:out value="${galleryView.name }" /></td>
					</tr>
					<tr>
						<th scope="row">카테고리</th>
						<td><c:out value="${galleryView.category }" /></td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td><c:out value="${galleryView.subject }" /></td>
					</tr>
					<tr class="row_local">
						<th scope="row">내용</th>
						<td>${galleryView.content }</td>
					</tr>
					<tr class="row_thumbnail">
						<th scope="row">미리보기</th>
						<td>
							<c:forEach var="images" items="${galleryView.image }" varStatus="status">
								<a href="javascript:;" class="link_thumbnail">
									<img src="/admin/upload/board/gallery/images/<c:out value="${images }" />" alt="<c:out value="${galleryView.subject }" />" class="thumbnail_data" />
								</a>
							</c:forEach>
						</td>
					</tr><!-- // row_thumbnail -->
					<tr>
						<th scope="row">첨부파일</th>
						<td>
							<c:forEach var="images" items="${galleryView.image }" varStatus="status">
								<!--
								<a href="/admin/upload/board/gallery/images/<c:out value="${images }" />" target="_blank" class="link_download">
									<c:out value="${images }" />
									<c:if test="${!status.last }">, </c:if>
								</a>
								-->
								
								<a href="/admin/html/gallery/download.jsp?fileName=${images }">
									<c:out value="${images }" />
									<c:if test="${!status.last }">, </c:if>
								</a>
							</c:forEach>
						</td>
					</tr>
				</tbody>
			</table><!-- // table_global -->
			
			<div class="board_gravity">
				<div class="inner_gravity">
					<a href="/admin/gallery/modify.do?number=<c:out value="${galleryView.number }" />" class="button_global">수정</a>
					<a href="javascript:UI.callBoardDelete('/admin/gallery/delete.action?number=<c:out value="${galleryView.number }" />')" class="button_global">삭제</a>
					<a href="/admin/gallery/list.do" class="button_global">목록</a>
				</div><!-- // inner_gravity -->
			</div><!-- // board_gravity -->
			
			<%@ include file="/admin/html/include/comment.jsp" %>
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
	
	<div class="background_layer"></div><!-- // background_layer -->
	
	<div class="layer_wrapper">
		<div class="layer_outer">
			<div class="layer_inner">
				<div class="preview_layer">
					<div class="layer_header"></div>
				
					<div class="layer_body">
						<img src="" alt="" class="img_preview" />
					</div>
					
					<div class="layer_footer">
						<button type="button" class="button_global button_layer_close">닫기</button>
					</div>
				</div><!-- // layer_inner -->
			</div><!-- // preview_layer -->
		</div><!-- // layer_outer -->
	</div><!-- // layer_wrapper -->
	<!--
	<script>
		;(function ($) {
			var $btnPreview = $('.link_thumbnail'),
			$layerPreview = $('.layer_wrapper'),
			$btnClose = $('.button_close');
			
			$btnClose.on('click', function () {
				this.ownerDocument.getElementsByClassName('layer_wrapper')[0].setAttribute('class', 'layer_wrapper')
			});
			
			$btnPreview.on('click', function (event) {
				console.log("[gallery] [view.jsp] this.parentNode.children.length: ", this.parentNode.children.length);
				
				var imgSrc = event.target.src;
				console.log("[gallery] [view.jsp] imgSrc: ", imgSrc);
				
				$layerPreview.addClass('open').find('.img_preview').attr('src', imgSrc);
			});
		}(jQuery));
	</script>
	 -->
</body>
</html>