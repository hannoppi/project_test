<template>
  <section id="contents">
    <Hgr title="이미지 게시판 목록" descript="이곳에서 이미지 게시판 목록을 확인할 수 있습니다."></Hgr>

    <strong class="invisible">카테고리 선택</strong>

    <ul class="category_area">
      <li><a href="#" class="current">전체</a></li>
      <li><a href="#">일반</a></li>
    </ul><!-- // category_area -->

    <div class="gallery_area">
      <ul class="result_list" v-if="gallerylist.length !== 0">
        <li>-</li>
      </ul>

      <ul class="result_list" v-else>
        <li>게시물이 존재하지 않습니다.</li>
      </ul>
    </div><!-- // gallery_area -->

    <div class="paging" v-if="gallerylist.length !== 0">
      <span class="previous" v-if="paging.currentPage <= 1">이전</span>
      <a href="#" class="previous" v-else v-on:click="galleryList">이전</a>

      <div class="inner_paging">
        <router-link v-bind:to="{name: 'GalleryList', params: {number: index}}" v-for="index in paging | paging.endPage" v-bind:key="index" v-bind:class="current (index)" v-on:click.native="galleryList(index)">{{ index }}<span class="invisible" v-if="paging.currentPage === index">현재 페이지</span></router-link>
      </div><!-- // inner_paging -->

      <span class="next" v-if="paging.currentPage >= paging.maxPage">다음</span>
      <a href="#" class="next" v-else v-on:click="galleryList">다음</a>
    </div><!-- // paging -->

    <div class="board_search">
      <div class="design_select">
        <strong class="invisible">검색 조건 선택</strong>

        <label for="selectCategory">제목</label>

        <select id="selectCategory" name="option" v-on:change="selectCategory">
          <option value="subject" selected>제목</option>
          <option value="content">내용</option>
          <option value="write">작성자</option>
        </select>
      </div><!-- // design_select -->

      <span class="inputfield_outer"><input type="text" name="keyword" class="inputfield_keyword" v-model="keyword" /></span>
      <a href="#" class="button_global" v-on:click="galleryList(null)">검색</a>
    </div><!-- // board_search -->

    <div class="board_gravity">
      <div class="inner_gravity">
        <router-link v-bind:to="{name: 'GalleryWrite'}" class="button_global">등록</router-link>
      </div><!-- // inner_gravity -->
    </div><!-- // board_gravity -->
  </section><!-- // contents -->
</template>

<script>
// Vue Instant Life Cycle
// beforeCreate → created → beforeMount → mounted → beforeUpdate → updated → beforeDestroy → destroyed

import Constant from '../../../Constant.js'
import Hgr from '../member/Hgroup.vue'

import { mapState } from 'vuex'

export default {
  name: 'gallerylist',
  components: { Hgr },
  data () {
    return { keyword: '', option: '' }
  },
  created () {
    this.paging.currentPage = this.number
    this.$store.commit(Constant.GNB, { menu: 'gallery' })
  },
  computed: mapState({
    gallerylist: (state) => state.boardlist,
    paging: (state) => state.paging
  }),
  mounted () {
    let designSelect = document.querySelector('.design_select select')

    this.option = designSelect[designSelect.selectedIndex].value
    this.$store.dispatch(Constant.LIST, { keyword: this.keyword, option: this.option, index: this.$route.query.number })
  },
  methods: {
    current (index) {
      if (parseInt(this.paging.currentPage) === index) {
        return { current: true }
      } else {
        return { current: false }
      }
    },
    selectCategory (event) {
      this.option = event.target.value
    },
    galleryList (index) {
      this.$store.dispatch(Constant.LIST, { keyword: this.keyword, option: this.option, index: index })
    }
  }
}
</script>
