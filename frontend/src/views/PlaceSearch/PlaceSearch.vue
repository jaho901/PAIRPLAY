<template>
  <div>
    <!-- 헤더 -->
    <div style="max-width: 1400px; margin: auto">
      <Header></Header>
    </div>
    <!-- <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7" /> -->
    <place-search-filters :@searchFiltersData="searchFiltersData"></place-search-filters>
    <div class="container PlaceSearchContentFrame">
      <div class="placeSearchContent container d-flex justify-content-around align-items-start">
        <div class="mt-4 col">
          <div class="placeSearchTitle mb-3 ps-2">부산 금정구 운동시설</div>
          <div class="py-2">
            <div class="placeSearchList">
              <place-search-list v-for="card in cards" :key="card.idx" :card="card" class="placeSearchList me-3 col"> </place-search-list>
            </div>
          </div>
          <!-- <place-search-maps class="placeSearchMaps col-6" style="height: 800px">아아</place-search-maps> -->
        </div>
        <!-- <place-search-maps class="col placeSearchMaps">아아</place-search-maps> -->
      </div>
    </div>
    <footer>푸터</footer>
  </div>
</template>

<script>
import { onMounted, computed, reactive } from "vue";
import { useStore } from "vuex";
import Header from "../Common/Header.vue";
import PlaceSearchFilters from "./Components/PlaceSearchFilters.vue";
import PlaceSearchList from "./Components/PlaceSearchList.vue";
// import PlaceSearchMaps from "./Components/PlaceSearchMaps.vue";
export default {
  name: "PlaceSearch",
  // PlaceSearchMaps
  components: { Header, PlaceSearchFilters, PlaceSearchList },
  setup() {
    const store = useStore();
    let cards = reactive();
    let searchFiltersData = reactive({
      categoryList: "",
      endData: "",
      gugun: "",
      sido: "",
      startData: "",
    });

    const getCards = async () => {
      await store
        .dispatch("root/getPlaceSearchInfo", searchFiltersData)
        .then((res) => console.log(res, "데이터 나오나 get cards"))
        .catch((err) => console.log(err, "error"));
      if (store.state.placeSearchInfo) {
        cards.push(computed(() => store.state.placeSearchInfo));
      }
    };
    onMounted(() => {
      getCards();
    });
    // const cards = reactive([]);

    // const store = userStore();
    // const route = useRoute();
    return { cards, store, getCards, onMounted };
  },
};
</script>

<style lang="scss" scoped>
// " {
// }
// .totalFrmae {
//   // height: 100vh;
// }

.PlaceSearchContentFrame {
  width: 100%;

  // width: 50%;
  // background-color: wheat;
  max-height: 70vh;
  overflow-y: overlay;
  // overflow-x: overlay;
  // scrollbar-width: 0px;
  max-width: 100vw;
}
.PlaceSearchContentFrame {
}
// ::-webkit-scrollbar {
//   // display: none;
//   // width: 0px;
// }
.placeSearchContent {
  max-width: 1400px;
}
.placeSearchTitle {
  font-size: 16px;
}

// .placeSearchListCards {
// padding-left: 5px;
// padding-right: 5px;
// }

.placeSearchMaps {
  display: block;
  height: unset;
  position: sticky;
  top: 0px;
  visibility: unset;
  width: unset;
  //   width: 55%;
  // background: black;
  // height: 600px;
}
</style>
