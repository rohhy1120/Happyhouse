<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/boardviewimg1.png"
      class="d-inline-block align-middle"
      width="500px"
      alt="Kitten"
    />
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="success" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button variant="success" @click="moveModifyArticle" class="mr-2"
          >질문 수정</b-button
        >
        <b-button variant="success" @click="deleteArticle">질문 삭제</b-button>
      </b-col>
    </b-row>
    <br />
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="transparent"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-col v-if="answer">
      <b-table-simple hover responsive v-if="show" style="border-radius: 16px">
        <b-thead class="table-success">
          <b-tr>
            <b-th>작성자</b-th>
            <b-th>답변</b-th>
            <b-th>작성일</b-th>
          </b-tr>
        </b-thead>
        <tbody>
          <answer-list-row v-bind="answer" />
        </tbody> </b-table-simple
      ><answer-modify-row
        v-if="modify"
        v-bind="answer"
        type="modify"
      /><b-button variant="dark" @click="modifyAnswer">답변 수정</b-button
      >&nbsp;
      <b-button variant="dark" @click="deleteAnswer">답변 삭제</b-button>
    </b-col>
    <b-col v-else class="text-center"
      ><answer-write-form type="answer"
    /></b-col>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/util/http-common";
import AnswerWriteForm from "./child/AnswerWriteForm.vue";
import AnswerListRow from "@/components/board/child/AnswerListRow";
import AnswerModifyRow from "@/components/board/child/AnswerModifyRow";

export default {
  data() {
    return {
      article: {},
      answer: {},
      show: true,
      modify: false,
    };
  },
  components: {
    AnswerWriteForm,
    AnswerListRow,
    AnswerModifyRow,
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  created() {
    console.log(this.$route.params.articleno);
    http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
    });
    http.get(`/memo/${this.$route.params.articleno}`).then(({ data }) => {
      this.answer = data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "BoardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "BoardUpdate",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("삭제하실건가요?")) {
        this.$router.replace({
          name: "BoardDelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    deleteAnswer() {
      if (confirm("삭제하실건가요?")) {
        this.$router.replace({
          name: "AnswerDelete",
          params: { articleno: this.answer.articleno },
        });
      }
    },
    modifyAnswer() {
      this.show = !this.show;
      this.modify = !this.modify;
    },
  },
};
</script>

<style></style>
