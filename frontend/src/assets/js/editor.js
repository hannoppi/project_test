document.addEventListener('DOMContentLoaded', function () {
    var oEditors = [];

    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "content",
        sSkinURI: "SmartEditor2Skin.html",
        fCreator: "createSEditor2"
    });

    document.querySelector('.button_smarteditor').addEventListener('click', function (event) {
        event.preventDefault();

        oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

        // 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리합니다.
        try {
            document.frm.submit(); // 성공적으로 전송하였습니다.
        } catch(exception) {
            console.log("[editor.js] exception: ", exception);
        }
    });
});