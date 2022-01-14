<template>
  <b-tr>
    <b-td>{{ userid }}</b-td>
    <b-td
      ><b-form-input
        id="username"
        v-model="username"
        type="text"
        required
      ></b-form-input
    ></b-td>
    <b-td
      ><b-form-input
        id="userpwd"
        v-model="userpwd"
        type="text"
        required
      ></b-form-input
    ></b-td>
    <b-td
      ><b-form-input
        id="email"
        v-model="email"
        type="text"
        required
      ></b-form-input>
    </b-td>
    <b-td>{{ joindate }}</b-td>
    <b-td v-if="del == 0">X</b-td>
    <b-td v-else>O</b-td>

    <b-td>
      <b-button type="submit" variant="dark" @click="ModifyUser">수정</b-button
      >&nbsp;
      <b-button type="reset" variant="dark" @click="deleteUser">삭제</b-button>
    </b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";

import http from "@/util/http-common";
export default {
  name: "MemberListRow",
  props: {
    userid: String,
    username: String,
    userpwd: String,
    email: String,
    joindate: String,
    del: Number,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
  methods: {
    deleteUser() {
      if (confirm("삭제하실건가요?")) {
        http.delete(`/user/${this.userid}`).then(({ data }) => {
          let msg = "삭제 중 문제 발생";
          if (data === "success") {
            msg = "삭제 완료";
          }
          alert(msg);
          this.moveList();
        });
      }
    },
    ModifyUser() {
      http
        .put(`/user/modify`, {
          userid: this.userid,
          username: this.username,
          userpwd: this.userpwd,
          email: this.email,
        })
        .then(({ data }) => {
          let msg = "수정 중 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "MemberList" });
    },
  },
};
</script>

<style></style>
