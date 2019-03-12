<template>
  <section class="contents">
    <Hgr title="다운로드 게시판 상세" descript="이곳에서 다운로드 게시판 내용을 확인할 수 있습니다."></Hgr>

    <table class="table_global style_tableView">
      <caption>다운로드 게시판의 등록된 게시물의 번호, 등록일, 조회수, 아이디, 작성자, 카테고리, 제목, 내용을 확인할 수 있습니다.</caption>

      <colgroup>
        <col style="width:10%;">
        <col>
      </colgroup>

      <tbody v-for="(download, index) in this.downloadView" v-bind:key="index">
        <tr>
          <th scope="row">번호</th>
          <td>{{ download.number }}</td>
        </tr>
        <tr>
          <th scope="row">등록일</th>
          <td>{{ download.regdate }}</td>
        </tr>
        <tr>
          <th scope="row">조회수</th>
          <td>{{ download.count }}</td>
        </tr>
        <tr>
          <th scope="row">아이디</th>
          <td>{{ download.id }}</td>
        </tr>
        <tr>
          <th scope="row">작성자</th>
          <td>{{ download.name }}</td>
        </tr>
        <tr>
          <th scope="row">카테고리</th>
          <td>{{ download.category }}</td>
        </tr>
        <tr>
          <th scope="row">제목</th>
          <td>{{ download.subject }}</td>
        </tr>
        <tr class="row_local">
          <th scope="row">내용</th>
          <td class="ql-editor" v-html="download.content"></td>
        </tr>
        <tr v-if="download.file !== ''">
          <th scope="row">첨부파일</th>
          <td>{{ download.file }}</td>
        </tr>
      </tbody>
    </table><!-- // table_global -->

    <div class="board_gravity">
      <div class="inner_gravity">
        <router-link v-bind:to="{ name: 'DownloadModify', params: { number: this.$route.params.number } }" class="button_global">수정</router-link>
        <a href="#" class="button_global" v-on:click="downloadDelete">삭제</a>
        <router-link v-bind:to="{name: 'DownloadList'}" class="button_global">목록</router-link>
      </div><!-- // inner_gravity -->
    </div><!-- // board_gravity -->
  </section><!-- // contents -->
</template>

<script>
import Constant from '../../../Constant.js'
import Hgr from '../member/Hgroup.vue'

import { mapState } from 'vuex'

export default {
  name: 'downloadView',
  props: [ 'number' ],
  components: { Hgr },
  mounted () {
    this.$store.dispatch(Constant.VIEW, { number: this.number })
  },
  computed: mapState({
    downloadView: (state) => state.boardview
  }),
  methods: {
    downloadDelete () {
      if (confirm('삭제 후 복구할 수 없습니다.\n정말로 삭제하시겠습니까?')) this.$store.dispatch(Constant.DELETE, { number: this.number, id: localStorage.getItem('id') })
    }
  }
}
</script>
