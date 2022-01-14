import { sidoList, gugunList, cctvList } from "@/api/cctv.js";
const cctvStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    sidoNames: [],
    cctvs: [],
    cctv: null,
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    SET_CCTV_LIST: (state, cctvs) => {
      //console.log(cctvs);
      state.cctvs = cctvs;
    },
    SET_DETAIL_CCTV: (state, cctv) => {
      state.cctv = cctv;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getCctvList: ({ commit }, add) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      const SERVICE_KEY =
        "ZKcqG6latvlKmqOl7UW7UZcg2wz0X67jnQS%2FK8TfXW5zM%2Fd8bRWr1mm8yj0ZgtMqU8L3RrLHUAZW92W%2FZvllRw%3D%3D";
      const params = {
        pageNo: "0",
        numOfRows: "40",
        institutionNm: add,
        type: "json",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      cctvList(
        params,
        (response) => {
          console.log(response.data.response.body.items);
          commit("SET_CCTV_LIST", response.data.response.body.items);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailCctv: ({ commit }, cctv) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_CCTV", cctv);
    },
  },
};

export default cctvStore;
