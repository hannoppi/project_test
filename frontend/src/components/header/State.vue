<template>
    <ul class="utility_list" v-if="this.state !== null">
      <li><router-link to="/" @click.native="logout">로그아웃</router-link></li>
      <li><router-link v-bind:to="{name: 'Modify'}">회원정보 수정</router-link></li>
    </ul>
    <ul class="utility_list" v-else>
      <li><router-link v-bind:to="{name: 'Login'}">로그인</router-link></li>
      <li><router-link v-bind:to="{name: 'Register'}">회원가입</router-link></li>
      <li><router-link v-bind:to="{name: 'Id'}">아이디 찾기</router-link></li>
      <li><router-link v-bind:to="{name: 'Password'}">패스워드 찾기</router-link></li>
    </ul>
</template>

<script>
// components/header/State.vue

export default {
  name: 'state',
  data () {
    return { state: '' }
  },
  mounted () {
    this.state = localStorage.getItem('auth_token')
    console.log('(State.vue) this.state: ', this.state)
  },
  methods: {
    logout () {
      this.state = null
      localStorage.clear()

      console.log('*** (State.vue) this.state: ', this.state)
      console.log('*** (State.vue) localStorage.getItem(\'auth_token\'): ', localStorage.getItem('auth_token'))
      console.log('*** (State.vue) localStorage.getItem(\'id\'): ', localStorage.getItem('id'))
      console.log('*** (State.vue) localStorage.getItem(\'name\'): ', localStorage.getItem('name'))
    }
  }
}
</script>
