<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/joinimg1.png"
      class="d-inline-block align-middle"
      width="400px"
      alt="Kitten"
    />
    <register-form />
  </b-container>
</template>

<script>
import RegisterForm from "./child/RegisterForm.vue";
export default {
  name: "MemberJoin",
  components: {
    RegisterForm,
  },
};
</script>

<style></style>
