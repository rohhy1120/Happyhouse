<template>
  <b-container class="bv-example-row mt-3 text-center">
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron class="jumbo" text-variant="dark" border-variant="white">
          <template #header>
            <img
              src="@/assets/commercialimg1.png"
              class="d-inline-block align-middle"
              width="600px"
              alt="Kitten"
            />
          </template>
          <CommercialMap />
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import CommercialMap from "@/components/commercial/CommercialMap";
export default {
  name: "Commercial",
  props: {
    msg: String,
  },
  components: {
    CommercialMap,
  },
};
</script>

<style scoped>
.jumbo {
  background-color: rgb(225, 214, 231);
}
</style>
