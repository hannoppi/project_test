<template>
  <nav id="gnb">
    <h2 class="invisible">전체 메뉴 시작</h2>

    <ul class="menu_list">
      <li class="current">
        <a href="#">게시판 관리</a>

        <ul class="sub_list">
          <li v-bind:class="menu('notice')"><router-link v-bind:to="{ name: 'NoticeList' }">공지사항 게시판 관리</router-link></li>
          <li v-bind:class="menu('board')"><router-link v-bind:to="{ name: 'BoardList' }">일반 게시판 관리</router-link></li>
          <li v-bind:class="menu('gallery')"><router-link v-bind:to="{ name: 'GalleryList' }">이미지 게시판 관리</router-link></li>
          <li v-bind:class="menu('download')"><router-link v-bind:to="{ name: 'DownloadList' }">다운로드 게시판 관리</router-link></li>
        </ul>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'gnb',
  methods: {
    menu (name) {
      if (this.$store.state.gnb.menu === name) {
        return { current: true }
      } else {
        return { current: false }
      }
    }
  }
}
</script>
