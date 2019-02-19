<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>관리자 로그인 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body class="main">
	<%@ include file="/admin/html/session/login.jsp" %>
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>

	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<div class="outer_login">
			<section class="login_area">
				<div class="inner_login">
					<div class="hgroup">
						<h3 class="title">관리자 로그인</h3>
						<p class="descript">홈페이지 관리자 시스템입니다.</p>
					</div><!-- // hgroup -->
					
					<div class="options">
						<a href="/admin/register.do">회원가입</a>
						<a href="/admin/find/id.do">아이디 찾기</a>
						<a href="/admin/find/password.do">패스워드 찾기</a>
					</div><!-- // options -->
					
					<form name="frm" action="/admin/login.action" method="post">
						<fieldset>
							<legend class="invisible">관리자 페이지 로그인</legend>
							
							<div class="inner_fieldset">
								<div class="inputfield_outer">
									<label for="id" class="invisible">아이디</label><input type="text" name="id" id="id" class="inputfield_global" placeholder="아이디를 입력해주세요." value="" />
								</div><!-- // inputfield_outer -->
								
								<div class="inputfield_outer">
									<label for="password" class="invisible">패스워드</label><input type="password" name="password" id="password" class="inputfield_global" placeholder="패스워드를 입력해주세요." value="" />
								</div><!-- // inputfield_outer -->
								
								<button type="submit" class="button_global">로그인</button>
							</div><!-- // inner_fieldset -->
						</fieldset>
					</form>
				</div><!-- // inner_login -->
			</section><!-- // login_area -->
		</div><!-- // outer_login -->
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>