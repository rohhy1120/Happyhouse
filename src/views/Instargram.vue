<template>
  <b-container class="bv-example-row mt-3 text-center">
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron
          class="jumbo"
          bg-variant="muted"
          text-variant="dark"
          border-variant="light"
        >
          <a href="https://www.instagram.com/hellossafy/?hl=ko">
            <img
              src="@/assets/instagramimg1.png"
              class="d-inline-block align-middle"
              width="600px"
              alt="Kitten"
            />
          </a>
          <hr class="my-4" />

          <p><b>위 사진을 클릭하여 행복의 집 계정을 방문해보세요!</b></p>
          <p><b>ssafy@instagram.com</b></p>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "Instargram",
};
</script>

<style scoped>
.jumbo {
  background-color: #ffd9e7;
}
</style>
