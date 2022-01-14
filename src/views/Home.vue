<template class="tofont">
  <b-container class="bv-example-row mt-3 text-center">
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron class="jumbo" text-variant="dark" border-variant="white">
          <template #header>
            <img
              src="@/assets/homeimg1.png"
              class="d-inline-block align-middle"
              width="500px"
              alt="Kitten"
            />
          </template>

          <template #lead> 해피하우스에 오신 것을 환영합니다.<br /> </template>
          <img
            src="@/assets/homeimg2.png"
            class="d-inline-block align-middle"
            width="700px"
            alt="Kitten"
          />
          <hr class="my-4" />

          <p>행복한 우리 집</p>
          <p>문의 : <b>02-1234-5678</b>로 전화주세요.</p>
          <p>방문 : <b>서울시 강남구 테헤란로</b>로 방문해주세요.</p>
          <p>이메일 : <b>admin@ssafy.com</b>으로 보내주세요.</p>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "Main",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.jumbo {
  background-color: #faed7d;
}
.tofont {
  font-family: "Jua", sans-serif;
}
</style>
