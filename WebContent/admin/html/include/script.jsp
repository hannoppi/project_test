<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<c:if test="${not empty smarteditor }">
	<script src="/admin/naver/se2/js/service/HuskyEZCreator.js"></script>
	<script src="/admin/js/editor.js"></script>
</c:if>
<script src="/admin/js/ui.js"></script>
<script src="/admin/js/test.js"></script>
<!--
<script>
	// textArea에 이미지를 첨부합니다.
	function pasteHTML(filepath){
	    var sHTML = '<img src="<!-- %=request.getContextPath() --%>/path에서 설정했던 경로/'+filepath+'">';
	    oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
	}
</script>
-->