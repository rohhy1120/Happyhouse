<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/boardwriteimg1.png"
      class="d-inline-block align-middle"
      width="500px"
      alt="Kitten"
    />
    <board-write-form type="register" />
  </b-container>
</template>

<script>
import BoardWriteForm from "./child/BoardWriteForm.vue";

export default {
  name: "BoardWrite",
  components: {
    BoardWriteForm,
  },
};
</script>

<style></style>
