<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/qnaimg1.png"
      class="d-inline-block align-middle"
      width="700px"
      alt="Kitten"
    />
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="dark" @click="moveWrite()">질문하기</b-button>
      </b-col>
    </b-row>
    <b-form-input
      v-model="searchName"
      type="text"
      placeholder="제목을 검색하세요."
    ></b-form-input>
    <br />
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive style="border-radius: 16px">
          <b-thead head-variant="success">
            <b-tr class="table-success">
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-row
              v-for="(article, index) in itemsForList"
              :key="index"
              v-bind="article"
              v-bind:index="index"
              :per-page="perPage"
              :current-page="currentPage"
            />
          </tbody> </b-table-simple
        ><b-pagination
          align="center"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
        ></b-pagination>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import BoardListRow from "@/components/board/child/BoardListRow";

export default {
  name: "BoardList",
  components: {
    BoardListRow,
  },
  data() {
    return {
      articles: [],
      perPage: 3,
      currentPage: 1,
      searchName: "",
    };
  },
  computed: {
    rows() {
      return this.articles.length;
    },
    itemsForList() {
      if (this.searchName) {
        return this.articles
          .filter((data) => {
            return data.subject.includes(this.searchName);
          })
          .slice(
            (this.currentPage - 1) * this.perPage,
            this.currentPage * this.perPage
          );
      } else {
        return this.articles.slice(
          (this.currentPage - 1) * this.perPage,
          this.currentPage * this.perPage
        );
      }
    },
  },
  created() {
    http.get(`/board`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "BoardWrite" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.table {
  background-color: white;
}
</style>
