<template>
  <div class="hgroup">
    <h3 class="title">{{ title }}</h3>

    <ul class="descript" v-if="!this.$store.state.header">
      <li>{{ descript }}</li>
    </ul>

    <p class="descript" v-else>{{ descript }}</p>
  </div><!-- // hgroup -->
</template>

<script>
export default {
  name: 'hgr',
  props: [ 'title', 'descript' ]
}
</script>
