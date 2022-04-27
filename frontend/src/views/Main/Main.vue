<template>
  <div style="height: 100vh; width: 100vw; margin: 0px">
    <Header></Header>
    <div class="main"></div>
  </div>
</template>

<script>
import Header from "../Common/Header.vue";
export default {
  name: "Main",
  components: {
    Header,
  },
};
</script>

<style>
.main {
  width: 100%;
  height: 30%;
  background-image: url("@/assets/images/Main/Main.jpeg");
  background-size: cover;
}
</style>
