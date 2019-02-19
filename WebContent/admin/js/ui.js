/* ---------- ---------- ---------- ---------- ----------
홈페이지 (http://www.homepage.com/)
최종 수정일 : 

알림 : 
---------- ---------- ---------- ---------- ---------- */

var UI = (function () {
	var confirmId = function () {
		console.log("(confirmId) 아이디 중복확인을 클릭하였습니다.");
		
		var frm = arguments[0];
		var url = "confirmId.action?id=";
		
		if (frm.id.value === '') {
			alert('사용할 아이디를 입력해주세요.');
			
			return false;
		}
		
		url += frm.id.value;
		console.log("(confirmId) url: ", url);
		
		window.open(url, '_blank', 'toolbar=no, menubar=no, scrollbar=yes, resizable=no, width=480, height=320');
	}; // confirmId
	
	var confirmSuccess = function () {
		console.log("(confirmSuccess) 확인을 클릭하였습니다.");
		
		var id = arguments[0];
		console.log("(confirmSuccess) id: ", id);
		
		opener.document.getElementById('id').value = id; // "${id }";
		opener.document.getElementById('confirmId').value = id; // "${id }";
		
		self.close();
	}; // confirmSuccess
	
	var confirmFrm = function () {
		console.log("(confirmFrm) 등록을 클릭하였습니다.");

		var frm = arguments[0];
		
		if (frm.confirmId.value.length === 0) {
			alert("(confirmFrm) 아이디 중복확인을 해주세요.");
			
			frm.confirmId.focus();
			
			return false;
		}
		
		if (frm.password.value.length === 0) {
			alert("(confirmFrm) 패스워드를 입력해주세요.");
			
			frm.confirmId.focus();
			
			return false;
		} else {
			console.log("(confirmFrm) frm.password.value: ", frm.password.value);
			console.log("(confirmFrm) frm.passwordCheck.value: ", frm.passwordCheck.value);
			
			if (frm.password.value !== frm.passwordCheck.value) {
				alert("(confirmFrm) 입력한 패스워드가 일치하지 않습니다.");
				
				frm.passwordCheck.focus();
				
				return false;
			}
		}
		
		if (frm.quiz.value.length === 0) {
			alert("(confirmFrm) 퀴즈를 입력해주세요.");
			
			frm.quiz.focus();
			
			return false;
		}
		
		if (frm.name.value.length === 0) {
			alert("(confirmFrm) 이름을 입력해주세요.");
			
			frm.name.focus();
			
			return false;
		}
		
		frm.submit();
	}; // confirmFrm
	
	var boardDelete = function () {
		var url = arguments[0];
		console.log("url: ", url);
		
		if (confirm("삭제 후 복구할 수 없습니다.\n정말로 삭제하시겠습니까?")) {
			location.href = url;
		}
	}; // boardDelete
	
	var designSelect = function () {
		var selector = arguments[0].selector, evntTrgt = null, selected = null;
		
		document.querySelector('.design_select select') && document.querySelector('.design_select select').addEventListener('change', function (event) {
			evntTrgt = event.target, selected = evntTrgt[evntTrgt.selectedIndex].text; // text, value
			
			evntTrgt.parentElement.children[1].innerText = selected;
		});
	};
	
	var inputDefaultValue = function () {
		var i,  inputfield = document.querySelectorAll(arguments[0].selector);
		
		for (var i = 0; i < inputfield.length; i++) {
			inputfield[i].addEventListener('focus', function () {
				if (this.value === this.defaultValue) this.previousSibling.style.display = 'none';
			});
			
			inputfield[i].addEventListener('blur', function () {
				if (this.value === '') this.previousSibling.style.display = 'block';
			});
		}
	};
	
	return {
		callConfirmId: function () {
			return confirmId(arguments[0]);
		},
		callConfirmSuccess: function () {
			return confirmSuccess(arguments[0]);
		},
		callConfirmFrm: function () {
			return confirmFrm(arguments[0]);
		},
		callBoardDelete: function () {
			return boardDelete(arguments[0]);
		},
		callDesignSelect: function () {
			return designSelect(arguments[0]);
		},
		callInputDefaultValue: function () {
			return inputDefaultValue(arguments[0]);
		}
	}
}()); // UI

/*
window.onload = function () {
	;(function ($) {
		$('.option_choice').on('change', function () {
			var selected = $(this).children('option:selected').text();
			console.log("[ui.js] selected:" , selected);
			
			$(this).siblings('.label_choice').find('.text_choice').text(selected);
		});
	}(jQuery));
};
*/