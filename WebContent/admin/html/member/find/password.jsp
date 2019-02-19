<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>패스워드 찾기 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body class="main">
	<%@ include file="/admin/html/session/login.jsp" %>
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<div class="outer_register">
			<div class="register_area">
				<div class="inner_register">
					<div class="hgroup">
						<h3 class="title">패스워드 찾기</h3>
						<p class="descript">정보를 입력하세요.</p>
					</div><!-- // hgroup -->
					
					<div class="options">
						<a href="/admin/register.do">회원가입</a>
						<a href="/admin/find/id.do">아이디 찾기</a>
					</div><!-- // options -->
					
					<form name="frm" action="/admin/member/password/change.do" method="post">
						<fieldset>
							<legend class="invisible">회원정보 입력</legend>
							
							<div class="inner_fieldset">
								<div class="inputfield_outer">
									<label for="id">아이디</label><input type="text" name="id" id="id" class="inputfield_global" value="" /><!-- // placeholder="찾고자 하는 아이디를 입력해주세요." -->
								</div><!-- // inputfield_outer -->
								
								<div class="inputfield_outer">
									<label for="quiz">2차 패스워드</label><input type="text" name="quiz" id="quiz" class="inputfield_global" value="" /><!-- // placeholder="직접 입력해주세요." -->
								</div><!-- // inputfield_outer -->
								
								<div class="button_area">
									<div class="inner_area">
										<span class="button_outer"><button type="submit" class="button_global">확인</button></span>
										<span class="button_outer"><a href="/admin/login.do" class="button_global bgc_gray">되돌아가기</a></span>
									</div><!-- // inner_area -->
								</div><!-- // button_area -->
							</div><!-- // inner_fieldset -->
						</fieldset>
					</form>
				</div><!-- // inner_register -->
			</div><!-- // register_area -->
		</div><!-- // outer_register -->
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>