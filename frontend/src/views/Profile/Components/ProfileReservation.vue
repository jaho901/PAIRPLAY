<template>
  <div>
    <div class="ps-4 pt-4" style="height: 100%;">
      <profile-reservation-view :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-reservation-view>
    </div>
  </div>
</template>

<script>
import { reactive, onMounted } from 'vue'
import { useStore } from 'vuex'
import ProfileReservationView from './ProfileReservationView.vue'
export default {
  name: "ProfileReservation",
  components: {
    ProfileReservationView
  },
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const store = useStore()
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo
    })

    onMounted(async () => {
      await store.dispatch("root/profileReservationList")
    })

    return { state, onMounted }
  }
}
</script>

<style>

</style>