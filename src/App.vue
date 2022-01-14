<template>
  <div id="app">
    <navi-bar />
    <router-view />
  </div>
</template>

<script>
import NaviBar from "./components/layout/NaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style>
html {
  background-color: #86e57f;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
#app {
  background-color: #86e57f;
  background-size: cover;
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
  color: brown;
  text-decoration: none !important;
}

.link {
  color: brown;
}

.link:hover {
  color: brown;
}
</style>
