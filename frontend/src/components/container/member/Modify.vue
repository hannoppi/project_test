<template>
  <div class="outer_register">
    <div class="register_area">
      <div class="inner_register">
        <div class="hgroup">
            <h3 class="title">회원정보 수정</h3>
            <p class="descript">정보를 수정할 수 있습니다.</p>
        </div><!-- // hgroup -->

        <form name="frm" action="#" method="post">
          <fieldset>
            <legend class="invisible">회원정보 수정</legend>

            <div class="inner_fieldset">
              <div class="inputfield_outer">
                <span>아이디</span>
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <label for="password">패스워드</label>
                <input type="password" name="password" id="password" class="inputfield_global" value="" placeholder="패스워드를 입력해주세요.">
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <label for="passwordCheck">패스워드 재확인</label>
                <input type="password" name="passwordCheck" id="passwordCheck" class="inputfield_global" value="" placeholder="패스워드 확인을 위해 한 번 더 입력해주세요.">
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <label for="quiz">퀴즈</label>
                <input type="text" name="quiz" id="quiz" class="inputfield_global" value="" placeholder="직접 입력해주세요.">
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <span>이름</span>
              </div><!-- // inputfield_outer -->

              <button type="submit" class="button_global button_join">수정</button>
              <a href="#">메인 페이지로 이동</a>
            </div><!-- // inner_fieldset -->
          </fieldset>
        </form>
      </div><!-- // register_area -->
    </div><!-- // inner_register -->
  </div><!-- // outer_register -->
</template>

<script>
export default {
  name: 'modify'
}
</script>
