<template>
  <section id="container">
    <h2 class="invisible">본문 시작</h2>

    <router-view />
  </section><!-- // container -->
</template>

<script>
// components/container/Container.vue

import NoticeList from './notice/NoticeList.vue'
import NoticeWrite from './notice/NoticeWrite.vue'
import NoticeView from './notice/NoticeView.vue'
import NoticeModify from './notice/NoticeModify.vue'

export default {
  name: 'container',
  components: { NoticeList, NoticeWrite, NoticeView, NoticeModify }
}
</script>
