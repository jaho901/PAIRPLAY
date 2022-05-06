<template>
  <div>
    <Datepicker v-model="timeData" :enableTimePicker="false" range multiCalendars class="ms-2 datePicker"></Datepicker>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";

export default {
  name: "PlaceSearchFiltersTime",
  components: { Datepicker },
  // emits: [timeData],

  setup() {
    const timeData = ref({});
    onMounted(() => {
      const startDate = new Date();
      const endDate = new Date(new Date().setDate(startDate.getDate() + 7));
      timeData.value = [startDate, endDate];
    });
    // const picked = ref(new Date());
    return {
      timeData,
      // picked,
    };
    // const store = userStore();
    // const route = useRoute();
  },
};
</script>

<style lang="scss">
.datePicker {
  width: 250px;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  // border-radius: 500px;
  &:hover {
    border: 0.5px solid black;
    border-radius: 4px;
  }
}
</style>
