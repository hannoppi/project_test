<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			
			<strong class="invisible">카테고리 선택</strong>
			<ul class="category_area">
				<li><a href="#" class="current">전체</a></li>
				<!-- <li><a href="#">일반</a></li> -->
			</ul><!-- // category_area -->
			
			<div class="gallery_area">
				<c:choose>
					<c:when test="${not empty galleryList }">
						<ul class="gallery_list">
						<c:forEach var="galleryList" items="${galleryList }" varStatus="status">
							<li>
								<div class="frame">
									<c:if test="${status.index } eq 0">
										<strong style="font-size:14px;">3번째 입니다.</strong>
									</c:if>
									
									<a href="/admin/gallery/view.do?number=<c:out value="${galleryList.number }" />">
										<span class="thumbnail">
											<img src="/admin/images/common/dummy_3x2.gif" alt="" class="dummy" />
											<img src="/admin/upload/board/gallery/images/<c:out value="${fn:split(galleryList.image, ',')[0] }" />" alt="" />
										</span><!-- // thumbnail -->
										
										<span class="subject">
											<em class="emphasis"><c:out value="${galleryList.subject }" /></em><!-- <span class="icon_latest">새 글</span> -->
										</span>
									</a>
									
									<dl class="options">
										<dt class="invisible">작성자</dt>
										<dd><c:out value="${galleryList.name }" /></dd>
										<dt class="invisible">등록일</dt>
										<dd class="right"><fmt:formatDate value="${galleryList.regdate }" type="date" /></dd>
									</dl><!--  // options -->
								</div><!-- // frame -->
							</li>
						</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="result_list">
							<li>게시물이 존재하지 않습니다.</li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div><!-- // gallery_area -->
			
			<c:if test="${not empty galleryList }">
				<div class="paging">
					<c:choose>
						<c:when test="${currentPage <= 1 }">
							<span class="previous">이전</span>
						</c:when>
						
						<c:otherwise>
							<a href="/admin/gallery/list.do?page=<c:out value="${currentPage - 1 }" />" class="previous">이전</a>
						</c:otherwise>
					</c:choose>
					
					<div class="inner_paging">
						<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1" varStatus="status">
							<c:choose>
								<c:when test="${i == currentPage }">
									<a href="/admin/gallery/list.do?page=<c:out value="${i }" />" class="current"><c:out value="${i }" /><span class="invisible">현재 페이지</span></a>
								</c:when>
								
								<c:otherwise>
									<a href="/admin/gallery/list.do?page=<c:out value="${i }" />"><c:out value="${i }" /></a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div><!-- // inner_paging -->
					
					<c:choose>
						<c:when test="${currentPage >= maxPage }">
							<span class="next">다음</span>
						</c:when>
						
						<c:otherwise>
							<a href="/admin/gallery/list.do?page=<c:out value="${currentPage + 1 }" />" class="next">다음</a>
						</c:otherwise>
					</c:choose>
				</div><!-- // paging -->
			</c:if>
			
			<div class="board_search">
				<form action="/admin/gallery/list.do" method="get">
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
					<a href="/admin/gallery/write.do" class="button_global">등록</a>
				</div><!-- // inner_gravity -->
			</div><!-- // board_gravity -->
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>