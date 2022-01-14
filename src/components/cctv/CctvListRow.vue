<template>
  <b-tr>
    <b-td>{{ institutionNm }}</b-td>
    <b-td>{{ lnmadr }}</b-td>
    <b-td>{{ phoneNumber }}</b-td>
    <b-td>{{ installationYymm }}</b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "CctvListRow",
  props: {
    institutionNm: String,
    lnmadr: String,
    phoneNumber: String,
    installationYymm: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style></style>
