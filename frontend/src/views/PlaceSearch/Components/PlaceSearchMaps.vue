<template>
  <div>
    <naver-maps class="naverMaps" :mapOptions="mapOptions" style="width: 100%; height: 70vh" :initLayers="initLayers" @onLoad="onLoadMap($event)" @click="checkPosition">
      <naver-marker v-for="(mark, idx) in markers" :key="idx" :latitude="mark.latitude" :longitude="mark.longitude" @click="onMarkerClicked" @onLoad="onLoadMarker($markerObject)">
        <div class="marker"><i class="bi bi-geo-alt-fill"></i> {{ cards[idx].name }}</div>
      </naver-marker>
      <!-- <naver-marker :latitude="markers.latitude" :longitude="markers.longitude" @click="onMarkerClicked" @onLoad="onLoadMarker($markerObject)"></naver-marker> -->
    </naver-maps>
  </div>
</template>

<script>
import { ref, reactive, computed, onMounted } from "vue";
import { useStore } from "vuex";

import { NaverMaps, NaverMarker } from "vue3-naver-maps";

export default {
  name: "PlaceSearchMaps",
  components: { NaverMaps, NaverMarker },
  setup() {
    const store = useStore();
    const markers = reactive(computed(() => store.state.root.mapPosition));
    // const markers = reactive();
    // watch(markers, () => console.log(markers.value));
    // { latitude: 36, longitude: 127 },
    // { latitude: 36.5759477, longitude: 128.5056462 },
    // ];
    let cards = reactive(computed(() => store.state.root.placeSearchInfo.placeList));
    const positionYX = ref({});
    const map = ref();
    const marker = ref();
    let tempLongitude;
    let tempLatitude;
    const mapOptions = reactive({
      // 요부분이 맨 첫 리스트의경도 위도 일듯
      longitude: computed(() => {
        return checkLongitude();
      }),
      latitude: computed(() => {
        return checkLatitude();

        // return temp;
      }),
      zoom: 14,
      zoomControl: true,
      zoomControlOptions: { position: "TOP_RIGHT" },
    });
    const initLayers = ["BACKGROUND", "BACKGROUND_DETAIL", "POI_KOREAN", "TRANSIT", "ENGLISH"];
    let checkLatitude = () => {
      if (store.state.root.placeSearchInfo.placeList) {
        tempLatitude = store.state.root.placeSearchInfo.placeList[0].latitude;
        console.log(tempLatitude, "있다 래티튜드");
      } else {
        tempLatitude = 127;
        console.log(tempLatitude, "없다 latitude");
      }
      return tempLatitude;
    };
    let checkLongitude = () => {
      if (store.state.root.placeSearchInfo.placeList) {
        tempLongitude = store.state.root.placeSearchInfo.placeList[0].longitude;
        console.log(tempLongitude, "있다 롱기튜드");
      } else {
        tempLongitude = 6;
        console.log(tempLongitude, "없다 longitude");
      }
      return tempLongitude;
    };
    // const LatLng = new window.naver.maps.LatLng(37, 127);
    const changeMarkers = async () => {
      var tempLong;
      var tempLat;
      var tempList = { tempLong, tempLat };
      for (let i = 0; i < store.state.root.placeSearchInfo.placeList.length; i++) {
        tempLong = store.state.root.placeSearchInfo.placeList[i].longitude;
        tempLat = store.state.root.placeSearchInfo.placeList[i].latitude;
        // console.log(tempLat);
        markers.pos = tempList;
      }
      return tempList;
      // await store.dispatch("root/changePosition", markers.value);
      // console.log(markers.value);
    };
    // watch(markers.pos, async () => {
    // changeMarkers();
    // });
    onMounted(async () => {
      // changeMarkers();
      // checkLatitude;
      // checkLongitude;
    });

    const onLoadMap = (mapObject) => {
      map.value = mapObject; // map에 반환된 객체 저장
      map.value.setCenter(new window.naver.maps.LatLng(37, 127));
      // console.log(map.value, "map");
      // map.value.setCenter(new window.naver.maps.LatLng(36, 127)); // 지도 중앙 변경
    };
    const onLoadMarker = (markerObject) => {
      // markerObject.marker.setDraggable(true).setCursor("").setClickable(true);
      marker.value = markerObject;
      // console.log(marker.value, "marker");
    };
    const onMarkerClicked = (event) => {
      console.log(event, "onMarkerClicked");
    };
    const checkPosition = (event) => {
      // positionYX['latitude':]
      console.log(event, "checkPosition");
    };
    return {
      cards,
      map,
      marker,
      mapOptions,
      initLayers,
      positionYX,
      markers,
      tempLatitude,
      tempLongitude,
      onMounted,
      onLoadMap,
      onLoadMarker,
      onMarkerClicked,
      checkPosition,
      changeMarkers,
      checkLatitude,
      checkLongitude,
    };
  },
};
</script>

<style lang="scss" scoped>
// .naverMaps {
// width: 100%;
// height: 500px;
// }
.marker {
  padding: 0.25rem 0.25rem 0.25rem 0.25rem;
  min-height: 30px;
  min-width: 10px;
  max-width: 10rem;
  border-radius: 0.5rem;
  background: white;
  font-size: 12px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-weight: bold;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));

  // display: -webkit-box;
  // -webkit-line-clamp: 1;
  // -webkit-box-orient: vertical;
  cursor: pointer;
  &:hover {
    z-index: 10;
    background: black;
    color: white;
    position: relative;
  }
}
</style>
