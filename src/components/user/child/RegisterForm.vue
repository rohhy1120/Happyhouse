<template>
  <b-row class="align-middle">
    <b-col></b-col>
    <b-col cols="5">
      <b-card
        class="text-center mt-3 bg-transparent border-0 align-middle"
        style="max-width: 30rem"
      >
        <b-form @submit="onSubmit" @reset="onReset" class="text-left">
          <b-form-group
            id="userid-group"
            label="아이디"
            label-for="userid"
            description="아이디를 입력하세요."
          >
            <b-form-input
              id="userid"
              :disabled="isUserid"
              v-model="user.userid"
              type="text"
              required
              placeholder="아이디를 입력하세요."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="username-group"
            label="이름"
            label-for="username"
            description="이름을 입력하세요."
          >
            <b-form-input
              id="username"
              v-model="user.username"
              type="text"
              required
              placeholder="이름을 입력하세요."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="userpwd-group"
            label="비밀번호"
            label-for="userpwd"
            description="비밀번호를 입력하세요."
          >
            <b-form-input
              id="userpwd"
              v-model="user.userpwd"
              type="password"
              required
              placeholder="비밀번호를 입력하세요."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="email-group"
            label="이메일"
            label-for="email"
            description="이메일을 입력하세요."
          >
            <b-form-input
              id="email"
              v-model="user.email"
              type="text"
              required
              placeholder="이메일을 입력하세요."
            ></b-form-input>
          </b-form-group>
          <b-col class="text-center">
            <b-button type="submit" variant="dark" class="m-1" align="center"
              >가입</b-button
            >
            <b-button type="reset" variant="dark" class="m-1" align="center"
              >초기화</b-button
            >
          </b-col>
        </b-form>
      </b-card>
    </b-col>
    <b-col></b-col>
  </b-row>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "BoardWriteForm",
  data() {
    return {
      user: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
        regtime: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {},
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.user.userid &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.user.username &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.username.focus());
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userpwd.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.email.focus());

      if (!err) alert(msg);
      else this.registArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.user.userid = "";
      this.user.username = "";
      this.user.userpwd = "";
      this.user.useremail = "";
      this.$router.push({ name: "SignUp" });
    },
    registArticle() {
      http
        .post(`/user/register`, {
          userid: this.user.userid,
          username: this.user.username,
          userpwd: this.user.userpwd,
          email: this.user.email,
        })
        .then(({ data }) => {
          let msg = "등록 중 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "SignIn" });
    },
  },
};
</script>

<style></style>
