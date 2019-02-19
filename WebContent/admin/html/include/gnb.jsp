<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav id="gnb">
	<h2 class="invisible">전체 메뉴 시작</h2>
	<ul class="menu_list">
		<li class="current"><!-- // li class="current" -->
			<a href="javascript:;">게시판 관리</a>
			
			<ul class="sub_list">
				<li><a href="/admin/notice/list.do">공지사항 게시판 관리</a></li><!-- // li class="current" -->
				<li><a href="/admin/board/list.do">일반 게시판 관리</a></li>
				<li><a href="/admin/gallery/list.do">이미지 게시판 관리</a></li>
				<li><a href="/admin/file/list.do">다운로드 게시판 관리</a></li>
				<!-- <li><a href="/admin/youtube/list.do">유튜브 게시판 관리</a></li> -->
			</ul>
		</li>
		<li>
			<a href="javascript:;">사이트 관리</a>
			
			<ul class="sub_list">
				<li><a href="/admin/slide.do">메인 이미지 슬라이드 관리</a></li>
				<li><a href="/admin/portfolio.do">포트폴리오 게시판 관리</a></li>
				<li><a href="/admin/story.do">스토리 게시판 관리</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:;">사용자 관리</a>
			
			<ul class="sub_list">
				<li><a href="javascript:;">회원관리</a></li>
			</ul>
		</li>
	</ul>
</nav>