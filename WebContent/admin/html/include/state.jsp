<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<ul class="utility_list">
	<c:choose>
		<c:when test="${!empty user }">
			<li><a href="/admin/logout.action">로그아웃</a></li>
			<li><a href="/admin/member/modify.do">회원정보 수정</a></li>
		</c:when>
		
		<c:otherwise>
			<li><a href="/admin/login.do">로그인</a></li>
			<li><a href="/admin/register.do">회원가입</a></li>
			<li><a href="/admin/find/id.do">아이디 찾기</a></li>
			<li><a href="/admin/find/password.do">패스워드 찾기</a></li>
		</c:otherwise>
	</c:choose>
</ul>