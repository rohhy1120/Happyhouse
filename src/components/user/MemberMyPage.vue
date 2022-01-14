<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron class="jumbo">
          <img
            src="@/assets/mypageimg1.png"
            class="d-inline-block align-middle"
            width="600px"
            alt="Kitten"
          />

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.username
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입시간</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="dark" href="#" class="mr-1 text-white">
            <router-link
              :to="{ name: 'Modify', params: { userid: userInfo.userid } }"
              >정보수정</router-link
            ></b-button
          >
          <b-button variant="dark" href="#" @click="deleteUser"
            >회원탈퇴</b-button
          >
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";
import http from "@/util/http-common";
export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  created() {},
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    deleteUser() {
      if (confirm("삭제하실건가요?")) {
        http.delete(`/user/${this.userInfo.userid}`).then(({ data }) => {
          let msg = "삭제 중 문제 발생";
          if (data === "success") {
            msg = "삭제 완료";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          if (this.$route.path != "/") this.$router.push({ name: "Home" });
        });
      }
    },
  },
};
</script>

<style scoped>
.jumbo {
  background-color: rgb(255, 211, 211);
}

a.router-link {
  text-decoration: none !important;
  color: black;
}
a:hover {
  text-decoration: none !important;
  font-weight: bold;
}

a.router-link-exact-active {
  color: #24ad1a !important;
  font-weight: bold;
}

a.router-link-active {
  color: #24ad1a !important;
  text-decoration: none !important;
  font-weight: bold;
}
a {
  color: white !important;
  text-decoration: none !important;
}

.link {
  color: white !important;
}

.link:hover {
  color: white !important;
}
</style>
