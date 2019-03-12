<template>
  <section>
    <h1>아이디 중복확인</h1>

    <strong class="invisible">아이디 중복확인</strong>

    <label for="id">아이디</label>
    <input type="text" name="id" id="id_test" class="inputfield_global" placeholder="아이디" v-model="id">

    <button type="button" v-on:click="sample">아이디 중복확인</button>

    <strong class="invisible">아이디 중복확인 결과</strong>

    <div v-if="this.$store.state.result > -1"><em>{{ this.id }}</em>는 이미 사용 중입니다.</div>

    <div v-else>
      <em>{{ this.id }}</em>는 사용 가능합니다.

      <button type="button" v-on:click="success">확인</button>
    </div>
  </section>
</template>

<script>
import Constant from '../../../Constant'

export default {
  name: 'confirmId',
  data () {
    return { id: this.$route.query.id }
  },
  mounted () {
    this.$store.commit(Constant.HEADER, true)
  },
  methods: {
    confirmId () {
      this.$store.dispatch(Constant.CONFIRMID, { id: this.id })
    },
    sample () {
      this.confirmId()
    }
  }
}
</script>
