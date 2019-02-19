<template>
  <section id="contents">
    <Hgr title="이미지 게시판 수정" descript="이곳에서 이미지 게시판 내용을 수정할 수 있습니다."></Hgr>

    <table class="table_global">
      <caption>이미지 게시판에 등록된 게시물의 카테고리, 제목, 내용을 수정할 수 있습니다.</caption>

      <colgroup>
        <col style="width:10%;" />
        <col />
      </colgroup>

      <tbody>
        <tr>
          <th scope="row">번호</th>
          <td><span class="inputfield_outer"><input type="text" name="number" class="inputfield_global" v-model="number" /></span></td>
        </tr>
        <tr>
          <th scope="row">아이디</th>
          <td><span class="inputfield_outer"><input type="text" name="id" class="inputfield_global" v-model="id" /></span></td>
        </tr>
        <tr>
          <th scope="row">작성자</th>
          <td><span class="inputfield_outer"><input type="text" name="name" class="inputfield_global" v-model="name" /></span></td>
        </tr>
        <tr>
          <th scope="row">카테고리</th>
          <td><span class="inputfield_outer"><input type="text" name="category" class="inputfield_global" v-model="category" /></span></td>
        </tr>
        <tr>
          <th scope="row">제목</th>
          <td><span class="inputfield_outer"><input type="text" name="subject" class="inputfield_global"  v-model="subject" /></span></td>
        </tr>
        <tr class="row_local">
          <th scope="row">내용</th>
          <td><span class="textfield_outer"><textarea name="content" id="content" class="textfield_global"  v-model="content" ></textarea></span></td>
        </tr>
      </tbody>
    </table><!-- // table_global -->

    <div class="board_gravity">
      <div class="inner_gravity">
        <button type="submit" class="button_smarteditor" v-on:click="galleryModify">수정</button>
        <router-link v-bind:to="{name: 'GalleryList'}" class="button_global">취소</router-link>
      </div><!-- // inner_gravity -->
    </div><!-- // board_gravity -->
  </section><!-- // contents -->
</template>

<script>
// Vue Instant Life Cycle
// beforeCreate → created → beforeMount → mounted → beforeUpdate → updated → beforeDestroy → destroyed

import Constant from '../../../Constant.js'
import Hgr from '../member/Hgroup.vue'

export default {
  name: 'galleryModify',
  props: [ 'number' ],
  components: { Hgr },
  data () {
    return { id: '', name: '', category: '', subject: '', content: '' }
  },
  methods: {
    galleryModify () {
      this.$store.dispatch(Constant.MODIFY, { number: this.number, id: this.id, name: this.name, category: this.category, subject: this.subject, content: this.content })
    }
  }
}
</script>
