<template>
  <h2>
    <div class="d-flex justify-content-between">
      <div><b>예약 기록</b></div>
      <div class="d-flex align-items-end">
        <div class="me-5" style="font-size: medium; cursor: pointer;" @click="profileReservationList"><b>모든 장소</b></div>
        <div class="me-5" style="font-size: medium; cursor: pointer;" @click="profileReservationDoingList"><b>예악 중인 장소</b></div>
        <div style="font-size: medium; cursor: pointer;" @click="profileReservationDoneList"><b>사용 완료 장소</b></div>
      </div>
    </div>
    <hr>
  </h2>
  <div v-for="(data, idx) in state.profileReservationList" :key="idx">
    <div class="d-flex my-5 row">
      <div class="col-3">
        <img :src="data.myReservation.place[0].img[0]" alt="" class="res-img">
      </div>
      <div class="col-6 ms-5 d-flex justify-content-center" style="flex-direction: column;">
        <p style="font-size: medium; color: rgb(182 182 182);">{{ data.myReservation.place[0].address }}</p>
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <h4>{{ data.myReservation.place[0].name }}</h4>
            <div class="d-flex align-items-center">
              <div class="rating me-2"></div>
              <span class="me-4">{{ data.myReservation.place[0].score }}</span>
              <div class="view me-2"></div>
              <span class="me-4">{{ data.myReservation.place[0].viewCnt }}</span>
              <div class="review me-2"></div>
              <span class="me-4">{{ data.myReservation.place[0].reviewCnt }}</span>
            </div>
          </div>
          <span><b>{{ data.myReservation.place[0].category }}</b></span>
        </div>
        <p class="mt-5">
          <span style="font-weight: bold; font-size: small;">
            {{ data.myReservation.reserveStartDt[0] }}년 {{ data.myReservation.reserveStartDt[1] }}월 {{ data.myReservation.reserveStartDt[2] }}일
          </span>
          <span v-for="(time, idx) in data.myReservation.time" :key="idx" style="font-weight: bold; font-size: small;">
            <span v-if="idx==data.myReservation.time.length-1">{{ time }}:00 ~ {{ time + 1 }}:00</span>
            <span v-else>{{ time }}:00 ~ {{ time + 1 }}:00, </span>
          </span>
          <span style="font-size: small;">에 예약중입니다.</span>
        </p>
        <p v-if="data.used==false" style="font-size: x-small;">
          더 자세한 상품의 이용방법, 환불 등에 대한 문의는 판매자에게 문의해주세요.
        </p>
        <p v-else style="font-size: x-small;">
          구매확정 이후 상품의 이용방법 및 반품 등에 대한 문의는 판매자에게 문의해주세요.
        </p>
      </div>
      <div v-if="data.used==false" class="col-2 d-flex justify-content-evenly align-items-center" style="flex-direction: column;">
        <button class="reser-btn" @click="moveToPlaceDetail(data.myReservation.placeId)">자세히</button>
        <button class="reser-btn" @click="cancelPlaceReservation(data.myReservation.id)">예약취소</button>
      </div>
      <div v-else-if="data.used==true & data.myReservation.writtenReview==false" class="col-2 d-flex justify-content-evenly align-items-center" style="flex-direction: column;">
        <button class="reser-btn" @click="moveToPlaceDetail(data.myReservation.placeId)">자세히</button>
        <button class="reser-btn"  type="button" data-bs-toggle="modal" data-bs-target="#exampleModal1" @click="reviewDetail(data.myReservation.id)">리뷰 작성</button>
      </div>
      <div v-else-if="data.used==true & data.myReservation.writtenReview==true" class="col-2 d-flex justify-content-evenly align-items-center" style="flex-direction: column;">
        <button class="reser-btn" @click="moveToPlaceDetail(data.myReservation.placeId)">자세히</button>
        <button class="reser-btn"  type="button" data-bs-toggle="modal" data-bs-target="#exampleModal2">내 리뷰</button>
      </div>
    </div>
    <hr>
  </div>
  <!-- Modal Create -->
  <div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 750px;">
      <div class="modal-content" style="border: none;">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel1">리뷰 작성하기</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="d-flex row">
            <div class="col-4" style="height: 250px;">
              <div class="d-flex justify-content-between">
                <span>cleanness: </span>
                <input type="number" style="width: 50%;">
              </div>
              <div class="d-flex justify-content-between my-3">
                <span>location: </span>
                <input type="number" style="width: 50%;">
              </div>
              <div class="d-flex justify-content-between my-3">
                <span>place: </span>
                <input type="number" style="width: 50%;">
              </div>
              <div class="d-flex justify-content-between my-3">
                <span>price: </span>
                <input type="number" style="width: 50%;">
              </div>
            </div>
            <div class="col-1"></div>
            <div class="col-7" style="height: 250px;">
              <span class="me-3">사진 등록</span>
              <input type="file">
              <div>
                <img src="" alt="">
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal View -->
  <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" style="border: none;">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel2">내 리뷰 보기</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          ...
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
  <br><br>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: "ProfileReservationView",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
      profileReservationList: computed(() => store.getters["root/profileReservationList"]),
      reviewCreateId: 0,
    })

    const profileReservationList = async function () {
      await store.dispatch("root/profileReservationList")
    }

    const profileReservationDoingList = async function () {
      await store.dispatch("root/profileReservationDoingList")
    }

    const profileReservationDoneList = async function () {
      await store.dispatch("root/profileReservationDoneList")
    }

    const reviewDetail = async function (id) {
      state.reviewCreateId = id
    }

    const moveToPlaceDetail = function (id) {
      router.push({
        name: "PlaceDetail",
        params: {
          id: id,
        },
      });
    };

    const cancelPlaceReservation = function (id) {
      store.dispatch("root/profileCancelPlaceReservation", {
        'reservationId': id
      })
    }

    return { state, profileReservationList, profileReservationDoingList, profileReservationDoneList, reviewDetail, moveToPlaceDetail, cancelPlaceReservation }
  }
}
</script>

<style scoped lang="scss">

.res-img {
  width: 250px;
  height: 250px;
  border-radius: 10px;
}

.rating {
  background-image: url('@/assets/images/Profile/Rating.png');
  width: 20px;
  height: 20px;
  background-size: 100%;
}

.view {
  background-image: url('@/assets/images/Profile/View.png');
  width: 20px;
  height: 20px;
  background-size: 100%;
}

.review {
  background-image: url('@/assets/images/Profile/Review.png');
  width: 20px;
  height: 20px;
  background-size: 100%;
}

.reser-btn {
  width: 70%;
  padding-top: 10px;
  padding-bottom: 10px;
  border-radius: 25px;
  font-weight: bold;
  background-color: white;
  cursor: pointer;
}

</style>