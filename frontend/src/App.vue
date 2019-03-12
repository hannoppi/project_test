<template>
  <div id="app" v-bind:class="{ main: this.$store.state.header }">
    <accessibility></accessibility>

    <wrapper></wrapper>
  </div><!-- // app -->
</template>

<script>
import Accessibility from './components/Accessibility.vue'
import Wrapper from './components/Wrapper.vue'

export default {
  name: 'App',
  components: {
    'accessibility': Accessibility,
    'wrapper': Wrapper
  }
}
</script>

<style>
  @import './assets/css/common.css';

  @import 'quill/dist/quill.core.css';
  @import 'quill/dist/quill.snow.css';
  @import 'quill/dist/quill.bubble.css';
</style>
