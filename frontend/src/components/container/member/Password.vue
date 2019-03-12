<template>
  <div class="outer_register">
    <div class="register_area">
      <div class="inner_register">
        <div class="hgroup">
          <h3 class="title">패스워드 찾기</h3>
          <p class="descript">정보를 입력하세요.</p>
        </div><!-- // hgroup -->

        <div class="options">
          <router-link to="/member/register">회원가입</router-link>
          <router-link to="/member/id">아이디 찾기</router-link>
        </div><!-- // options -->

        <form name="frm" action="#" method="post">
          <fieldset>
            <legend class="invisible">회원정보 입력</legend>

            <div class="inner_fieldset">
              <div class="inputfield_outer">
                <label for="id">아이디</label><input type="text" name="id" id="id" class="inputfield_global" value="">
              </div><!-- // inputfield_outer -->

              <div class="inputfield_outer">
                <label for="quiz">2차 패스워드</label><input type="text" name="quiz" id="quiz" class="inputfield_global" value="">
              </div><!-- // inputfield_outer -->

                <div class="button_area">
                  <div class="inner_area">
                    <span class="button_outer"><button type="submit" class="button_global">확인</button></span>
                    <span class="button_outer"><router-link to="/member/login" class="button_global bgc_gray">되돌아가기</router-link></span>
                  </div><!-- // inner_area -->
                </div><!-- // button_area -->
            </div><!-- // inner_fieldset -->
          </fieldset>
        </form>
      </div><!-- // register_area -->
    </div><!-- // inner_register -->
  </div><!-- // outer_register -->
</template>

<script>
import Constant from '../../../Constant'

export default {
  name: 'password',
  mounted () {
    this.$store.commit(Constant.HEADER, true)
  }
}
</script>
