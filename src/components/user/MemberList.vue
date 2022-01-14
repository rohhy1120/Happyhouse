<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/adminimg1.png"
      class="d-inline-block align-middle"
      width="600px"
      alt="Kitten"
    />
    <b-row>
      <b-col v-if="users.length">
        <b-table-simple hover responsive style="border-radius: 16px">
          <b-thead class="table-success">
            <b-tr>
              <b-th class="col-1">아이디</b-th>
              <b-th class="col-2">이름</b-th>
              <b-th class="col-2">비밀번호</b-th>
              <b-th>이메일</b-th>
              <b-th>가입일</b-th>
              <b-th class="col-1">탈퇴유무</b-th>
              <b-th>수정/삭제</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <member-list-row
              v-for="(user, index) in itemsForList"
              :key="index"
              v-bind="user"
            />
          </tbody> </b-table-simple
        ><b-pagination
          align="center"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="my-table"
        ></b-pagination>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import MemberListRow from "./child/MemberListRow";

export default {
  name: "MemberList",
  components: {
    MemberListRow,
  },
  data() {
    return {
      users: [],
      perPage: 5,
      currentPage: 1,
    };
  },
  computed: {
    rows() {
      return this.users.length;
    },
    itemsForList() {
      return this.users.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      );
    },
  },
  created() {
    http.get(`/user`).then(({ data }) => {
      this.users = data;
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
.row {
  margin-right: -50px;
}
</style>
