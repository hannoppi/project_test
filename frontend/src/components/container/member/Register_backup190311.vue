<template>
  <div class="outer_register">
    <div class="register_area">
      <div class="inner_register">
        <div class="hgroup">
          <h3 class="title">회원가입</h3>
          <p class="descript">정보를 입력하세요.</p>
        </div><!-- // hgroup -->

        <div class="options">
          <router-link to="/member/id">아이디 찾기</router-link>
          <router-link to="/member/password">패스워드 찾기</router-link>
        </div><!-- // options -->

        <strong class="invisible">회원정보 입력</strong>
        <div class="inner_fieldset">
          <div class="inputfield_outer">
            <label for="id">아이디</label><input type="text" name="id" id="id" class="inputfield_global" v-model="id" v-on:keyup="confirmInit">
            <input type="hidden" name="confirmId" class="inputfield_global" readonly v-bind:value="this.confirmId">
            <button type="button" class="confirmId button_global" v-on:click="confirmId23">아이디 중복확인</button>
          </div><!-- // inputfield_outer -->

          <strong class="invisible">{{ this.$store.state.confirmResult }}아이디 중복확인 결과</strong>
          <p v-if="this.$store.state.confirmResult === 1">{{ this.confirmId }}는 이미 사용 중입니다.</p>
          <p v-else-if="this.$store.state.confirmResult === -1">{{ this.confirmId }}는 사용 가능합니다.</p>

          <div class="inputfield_outer">
            <label for="password">패스워드</label><input type="password" name="password" id="password" class="inputfield_global" v-model="password">
          </div><!-- // inputfield_outer -->

          <div class="inputfield_outer">
            <label for="passwordCheck">패스워드 재확인</label><input type="password" name="passwordCheck" id="passwordCheck" class="inputfield_global" value="">
          </div><!-- // inputfield_outer -->

          <div class="inputfield_outer">
            <label for="quiz">2차 패스워드</label><input type="text" name="quiz" id="quiz" class="inputfield_global" v-model="quiz">
          </div><!-- // inputfield_outer -->

          <div class="inputfield_outer">
            <label for="name">이름</label><input type="text" name="name" id="name" class="inputfield_global" v-model="name">
          </div><!-- // inputfield_outer -->

          <div class="button_area">
            <div class="inner_area">
              <span class="button_outer"><button type="button" class="button_global" v-on:click="register">등록</button></span>
              <span class="button_outer"><router-link to="/member/login" class="button_global bgc_gray">되돌아가기</router-link></span>
            </div><!-- // inner_area -->
          </div><!-- // button_area -->
        </div><!-- // inner_fieldset -->
      </div><!-- // register_area -->
    </div><!-- // inner_register -->
  </div><!-- // outer_register -->
</template>

<script>
import Constant from '../../../Constant'

export default {
  name: 'register',
  data () {
    return { id: this.id, confirmId: '', password: '', quiz: '', name: '' }
  },
  mounted () {
    this.$store.commit(Constant.HEADER, true)
  },
  methods: {
    confirmInit () {
      this.confirmId = ''
      this.$store.state.confirmResult = 0
    },
    confirmId23 () {
      this.$store.dispatch(Constant.CONFIRMID, { id: this.id }).then((response) => {
        console.log('this.id: ', this.id)
        this.confirmId = this.id
      })
    },
    register () {
      let formData = new FormData()

      formData.append('confirmId', this.confirmId)
      formData.append('password', this.password)
      formData.append('quiz', this.quiz)
      formData.append('name', this.name)

      this.$store.dispatch(Constant.REGISTER, formData)
    }
  }
}
</script>
