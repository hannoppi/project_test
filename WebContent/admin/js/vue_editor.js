document.addEventListener('DOMContentLoaded', function () {
	var oEditors = [];

	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "content",
	    sSkinURI: "/api/admin/naver/se2/SmartEditor2Skin.html",
	    fCreator: "createSEditor2"
	});
});