<template>
  <b-tr>
    <b-td>{{ index + currentPage * perPage - 2 }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'BoardView', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ regtime }}</b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "BoardListRow",
  props: {
    index: Number,
    articleno: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
    currentPage: Number,
    perPage: Number,
    searchName: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style></style>
