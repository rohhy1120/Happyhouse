<template>
  <div class="navv">
    <b-navbar class="nav-custom" toggleable="lg" type="white">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="70px"
            alt="Kitten"
          />
          <img
            src="@/assets/navicon1.png"
            class="d-inline-block align-middle"
            width="120px"
            alt="Kitten"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Home' }" class="link">
              HOME</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Board' }" class="link">
              QNA</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Instargram' }" class="link">
              SNS</router-link
            ></b-nav-item
          >
          <b-nav-item href="#">
            <router-link :to="{ name: 'News' }" class="link">
              NEWS</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'House' }" class="link">
              APT INFO</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Commercial' }" class="link">
              COMMERCIAL</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Cctv' }" class="link">
              CCTV</router-link
            ></b-nav-item
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center">
            {{ userInfo.username }}님 환영합니다.</b-nav-item
          >
          <b-nav-item class="align-self-center"
            ><router-link
              :to="{ name: 'MyPage' }"
              class="link align-self-center"
              >내정보보기</router-link
            ></b-nav-item
          >
          <b-nav-item class="align-self-center">
            <router-link
              v-if="userInfo.userid == 'admin'"
              :to="{ name: 'MemberList' }"
              class="link align-self-center"
              >회원관리</router-link
            ></b-nav-item
          >
          <b-nav-item
            class="link align-self-center text-danger"
            @click.prevent="onClickLogout"
            >로그아웃</b-nav-item
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-dropdown-item href="#"
            ><router-link :to="{ name: 'SignUp' }" class="link">
              JOIN</router-link
            ></b-dropdown-item
          >
          <b-dropdown-item href="#"
            ><router-link :to="{ name: 'SignIn' }" class="link">
              LOGIN</router-link
            ></b-dropdown-item
          >
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style>
.nav-custom {
  background-image: url("wood.jpeg");
  background-size: cover;
}
.nav-item {
  color: green !important;
}

/* @import url("https://fonts.googleapis.com/css2?family=Road+Rage&display=swap");
html,
body {
  font-family: "Road Rage", cursive !important;
} */
</style>
