<template>
  <b-container class="bv-example-row mt-3">
    <!-- <img
      src="@/assets/answerdeleteimg1.png"
      class="d-inline-block align-middle"
      width="600px"
      alt="Kitten"
    /> -->
  </b-container>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "AnswerDelete",
  created() {
    http.delete(`/memo/${this.$route.params.articleno}`).then(({ data }) => {
      let msg = "삭제 중 문제가 발생했습니다.";
      if (data === "success") {
        msg = "삭제가 완료되었습니다.";
      }
      alert(msg);
      // 현재 route를 /list로 변경.
      this.$router.push({ name: "BoardList" });
    });
  },
};
</script>

<style></style>
