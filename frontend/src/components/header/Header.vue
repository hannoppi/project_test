<template>
  <header id="header">
    <h1><a href="/">JAVA/Vue.js</a></h1>

    <state></state>

    <gnb></gnb>
  </header>
</template>

<script>
// components/header/Header.vue

import Gnb from './gnb.vue'
import State from './state.vue'

export default {
  name: 'hdr',
  components: { 'gnb': Gnb, 'state': State }
}
</script>
