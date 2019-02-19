<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="board_comment">
	<ul class="comment_order">
		<li>댓글 <em>0</em></li>
		<li><button type="button" class="button_like">좋아요</button> <em>0</em></li>
	</ul>
	
	<strong class="invisible">댓글 입력</strong>
	<ul class="comment_write">
		<li class="invisible">게시물 번호(board): <input type="text" name="board" value="${boardView.number }" /></li>
		<li class="invisible">댓글 번호(number): auto</li>
		<li class="invisible">아이디(id): <input type="text" name="id" value="<c:out value="${user.id }" />" readonly /></li>
		<li class="invisible">작성자(name): <input type="text" name="name" value="<c:out value="${user.name }" />" readonly /></li>
		<li><span class="invisible">내용(content): </span><span class="textfield_outer"><textarea name="comment" class="textfield_global">첫 번째 댓글입니다.</textarea></span></li>
		<li class="invisible">댓글 부모(reference): <input type="text" name="reference" value="0" readonly /></li>
		<li class="invisible">댓글 깊이(level): <input type="text" name="level" value="0" readonly /></li>
		<li class="invisible">댓글 순서(sequence): auto</li>
		<li class="invisible">관계(together): auto</li>
	</ul>
	
	<input type="submit" value="등록" class="button_global" />
</div><!-- // board_comment -->

<div class="comment_group">
	<c:choose>
		<c:when test="${not empty commentList }">
			<c:forEach var="commentList" items="${commentList }">
				<div class="comment_article item${commentList.together }" style="margin-left:${commentList.level * 100}px;">
					<div class="comment_information">
						<span class="invisible">게시물 번호(board): <em class="commentBoard"><c:out value="${commentList.board }" /></em></span>
						<span class="invisible number">댓글 번호(number): <em class="commentNumber"><c:out value="${commentList.number }" /></em></span>
						<span class="invisible">아이디(id): <em class="commentId"><c:out value="${commentList.id }" /></em></span>
						<span><span class="invisible">작성자(name): </span><em class="commentName"><c:out value="${commentList.name }" /></em></span>
						<span class="invisible">댓글 부모(reference): <em class="commentReference"><c:out value="${commentList.reference }" /></em></span>
						<span class="invisible">댓글 깊이(level): <em class="commentLevel"><c:out value="${commentList.level }" /></em></span>
						<span class="invisible">댓글 순서(sequence): <em class="commentSequence"><c:out value="${commentList.sequence }" /></em></span>
						<span class="invisible">관계(together): <em class="commentTogether"><c:out value="${commentList.together }" /></em></span>
						<span><span class="invisible">등록일(regdate): </span><em class="commentRegdate"><fmt:formatDate value="${commentList.regdate }" type="date" /></em></span>
					</div><!-- // comment_information -->
					
					<div class="comment_content">
						<span class="invisible">내용(content): </span><em class="commentContent"><c:out value="${commentList.content }" /></em>
					</div><!-- // comment_content -->
					
					<!-- <button type="button" class="button_activate">답변</button> -->
					<div class="invisible wrap_activate"></div><!-- // wrap_activate -->
				</div><!-- // comment_article -->
			</c:forEach>
		</c:when>
	</c:choose>
</div><!-- // comment_group -->