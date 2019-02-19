document.addEventListener('DOMContentLoaded', function () {
	console.log("DOMContentLoaded");
	
	UI.callDesignSelect({selector: '#selectCategory'});
	
	UI.callInputDefaultValue({selector: '.inputfield_global'});
	
	document.querySelector('.confirmId') && document.querySelector('.confirmId').addEventListener('click', function () {
		var register = document.register;
		console.log("[test.js] 회원가입: 아이디 중복확인 register: ", register);
		
		UI.callConfirmId(register); // 회원가입: 아이디 중복확인
	}); // confirmId
	
	
	document.querySelector('form[name = "register"]') && document.querySelector('form[name = "register"]').addEventListener('submit', function (event) {
		event.preventDefault();
		
		var register = document.register;
		console.log("[test.js] 회원가입: 등록 register: ", register);
		
		UI.callConfirmFrm(register); // 회원가입: 등록
	}); // confirmFrm
	
	
	document.querySelector('.button_close') && document.querySelector('.button_close').addEventListener('click', function (event) {
		this.ownerDocument.getElementsByClassName('layer_wrapper')[0].setAttribute('class', 'layer_wrapper')
	});
	
	document.querySelector('.link_thumbnail') && document.querySelector('.link_thumbnail').addEventListener('click', function (event) {
		/*
		var layer = document.createElement('div');
		layer.className = 'background_layer';
		document.body.appendChild(layer);
		*/
		
		var imgSrc = event.target.src;
		
		document.querySelector('.layer_wrapper').classList.add('open');
		document.querySelector('.layer_wrapper').querySelector('.img_preview').setAttribute('src', imgSrc);
		document.querySelector('.background_layer').classList.add('open');
	});
	
	document.querySelector('.button_layer_close') && document.querySelector('.button_layer_close').addEventListener('click', function () {
		document.querySelector('.layer_wrapper').classList.remove('open');
		document.querySelector('.background_layer').classList.remove('open');
		document.querySelector('.layer_wrapper').querySelector('.img_preview').removeAttribute('src');
	});
	
	document.querySelector('.board_comment .button_global') && document.querySelector('.board_comment .button_global').addEventListener('click', function () {
		var query = {
			board: document.querySelector('.comment_write input[name = "board"]').value,
			// number: document.querySelector('.comment_write input[name = "number"]').value,
			id: document.querySelector('.comment_write input[name = "id"]').value,
			name: document.querySelector('.comment_write input[name = "name"]').value,
			content: document.querySelector('.comment_write textarea[name = "comment"]').value,
			reference: document.querySelector('.comment_write input[name = "reference"]').value,
			level: document.querySelector('.comment_write input[name = "level"]').value,
			// sequence: document.querySelector('.comment_write input[name = "sequence"]').value,
			// together: document.querySelector('.comment_write input[name = "together"]').value
		};
		
		console.log("[test.js] (button_comment) query.board: ", query.board);
		// console.log("[test.js] (button_comment) query.number: ", query.number);
		console.log("[test.js] (button_comment) query.id: ", query.id);
		console.log("[test.js] (button_comment) query.name: ", query.name);
		console.log("[test.js] (button_comment) query.content: ", query.content);
		console.log("[test.js] (button_comment) query.reference: ", query.reference);
		console.log("[test.js] (button_comment) query.level: ", query.level);
		// console.log("[test.js] (button_comment) query.sequence: ", query.sequence);
		// console.log("[test.js] (button_comment) query.together: ", query.together);
		
		var xhr = new XMLHttpRequest();
		
		xhr.open('POST', '/admin/board/comment.action');
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.send('board=' + query.board + '&id=' + query.id + '&name=' + query.name + '&content=' + query.content + '&reference=' + query.reference + '&level=' + query.level);
		
		xhr.onreadystatechange = function () {
			console.log("onreadystatechange");
			
			console.log("xhr.readyState: ", xhr.readyState);
			if (xhr.readyState !== 4) {
				// readyState 4: 완료
				return;
			}
			
			console.log("xhr.status: ", xhr.status);
			if (xhr.status === 200) {
				// status 200: 성공
				console.log("xhr.responseText: ", xhr.responseText);
				
				var data = JSON.parse(xhr.responseText);
				console.log("data: ", data);
				
				var result = data.result,
				html = "",
				i,
				j,
				together;
				
				console.log("result: ", result);
				
				if (result !== undefined) {
					for (i = 0; i < result.length; i++) {
						html += '<div class="comment_article item">';
						
						for (j = 0; j < result[i].value.length; j++) {
							togerther = result[j].value[j].together;
							console.log("togerther: ", togerther);
							
							html += '<div class="comment_information">';
							html += '<span class="invisible">게시물 번호(board): <em class="commentBoard">' + result[j].value[j].board + '</em></span>';
							html += '<span class="invisible number">댓글 번호(number): <em class="commentNumber">' + result[j].value[j].number + '</em></span>';
							html += '<span class="invisible">아이디(id): <em class="commentId">' + result[j].value[j].id + '</em></span>';
							html += '<span><span class="invisible">작성자(name): </span>' + result[j].value[j].name + '</em></span>';
							html += '<span class="invisible">댓글 부모(reference): <em class="commentReference">' + result[j].value[j].reference + '</em></span>';
							html += '<span class="invisible">댓글 깊이(level): <em class="commentLevel">' + result[j].value[j].level + '</em></span>';
							html += '<span class="invisible">댓글 순서(sequence): <em class="commentSequence">' + result[j].value[j].sequence + '</em></span>';
							html += '<span class="invisible">관계(together): <em class="commentTogether">' + result[j].value[j].together + '</em></span>';
							html += '<span><span class="invisible">등록일(regdate): </span><em class="commentRegdate">' + result[j].value[j].regdate + '</em></span>';
							html += '</div><!-- // comment_information -->';
							
							html += '<div class="comment_content">';
							html += '<span class="invisible">내용(content): </span><em class="commentContent">' + result[j].value[j].content + '</em></span>';
							html += '</div><!-- // comment_content -->';
							
							html += '<!-- <button type="button" class="button_activate">답변</button> -->';
							html += '<div class="invisible wrap_activate"></div>';
						}
						html += '</div><!-- // comment_article -->';
					}
					
					console.log("html: ", html);
					
					var div = document.createElement('div');
					div.className = 'comment_article';
					
					div.innerHTML = html;
					
					document.querySelector('.comment_group').appendChild(div);
					// document.querySelector('.comment_group').innerHTML = html;
					// $comment.find('.item').attr('class', 'item' + togerther);
				}
				
			} else {
				console.log("xhr.status: ", xhr.status);
				console.log("xhr.statusText: ", xhr.statusText);
			}
		};
	});
});