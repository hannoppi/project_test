<%@page import="java.util.List"%>
<%@page import="dto.ImageSlideVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/html/include/doctype.jsp" %>
<title>메인 이미지 슬라이드 관리 &lt; 사이트 관리 &lt; 홈페이지 관리자</title>
<%@ include file="/admin/html/include/stylesheet.jsp" %>
<%@ include file="/admin/html/include/script.jsp" %>
</head>
<body>
	<%@ include file="/admin/html/session/authentication.jsp" %>
	
	<%@ include file="/admin/html/include/accessibility.jsp" %>
	
	<%@ include file="/admin/html/include/header.jsp" %>
	
	<%@ include file="/admin/html/include/container.jsp" %>
		<section id="contents">
			<%@ include file="/admin/html/slide/include/hgroup.jsp" %>

			<button type="button" class="button_addition">추가</button>
			
            <form name="frm" action="/admin/slide.action" method="post" enctype="multipart/form-data">
                <div class="wrap_thumbnail">
                    <c:choose>
                        <c:when test="${not empty imageSlideList }">
                            <c:forEach var="imageSlideList" items="${imageSlideList }" varStatus="status">
                                <div class="area_thumbnail">
                                    <div class="group_thumbnail">
                                        <div class="box_thumbnail">
                                        	<input type="checkbox" name="check" class="inp_comm" value="<c:out value="${imageSlideList.number }" />" />
                                        	
                                            <img src="/admin/images/common/dummy_80x45.gif" alt="" class="thumbnail_dummy" />
                                            <img src="/admin/upload/slide/main/images/<c:out value="${imageSlideList.file }" />" alt="<c:out value="${imageSlideList.subject }" />" class="thumbnail_data" />
                                        </div><!-- // box_thumbnail -->
                                        
                                        <div class="upload_thumbnail">
                                            <input type="text" name="existingFile" value="<c:out value="${imageSlideList.file }" />" />
                                            <input type="file" name="file${imageSlideList.number }" value="<c:out value="${imageSlideList.file }" />" />
                                            <input type="text" name="number" value="<c:out value="${imageSlideList.number }" />" class="inputfield_global" />
                                        </div><!-- // upload_thumbnail -->
                                    </div><!-- // group_thumbnail -->
                                    
                                    <div class="information_thumbnail">
                                        <div class="box_inputfield">
                                            <div class="select_global">
                                                <strong class="screen_out">카테고리 선택</strong>
                                                
                                                <label for="selectCategory" class="label_choice">
                                                    <span class="text_choice">홈페이지</span>
                                                    <span class="screen_out">선택</span>
                                                </label>
                                                
                                                <select name="option" id="selectCategory" class="option_choice">
                                                    <option value="subject" selected>홈페이지</option>
                                                </select>
                                            </div><!-- // select_global -->
                                            
                                            <input type="text" name="subject" class="inputfield_global" value="<c:out value="${imageSlideList.subject }" />" />
                                        </div><!-- // box_inputfield -->
                                        
                                        <textarea name="content" class="textfield_global"><c:out value="${imageSlideList.content }" /></textarea>
                                    </div><!-- // information_thumbnail -->
                                </div><!-- // area_thumbnail -->
                            </c:forEach>
                        </c:when>
                    
                        <c:otherwise>
                            <p class="emphasis_message">이미지 슬라이드가 존재하지 않습니다.</p>
                        </c:otherwise>
                    </c:choose>
                </div><!-- // wrap_thumbnail -->
                
                <div class="wrap_button">
                    <input type="submit" class="button_global style_submit" data-submit-type="modify" value="수정" />
                    <input type="submit" class="button_global style_submit" data-submit-type="write" value="등록" />
                </div><!-- // wrap_button -->
            </form>
		</section><!-- // contents -->
	<%@ include file="/admin/html/include/footer.jsp" %>
	
	<script>
		;(function () {
			$(function () {
				function frmSubmit(selector, type) {
					console.log("[slide] [list.jsp] selector: ", selector);
					console.log("[slide] [list.jsp] selector.getAttribute('action'): ", selector.getAttribute('action'));
					
					if (type === 'write') {
						console.log("[slide] [list.jsp] 등록입니다.");
						selector.action = selector.getAttribute('action') + '?where=write';
					}
					
					if (type === 'modify') {
						console.log("[slide] [list.jsp] 수정입니다.");
						selector.action = selector.getAttribute('action') + '?where=modify';
					}
					
					selector.submit();
				}
				
				$('form[name = "frm"]').on('submit', function (event) {
					event.preventDefault();
					
					console.log("[slide] [list.jsp] form submit를 감지하였습니다.");
				});
				
				$('.button_global.style_submit').on('click', function () {
					console.log("[slide] [list.jsp] 클릭을 감지하였습니다.");
					
					var frm = document.getElementsByName('frm')[0];
					console.log("[slide] [list.jsp] frm: ", frm);
					
					var actionType = this.getAttribute('data-submit-type');
					console.log("[slide] [list.jsp] actionType: ", actionType);
					
					frmSubmit(frm, actionType);
				});
				
				var count = $('input[name="number"]').length;
				console.log("[slide] [list.jsp] count: ", count);
		
				$('.button_addition').on('click', function () {
					count++;
					console.log("[slide] [list.jsp] count: ", count);
					
					var html = '';
					
					html += '<div class="area_thumbnail">';
					html += '<div class="group_thumbnail">';
					html += '<div class="box_thumbnail">';
					html += '<img src="/admin/images/common/dummy_80x45.gif" alt="" class="thumbnail_dummy" />';
					html += '<img class="thumbnail_data" />';
					html += '</div><!-- // box_thumbnail -->';

					html += '<div class="upload_thumbnail">';
					html += '<input type="text" name="go" value="' + count  + '" />';
					html += '<input type="file" name="upload_file' + count  +'" />';
					html += '<input type="text" name="number" value="' + count  + '" class="inputfield_global" />';
					html += '</div><!-- // upload_thumbnail -->';
					html += '</div><!-- // group_thumbnail -->';

					html += '<div class="information_thumbnail">';
					html += '<div class="box_inputfield">';
					html += '<div class="select_global">';
					html += '<strong class="screen_out">카테고리 선택</strong>';

					html += '<label for="selectCategory" class="label_choice">';
					html += '<span class="text_choice">홈페이지</span>';
					html += '<span class="screen_out">선택</span>';
					html += '</label>';

					html += '<select name="option" id="selectCategory" class="option_choice">';
					html += '<option value="subject" selected>홈페이지</option>';
					html += '</select>';
					html += '</div><!-- // select_global -->';

					html += '<input type="text" name="subject" class="inputfield_global" />';
					html += '</div><!-- // box_inputfield -->';

					html += '<textarea name="content" class="textfield_global"></textarea>';
					html += '</div><!-- // information_thumbnail -->';
					html += '</div><!-- // area_thumbnail -->';
		
					$('.wrap_thumbnail').append(html);
				});
			});
		}());
	</script>
</body>
</html>