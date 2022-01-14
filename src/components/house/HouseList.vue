<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row">
    <img
      src="@/assets/aptlistimg1.png"
      class="d-inline-block align-left"
      width="500px"
      alt="Kitten"
    />
    <house-list-row
      v-for="(house, index) in itemsForList"
      :key="index"
      :house="house"
    />
    <b-pagination
      align="center"
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListRow from "@/components/house/HouseListRow.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return { perPage: 5, currentPage: 1 };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
    rows() {
      return this.houses.length;
    },
    itemsForList() {
      return this.houses.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      );
    },
  },
};
</script>

<style></style>
