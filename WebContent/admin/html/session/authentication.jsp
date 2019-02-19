<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${empty user }">
	<script type="text/javascript">
		alert("비정상적인 접근입니다. 로그인해주세요.");
		
		location.href = '/admin/login.do';
	</script>
</c:if>