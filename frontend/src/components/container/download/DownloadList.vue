<template>
  <section id="contents">
    <Hgr title="다운로드 게시판 목록" descript="이곳에서 다운로드 게시판 목록을 확인할 수 있습니다."></Hgr>

    <table class="table_global style_tableList">
      <caption>다운로드 게시판의 등록된 게시물의 번호, 카테고리, 제목, 등록일, 조회수를 확인할 수 있습니다.</caption>

      <colgroup>
        <col style="width:10%;">
        <col style="width:10%;">
        <col>
        <col style="width:10%;">
        <col style="width:15%;">
        <col style="width:10%;">
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

      <tbody v-if="downloadlist.length !== 0">
        <tr v-for="(download, index) in downloadlist" v-bind:key="index">
          <td>{{ download.number }}</td>
          <td>{{ download.category }}</td>
          <td class="subject"><router-link v-bind:to="{ name: 'DownloadView', params: { number: download.number } }">{{ download.subject }}</router-link></td>
          <td>{{ download.name }}</td>
          <td>{{ download.regdate }}</td>
          <td>{{ download.count }}</td>
        </tr>
      </tbody>

      <tbody v-else>
        <tr>
          <td colspan="6">게시물이 존재하지 않습니다.</td>
        </tr>
      </tbody>
    </table><!-- // table_global -->

    <div class="paging" v-if="downloadlist.length !== 0">
      <span class="previous" v-if="paging.currentPage <= 1">이전</span>
      <a href="#" class="previous" v-else v-on:click="downloadlist">이전</a>

      <div class="inner_paging">
        <router-link v-bind:to="{ name: 'downloadlist', query: { number: index } }" v-for="index in paging | paging.endPage" v-bind:key="index" v-bind:class="current (index)" v-on:click.native="downloadlist(index)">{{ index }}<span class="invisible" v-if="paging.currentPage === index">현재 페이지</span>
        </router-link>
      </div><!-- // inner_paging -->

      <span class="next" v-if="paging.currentPage >= paging.maxPage">다음</span>
      <a href="#" class="next" v-else v-on:click="downloadlist">다음</a>
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

      <span class="inputfield_outer"><input type="text" name="keyword" class="inputfield_keyword" v-model="keyword"></span>
      <a href="#" class="button_global" v-on:click="downloadlist(null)">검색</a>
    </div><!-- // board_search -->

    <div class="board_gravity">
      <div class="inner_gravity">
        <router-link v-bind:to="{name: 'DownloadWrite'}" class="button_global">등록</router-link>
      </div><!-- // inner_gravity -->
    </div><!-- // board_gravity -->
  </section><!-- // contents -->
</template>

<script>
import Constant from '../../../Constant.js'
import Hgr from '../member/Hgroup.vue'

import { mapState } from 'vuex'

export default {
  name: 'downloadList',
  components: { Hgr },
  data () {
    return { keyword: '', option: '' }
  },
  created () {
    this.paging.currentPage = this.number
    this.$store.commit(Constant.GNB, { menu: 'download' })
  },
  computed: mapState({
    downloadlist: (state) => state.boardlist,
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
    downloadList (index) {
      this.$store.dispatch(Constant.LIST, { keyword: this.keyword, option: this.option, index: index })
    }
  }
}
</script>
