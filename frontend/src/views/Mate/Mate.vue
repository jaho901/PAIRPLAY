<template>
  <div>
    <mate-filter></mate-filter>
    <mateview></mateview>
  </div>
</template>

<script>
import MateFilter from './components/MateFilter.vue'
import Mateview from './components/Mateview.vue'
export default {
  name: "Mate",
  components: { MateFilter, Mateview },
  setup() {

  }
}
</script>

<style>

</style>