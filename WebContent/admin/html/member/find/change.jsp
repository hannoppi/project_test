<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>패스워드 변경 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body>
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<div class="hgroup">
		    <h3 class="title">패스워드 변경</h3>
		</div><!-- // hgroup -->
		
		<form name="frm" action="/admin/member/password/change.action" method="post">
			<input type="text" name="id" value="<c:out value="${user.id }" />" />
			
			<fieldset>
				<legend class="invisible">패스워드 변경</legend>
				
				<div class="inputfield_outer">
					<label for="password">패스워드</label>
					<input type="password" name="password" id="password" class="inputfield_global" value="" placeholder="패스워드를 입력해주세요." />
				</div><!-- // inputfield_outer -->
				
				<div class="inputfield_outer">
					<label for="passwordCheck">패스워드 재확인</label>
					<input type="password" name="passwordCheck" id="passwordCheck" class="inputfield_global" value="" placeholder="패스워드 확인을 위해 한 번 더 입력해주세요." />
				</div><!-- // inputfield_outer -->
				
				<button type="submit" class="button_global">수정</button>
				<a href="/admin/login.do">취소</a>
			</fieldset>
		</form>
	<%@ include file="/admin/html/include/footer.jsp" %>
</body>
</html>