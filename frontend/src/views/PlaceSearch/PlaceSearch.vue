<template>
  <div>
    <!-- 헤더 -->
    <div style="max-width: 1400px; margin: auto">
      <Header></Header>
    </div>
    <!-- <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7" /> -->
    <place-search-filters @searchFiltersData="getSearchFiltersData"></place-search-filters>
    <div class="container PlaceSearchContentFrame">
      <div class="placeSearchContent container d-flex justify-content-around align-items-start">
        <div class="mt-4 col">
          <div class="placeSearchTitle mb-3 ps-2">부산 {{}} 운동시설</div>
          <div class="py-2">
            <div class="placeSearchList">
              <place-search-list v-for="(card, idx) in cards" :key="idx" :card="card" :cardId="card.id" class="placeSearchList me-3 col"> </place-search-list>
              <!-- 페이지네이션 시작 -->
              <nav aria-label="Search results pages">
                <ul class="pagination">
                  <li class="page-item" @click="prevPages">
                    <a class="page-link" style="font-family: bootstrap-icons"> &#xF284; </a>
                  </li>
                  <!-- aria-current="page" -->
                  <li v-for="(page, idx) in totalPages" :key="idx" class="page-item">
                    <a class="page-link" href="#">{{ idx + 1 }}</a>
                  </li>
                  <!-- <li class="page-item"> -->
                  <!-- <a class="page-link" href="#">{{ nowpage }}</a> -->
                  <!-- </li> -->
                  <li class="page-item" @click="nextPages">
                    <a class="page-link" style="font-family: bootstrap-icons" href="#">&#xF285;</a>
                  </li>
                </ul>
              </nav>
              <!-- 페이지네이션 끝 -->
            </div>
          </div>
        </div>
        <place-search-maps class="col placeSearchMaps"></place-search-maps>
      </div>
    </div>
    <footer>푸터</footer>
  </div>
</template>

<script>
import { onMounted, ref, reactive, computed } from "vue";
import { useStore } from "vuex";
// import { useRoute } from "vue-router";
import Header from "../Common/Header.vue";
import PlaceSearchFilters from "./Components/PlaceSearchFilters.vue";
import PlaceSearchList from "./Components/PlaceSearchList.vue";
import PlaceSearchMaps from "./Components/PlaceSearchMaps.vue";
export default {
  name: "PlaceSearch",
  components: { Header, PlaceSearchFilters, PlaceSearchList, PlaceSearchMaps },
  setup() {
    const store = useStore();
    // const route = useRoute();
    let cards = reactive(computed(() => store.state.root.placeSearchInfo.placeList));
    // let cards = reactive(computed(() => store.getters["root/getPlaceInfo"]));
    let totalPages = ref(computed(() => store.state.root.placeSearchInfo.totalPages));
    let searchFiltersData = ref({
      // categoryList: [route.params.categoryList],
      categoryList: ref(Object.values(store.state.root.selectSportsCategory)).value,
      endDate: "",
      gugun: "",
      page: 0,
      sido: "",
      searchWord: "",
      startDate: "",
    });
    // console.log(store.state.root.selectSportsCategory, "있냥기");
    const getSearchFiltersData = async (res) => {
      // 만약에 선택한 필터가 없다면
      if (res.categoryList.length == 0) {
        searchFiltersData.value.categoryList = store.state.root.selectSportsCategory;
        console.log(searchFiltersData.value.categoryList, "searchFiltersData.value.categoryList");
        // pass
      } else {
        searchFiltersData.value.categoryList = res.categoryList;
        console.log(res.categoryList, "잉");
      }
      searchFiltersData.value.endDate = res.endDate;
      searchFiltersData.value.gugun = res.region.gugun;
      searchFiltersData.value.sido = res.region.sido;
      searchFiltersData.value.startDate = res.startDate;
      // console.log(searchFiltersData.value, "된겨?");
      searchFiltersData.value.page = 0;
      await getCards();
      // return searchFiltersData.value;
    };
    const getCards = async () => {
      // console.log(searchFiltersData.value, "요기는요");
      await store.dispatch("root/getPlaceSearchInfo", searchFiltersData.value);
      // console.log(tempdata, "tempdata");
      // console.log(store.state.root.placeSearchInfo, "1번");
      // await console.log(store.state.root.placeSearchInfo, "2번");
      if (store.state.root.placeSearchInfo) {
        totalPages = store.state.root.placeSearchInfo.totalPages;
        // console.log(store.state.root.placeSearchInfo.placeList, "있나요?!");
        // cards.value = computed(() => store.state.root.placeSearchInfo.placeList);
      }
      // console.log(cards.value, "!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    };
    const prevPages = () => {
      searchFiltersData.value.page -= 1;
    };
    const nextPages = () => {
      searchFiltersData.value.page += 1;
    };
    onMounted(async () => {
      await getCards();
    });
    // watch(searchFiltersData.value, async () => {
    //   await getCards();
    //   // searchFiltersData.value =
    //   // await getSearchFiltersData();
    //   //   console.log(searchFiltersData.value, "!!!!!!!!!!");
    //   //   await getCards()
    //   //     .then(() => {
    //   //       // console.log(res, "!!!!!!!!!!!!!!!!!!!!!!");
    //   //     })
    //   //     .catch((err) => {
    //   //       console.log(err);
    // });
    // });
    // const cards = reactive([]);

    // const store = userStore();
    // const route = useRoute();
    return { cards, store, totalPages, onMounted, getSearchFiltersData, getCards, prevPages, nextPages };
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
.pagination {
  margin: auto;
  justify-content: center;
}
.page-item > .page-link {
  background: white;
  color: black;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;

  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
  // border: 1px solid rgba(1, 1, 1, 0.1);
}

.active > .page-link {
  background: black;
  color: white;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
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
