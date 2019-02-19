<template>
  <div id="app" v-bind:class="{main: this.$store.state.header}">
    <accessibility></accessibility>

    <wrapper></wrapper>
  </div><!-- // app -->
</template>

<script>
import Accessibility from './components/Accessibility.vue'
import Wrapper from './components/Wrapper.vue'
// import Paginate from 'vuejs-paginate'

export default {
  name: 'App',
  // computed: {
  //   pageCheck () {
  //     return this.$store.state.member
  //   }
  // },
  components: {
    'accessibility': Accessibility,
    'wrapper': Wrapper
    // 'paginate': Paginate
  }
}
</script>

<style>
  @import './assets/css/common.css';

  @import 'quill/dist/quill.core.css';
  @import 'quill/dist/quill.snow.css';
  @import 'quill/dist/quill.bubble.css';
</style>
