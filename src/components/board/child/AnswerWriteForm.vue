<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            v-model="userid"
            type="text"
            required
            placeholder="작성자를 입력하세요."
          ></b-form-input>
        </b-form-group>
        <b-form-group id="comment-group" label="내용:" label-for="comment">
          <b-form-textarea
            id="comment"
            v-model="article.comment"
            placeholder="내용을 입력하세요."
            rows="2"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <b-button
          type="submit"
          variant="dark"
          class="m-1"
          v-if="this.type === 'answer'"
          >답변 작성</b-button
        >
        <b-button type="reset" variant="dark" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "AnswerWriteForm",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        comment: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "answer") {
      http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        // this.article.articleno = data.article.articleno;
        // this.article.userid = data.article.userid;
        // this.article.subject = data.article.subject;
        // this.article.content = data.article.content;
        this.article = data;
      });
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.article.comment &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.comment.focus());

      if (!err) alert(msg);
      else this.answerArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.userid = "";
      this.article.comment = "";
      this.$router.push({ name: "BoardView" });
    },
    answerArticle() {
      http
        .post(`/memo`, {
          userid: this.article.userid,
          comment: this.article.comment,
          articleno: this.article.articleno,
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
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style></style>
