<template>
  <b-tr>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ comment }}</b-td>
    <b-td>{{ memotime }}</b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "AnswerListRow",
  props: {
    userid: String,
    comment: String,
    memotime: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style></style>
