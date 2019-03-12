<template>
  <div class="outer_login">
    <section class="login_area">
      <div class="inner_login">
        <hgr title="관리자 로그인" descript="홈페이지 관리자 시스템입니다."></hgr>

        <options></options>

        <div class="inner_fieldset">
          <div class="inputfield_outer">
            <label for="id" class="invisible">아이디</label>
            <input type="text" name="id" id="id" class="inputfield_global" placeholder="아이디를 입력해주세요." v-model="id">
          </div><!-- // inputfield_outer -->

          <em v-if="this.message !== ''">{{ this.message }}</em>

          <div class="inputfield_outer">
            <label for="password" class="invisible">패스워드</label>
            <input type="password" name="password" id="password" class="inputfield_global" placeholder="패스워드를 입력해주세요." v-model="password">
          </div><!-- // inputfield_outer -->

          <button type="button" class="button_global" v-on:click="login">로그인</button>
        </div><!-- // inner_fieldset -->
      </div><!-- // inner_login -->
    </section><!-- // login_area -->
  </div><!-- // outer_login -->
</template>

<script>
import hgr from './Hgroup.vue'
import Options from './Options.vue'
import Constant from '../../../Constant'

export default {
  name: 'login',
  components: { 'hgr': hgr, 'options': Options },
  data () {
    return { id: '', password: '', message: '' }
  },
  mounted () {
    this.$store.commit(Constant.HEADER, true)
  },
  methods: {
    login () {
      let formData = new FormData()

      formData.append('id', this.id)
      formData.append('password', this.password)

      this.$store.dispatch(Constant.LOGIN, formData).then((response) => {
        if (response === -1) this.message = '아이디가 존재하지 않습니다.'
        if (response === 0) this.message = '패스워드가 일치하지 않습니다.'
      })
    }
  }
}
</script>
