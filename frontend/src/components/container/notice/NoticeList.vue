<template>
  <section id="contents">
    <Hgr title="공지사항 게시판 목록" descript="이곳에서 공지사항 게시판 목록을 확인할 수 있습니다."></Hgr>

    <table class="table_global style_tableList">
      <caption>공지사항 게시판의 등록된 게시물의 번호, 카테고리, 제목, 등록일, 조회수를 확인할 수 있습니다.</caption>

      <colgroup>
        <col style="width:10%;" />
        <col style="width:10%;" />
        <col />
        <col style="width:10%;" />
        <col style="width:15%;" />
        <col style="width:10%;" />
      </colgroup>

      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">카테고리</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">등록일</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>

      <tbody v-if="noticelist.length !== 0">
        <tr v-for="(notice, index) in noticelist" v-bind:key="index">
          <td>{{notice.number}}</td>
          <td>{{notice.category}}</td>
          <td class="subject"><router-link v-bind:to="{name: 'NoticeView', params: {number: notice.number}}">{{notice.subject}}</router-link></td>
          <td>{{notice.name}}</td>
          <td>{{notice.regdate}}</td>
          <td>{{notice.count}}</td>
        </tr>
      </tbody>

      <tbody v-else>
        <tr>
          <td colspan="6">게시물이 존재하지 않습니다.</td>
        </tr>
      </tbody>
    </table><!-- // table_global -->

    <div class="paging" v-if="noticelist.length !== 0">
      <span class="previous" v-if="paging.currentPage <= 1">이전</span>
      <a href="#" class="previous" v-else v-on:click="noticeList">이전</a>

      <div class="inner_paging">
        <router-link
          v-bind:to="{ name: 'NoticeList', query: { number: index } }"
          v-for="index in paging | paging.endPage"
          v-bind:key="index"
          v-bind:class="current (index)"
          v-on:click.native="noticeList(index)"
        >
          {{ index }}<span class="invisible" v-if="paging.currentPage === index">현재 페이지</span>
        </router-link>
        <!-- <a href="javascript:;" v-for="index in paging | paging.endPage" v-bind:key="index" v-bind:class="current (index)" v-on:click="noticeList(index)">
          {{ index }}<span class="invisible" v-if="paging.currentPage === index">현재 페이지</span>
        </a> -->
        <!-- <button type="button" v-for="index in paging | paging.endPage" v-bind:key="index" v-bind:class="current (index)" v-on:click="noticeList(index)">
          {{ index }}<span class="invisible" v-if="paging.currentPage === index">현재 페이지</span>
        </button> -->
      </div><!-- // inner_paging -->

      <span class="next" v-if="paging.currentPage >= paging.maxPage">다음</span>
      <a href="#" class="next" v-else v-on:click="noticeList">다음</a>
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
      <a href="#" class="button_global" v-on:click="noticeList(null)">검색</a>
    </div><!-- // board_search -->

    <div class="board_gravity">
      <div class="inner_gravity">
        <router-link v-bind:to="{name: 'NoticeWrite'}" class="button_global">등록</router-link>
      </div><!-- // inner_gravity -->
    </div><!-- // board_gravity -->
  </section><!-- // contents -->
</template>

<script>
// components/container/notice/NoticeList.vue

// Vue Instant Life Cycle
// beforeCreate → created → beforeMount → mounted → beforeUpdate → updated → beforeDestroy → destroyed

import Constant from '../../../Constant.js'
import Hgr from '../member/Hgroup.vue'

import { mapState } from 'vuex'

export default {
  name: 'noticeList',
  // props: [ 'number' ],
  components: { Hgr },
  data () {
    return { keyword: '', option: '' }
  },
  created () {
    // if (this.number === undefined) this.number = 1
    console.log('(NoticeList.vue) (created) this.number:', this.number)
    console.log('(NoticeList.vue) (created) this.$route.query.number: ', this.$route.query.number)

    this.paging.currentPage = this.number
    console.log('(NoticeList.vue) (created) this.paging.currentPage: ', this.paging.currentPage)

    this.$store.commit(Constant.GNB, { menu: 'notice' })
  },
  /* watch: {
    '$route': (to, from) => {
      console.log('{watch} $route from(이전): ', from)
      console.log('{watch} $route to(이후): ', to)
      console.log('{watch} $route to(이후) to.query.number: ', to.query.number)

      this.number = to.query.number
    }
  }, */
  computed: mapState({
    noticelist: (state) => state.boardlist,
    paging: (state) => state.paging
  }),
  mounted () {
    let designSelect = document.querySelector('.design_select select')
    console.log('(NoticeList.vue) {mounted} designSelect: ', designSelect)

    this.option = designSelect[designSelect.selectedIndex].value
    console.log('(NoticeList.vue) {mounted} this.option: ', this.option)

    console.log('(NoticeList.vue) (mounted) this.number:', this.number)
    console.log('(NoticeList.vue) {mounted} this.$route.query.number: ', this.$route.query.number)

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
    noticeList (index) {
      console.log('(NoticeList.vue) {methods} noticeList (index): ', index)
      console.log('(NoticeList.vue) {methods} this.number: ', this.number)
      console.log('(NoticeList.vue) {methods} this.$route.query.number: ', this.$route.query.number)

      this.$store.dispatch(Constant.LIST, { keyword: this.keyword, option: this.option, index: index })
    }
  }
}
</script>
