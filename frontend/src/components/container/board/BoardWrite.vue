<template>
  <section id="contents">
    <Hgr title="일반 게시판 등록" descript="이곳에서 일반 게시판 내용을 등록할 수 있습니다."></Hgr>

    <table class="table_global">
      <caption>일반 게시판에 등록할 게시물의 카테고리, 제목, 내용을 입력할 수 있습니다.</caption>

      <colgroup>
        <col style="width:10%;" />
        <col />
      </colgroup>

      <tbody>
        <tr>
            <th scope="row">아이디</th>
            <td><span class="inputfield_outer"><input type="text" name="id" class="inputfield_global" readonly v-model="id" /></span></td>
          </tr>
          <tr>
            <th scope="row">작성자</th>
            <td><span class="inputfield_outer"><input type="text" name="name" class="inputfield_global" readonly v-model="name" /></span></td>
          </tr>
          <tr>
            <th scope="row">카테고리</th>
            <td><span class="inputfield_outer"><input type="text" name="category" class="inputfield_global" readonly v-model="category" /></span></td>
          </tr>
          <tr>
            <th scope="row">제목</th>
            <td><span class="inputfield_outer"><input type="text" name="subject" class="inputfield_global" v-model="subject" /></span></td>
          </tr>
          <tr>
            <th scope="row">내용</th>
            <td>
              <vue-editor class="quill-editor"
              useCustomImageHandler
              @imageAdded="handleImageAdded" v-model="htmlForEditor">
              </vue-editor>
            </td>
          </tr>
          <tr>
            <th scope="row">첨부파일</th>
            <td><input type="file" name="file" ref="file" /></td>
          </tr>
      </tbody>
    </table><!-- // table_global -->

    <div class="board_gravity">
      <div class="inner_gravity">
        <button type="button" class="button_smarteditor" v-on:click="boardWrite">등록</button>
        <router-link v-bind:to="{name: 'BoardList'}" class="button_global">취소</router-link>
      </div><!-- // inner_gravity -->
    </div><!-- // board_gravity -->
  </section><!-- // contents -->
</template>

<script>
// Vue Instant Life Cycle
// beforeCreate → created → beforeMount → mounted → beforeUpdate → updated → beforeDestroy → destroyed

import Constant from '../../../Constant.js'
import Hgr from '../member/Hgroup.vue'

import { VueEditor } from 'vue2-editor'

import axios from 'axios'

export default {
  name: 'boardWrite',
  components: { Hgr, VueEditor },
  data () {
    return { id: this.id, name: this.name, category: '일반', subject: '', content: '', htmlForEditor: '' }
  },
  mounted () {
    this.id = localStorage.getItem('id')
    this.name = localStorage.getItem('name')
  },
  methods: {
    handleImageAdded: function (file, Editor, cursorLocation, resetUploader) {
      let formData = new FormData()

      formData.append('image', file)

      axios({
        url: '/api/admin/vuejs/board/write.action',
        method: 'POST',
        data: formData
      }).then((result) => {
        let url = result.data.url

        Editor.insertEmbed(cursorLocation, 'image', url)

        resetUploader()
      }).catch((error) => {
        console.error(error)
      })
    },
    boardWrite () {
      let formData = new FormData()

      formData.append('id', this.id)
      formData.append('name', this.name)
      formData.append('category', this.category)
      formData.append('subject', this.subject)
      formData.append('content', this.content)
      formData.append('file', this.$refs.file.files[0])

      this.$store.dispatch(Constant.WRITE, formData)
    }
  }
}
</script>
