<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>아이디 중복확인 &lt; 회원가입 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
<script>
	document.addEventListener('DOMContentLoaded', function () {
		document.querySelector('.success') && document.querySelector('.success').addEventListener('click', function () {
			UI.callConfirmSuccess('${id }'); // 회원가입: 아이디 중복확인 성공
		}); // confirmSuccess
	});
</script>
</head>
<body>
	<h1>아이디 중복확인</h1>
	
	<form action="/admin/confirmId.action" method="get">
		<fieldset>
			<legend class="invisible">아이디 중복확인</legend>
			
			<label for="id">아이디</label>
			<input type="text" name="id" id="id" class="inputfield_global" value="" placeholder="아이디" />
			
			<input type="submit" value="아이디 중복확인" />
		</fieldset>
	</form>
	
	<strong class="invisible">아이디 중복확인 결과</strong>
	
	<c:if test="${result == 1 }">
		<em><c:out value="${id }" /></em>는 이미 사용 중입니다.
	</c:if>
	
	<c:if test="${result == -1 }">
		<em><c:out value="${id }" /></em>는 사용 가능합니다.
		
		<button type="button" class="success">확인</button>
	</c:if>
</body>
</html>