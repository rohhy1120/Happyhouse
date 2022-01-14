<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/boarddeleteimg1.png"
      class="d-inline-block align-middle"
      width="600px"
      alt="Kitten"
    />
  </b-container>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "BoardDelete",
  created() {
    http.delete(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      let msg = "삭제 중 문제 발생";
      if (data === "success") {
        msg = "삭제 완료";
      }
      alert(msg);
      // 현재 route를 /list로 변경.
      this.$router.push({ name: "BoardList" });
    });
  },
};
</script>

<style></style>
