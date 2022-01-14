<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/modifyimg1.png"
      class="d-inline-block align-middle"
      width="400px"
      alt="Kitten"
    />
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
              label="아이디:"
              label-for="userid"
              description="아이디를 입력하세요."
            >
              <b-form-input
                id="userid"
                v-model="userInfo.userid"
                type="text"
                required
                placeholder="아이디를 입력하세요."
                readonly
              ></b-form-input>
            </b-form-group>

            <b-form-group
              id="username-group"
              label="이름:"
              label-for="username"
              description="이름을 입력하세요."
            >
              <b-form-input
                id="username"
                v-model="userInfo.username"
                type="text"
                required
                placeholder="이름을 입력하세요."
              ></b-form-input>
            </b-form-group>

            <b-form-group
              id="userpwd-group"
              label="비밀번호:"
              label-for="userpwd"
              description="비밀번호를 입력하세요."
            >
              <b-form-input
                id="userpwd"
                v-model="userInfo.userpwd"
                type="password"
                required
                placeholder="비밀번호를 입력하세요."
              ></b-form-input>
            </b-form-group>

            <b-form-group
              id="email-group"
              label="이메일:"
              label-for="email"
              description="이메일을 입력하세요."
            >
              <b-form-input
                id="email"
                v-model="userInfo.email"
                type="text"
                required
                placeholder="이메일을 입력하세요."
              ></b-form-input>
            </b-form-group>
            <b-col class="text-center">
              <b-button type="submit" variant="dark" class="m-1" align="center"
                >수정</b-button
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
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "MemberModify",
  data() {
    return {
      user: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
      },
      isUserid: false,
    };
  },
  created() {
    console.log(this.$route.params.userid);
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.userInfo.username &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.username.focus());
      err &&
        !this.userInfo.userpwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userpwd.focus());
      err &&
        !this.userInfo.email &&
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
        .put(`/user/modify`, {
          userid: this.userInfo.userid,
          username: this.userInfo.username,
          userpwd: this.userInfo.userpwd,
          email: this.userInfo.email,
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
      this.$router.push({ name: "MyPage" });
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>
