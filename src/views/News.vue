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
          <template #header>
            <img
              src="@/assets/newsimg4.png"
              class="d-inline-block align-middle"
              width="600px"
              alt="Kitten"
          /></template>

          <!-- <template #lead> 홍보용 SNS </template> -->

          <hr class="my-4" />
          <a href="https://www.fnnews.com/news/202111170831513525">
            <img
              src="@/assets/newsimg1.png"
              class="d-inline-block align-middle"
              width="800px"
              alt="Kitten"
            />
          </a>
          <a
            href="https://newsis.com/view/?id=NISX20211116_0001653234&cID=10401&pID=10400"
          >
            <img
              src="@/assets/newsimg2.png"
              class="d-inline-block align-middle"
              width="800px"
              alt="Kitten"
            />
          </a>
          <a href="https://www.yna.co.kr/view/AKR20211118086500003?input=1195m">
            <img
              src="@/assets/newsimg3.png"
              class="d-inline-block align-middle"
              width="800px"
              alt="Kitten"
            />
          </a>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "News",
};
</script>

<style scoped>
.jumbo {
  background-color: rgb(200, 223, 245);
}
</style>
