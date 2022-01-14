<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/boardupdateimg1.png"
      class="d-inline-block align-middle"
      width="500px"
      alt="Kitten"
    />
    <board-write-form type="modify" />
  </b-container>
</template>

<script>
import BoardWriteForm from "./child/BoardWriteForm.vue";

export default {
  name: "BoardUpdate",
  components: {
    BoardWriteForm,
  },
};
</script>

<style></style>
