<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>아이디 찾기 &lt; 홈페이지 관리자</title>
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
						<h3 class="title">아이디 찾기</h3>
						<p class="descript">정보를 입력하세요.</p>
					</div><!-- // hgroup -->
					
					<div class="options">
						<a href="/admin/register.do">회원가입</a>
						<a href="/admin/find/password.do">패스워드 찾기</a>
					</div><!-- // options -->
					
					<form name="frm" action="/admin/findId.action" method="post">
						<fieldset>
							<legend class="invisible">회원정보 입력</legend>
							
							<div class="inner_fieldset">
								<div class="inputfield_outer">
									<label for="name">이름</label><input type="text" name="name" id="name" class="inputfield_global" value="" /><!-- // placeholder="이름을 입력해주세요." -->
								</div><!-- // inputfield_outer -->
								
								<div class="inputfield_outer">
									<label for="birthday">생년월일</label><input type="number" name="birthday" id="birthday" class="inputfield_global" value="" /><!-- // placeholder="'-' 없이 입력해주세요. 예를 들어, 19700101" -->
								</div><!-- // inputfield_outer -->
								
								<div class="inputfield_outer">
									<div class="design_select">
										<strong class="invisible">통신사 선택</strong>
										
										<label for="selectPhone">휴대폰 번호</label>
										
										<select id="selectPhone" name="service">
											<option value="" selected>-- 선택 --</option>
											<option value="skt">SKT</option>
											<option value="kt">KT</option>
											<option value="lg">LG</option>
										</select>
									</div><!-- // design_select -->
									
									<input type="text" name="phone" id="phone" class="inputfield_global" value="" placeholder="'-' 없이 입력해주세요. 예를 들어, 01012345678" />
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