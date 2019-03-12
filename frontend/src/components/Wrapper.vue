<template>
  <div id="wrapper">
    <hdr v-if="this.$store.state.header === false"></hdr>

    <container></container>

    <ftr></ftr>
  </div><!-- // wrapper -->
</template>

<script>
import Hdr from './header/Header.vue'
import Container from './container/Container.vue'
import Ftr from './footer/Footer.vue'

export default {
  name: 'wrapper',
  components: { 'hdr': Hdr, 'container': Container, 'ftr': Ftr }
}
</script>
