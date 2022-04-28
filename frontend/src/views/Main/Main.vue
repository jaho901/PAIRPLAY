<template>
  <div>
    <Header></Header>
    <div class="PlaceBackgroundImage">
      <div class="PlaceBackgroundImageCover">
        <div class="PlaceBackground-Content ms-5 ps-5">
          <p class="ps-5 ms-4">
            당신에게 적합한 <br />
            운동장소를 찾아보세요.
          </p>
        </div>
      </div>
    </div>
    <div class="container" style="max-width: 1280px;">
      <div class="category container">
        <div class="ps-3 mb-4">
          <div class="fs-3 fw-bold">주요 서비스</div>
          <div class="container-in mt-4 mb-5">
            <div class="thumbex">    
              <div class="thumbnail one">
                <a href="javascript:void(0)">
                  <span>장소</span>
                  <button>참가하기</button>
                </a>
              </div>
            </div>
            <div class="thumbex">
              <div class="thumbnail two">
                <a href="javascript:void(0)">
                  <span>메이트</span>
                  <button>참가하기</button>
                </a>
              </div>
            </div>
          </div>
          <div class="pt-5 fs-3 fw-bold">핵심 기술</div>
          <div class="container-in mt-5">
            <div class="box">

            </div>
          </div>
        </div>
        <place-category></place-category>
      </div>
    </div>
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

<style scoped lang="scss">
.PlaceBackgroundImage {
  width: 100%;
  height: 300px;
  background: black;
  margin-bottom: 5rem;
  /* background-color: rgba(0, 0, 0, 0.4); */
  background: url("https://images.pexels.com/photos/3601094/pexels-photo-3601094.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260") 50% 40% no-repeat;
  background-size: 100% auto;
}
.PlaceBackgroundImageCover {
  position: absolute;
  width: 100%;
  height: 300px;
  background: linear-gradient(30deg, rgba(0, 0, 0, 0.7) 0%, rgba(0, 0, 0, 0) 100%);
  /* background-color: rgba(0, 0, 0, 0.3); */
  z-index: 1;
  color: white;
}
.PlaceBackground-Content {
  position: absolute;
  top: 40%;
  /* max-width: 968px; */
  font-size: calc(1em + 2vw);
  /* transform: translate(-50%, -50%); */
  z-index: 2;
  text-align: left;
  font-weight: bold;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}

.container {
  margin: auto;
}

.category {
  padding: 1rem 2rem;
}

.container-in{
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-between;
  
  .thumbex{
    margin: 10px 0px 30px;
    width: 100%;
    min-width: 250px;
    max-width: 550px;
    height: 350px;
    -webkit-flex: 1;
    -ms-flex: 1;
    flex: 1;
    overflow: hidden;
    background-size: cover;
    border-radius: 15px;
    transition: all 0.4s ease-out;
    // box-shadow: 5px 10px 40px 5px rgba(0,0,0,0.5);
    &:hover{
      box-shadow: 3px 3px 3px 3px rgba(0,0,0,0.5);
    }  
    
    .thumbnail{
      overflow: hidden;
      // min-width: 250px;
      width: 100%;
      height: 100%;
      position: relative;
      backface-visibility: hidden;
      transition: all 0.4s ease-out;
      
      span{
        position: absolute;
        z-index: 2;
        top: calc(150px - 110px);
        padding: 0px 10px;
        margin: 0px 45px;
        text-align: left;
        font-size: 40px;
        color: black;
        font-weight: bold;
        letter-spacing: 0.2px;
        // transition: all 0.3s ease-out;
      }

      button{
        position: absolute;
        z-index: 2;
        top: calc(150px - 30px);
        left: 10px;
        right: 0;
        width: 20%;
        background: white;
        border: 1px solid black;
        border-radius: 15px;
        padding: 0px 10px;
        margin: 0 45px;
        text-align: center;
        font-size: 18px;
        color: black;
        font-weight: bold;
        letter-spacing: 0.2px;
        transition: all 0.3s ease-out;
      }
      
      &:hover{
        backface-visibility: hidden;
        // transform: scale(1.15, 1.15);
        opacity: 0.8;
        box-shadow: 5px 10px 40px 5px rgba(0,0,0,0.5);        
      }
    }
  }
}

.one {
  background-image: url("@/assets/images/Main/Place.jpeg");
  background-size: cover;
}

.two {
  background-image: url("@/assets/images/Main/Mate.jpeg");
  background-size: cover;
}

</style>
