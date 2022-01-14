<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/cctvimg1.png"
      class="d-inline-block align-middle"
      width="700px"
      alt="Kitten"
    />
    <b-row class="mb-1">
      <b-col class="sm-3">
        <img
          src="@/assets/aptinfoimg2.png"
          class="align-left"
          width="100px"
          alt="Kitten"
        />
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <img
          src="@/assets/aptinfoimg3.png"
          class="align-left"
          width="100px"
          alt="Kitten"
        />
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="gugunNames"
        ></b-form-select>
      </b-col>
    </b-row>
    <br />
    <b-row>
      <b-col>
        <!-- <b-table-simple hover responsive>
>>>>>>> 49593d3af76844ee61f34dc205966dd1f31f22ab
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>CCTV관리청</b-th>
              <b-th>CCTV위치</b-th>
              <b-th>관리 번호</b-th>
              <b-th>설치년도</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <cctv-list-row
              v-for="(cctv, index) in itemsForList"
              :key="index"
              v-bind="cctv"
            />
          </tbody>
        </b-table-simple> -->
        <b-table-simple hover responsive style="border-radius: 16px">
          <b-thead head-variant="success">
            <b-tr class="table-success">
              <b-th>CCTV관리청</b-th>
              <b-th>CCTV위치</b-th>
              <b-th>관리 번호</b-th>
              <b-th>설치년도</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <cctv-list-row
              v-for="(cctv, index) in itemsForList"
              :key="index"
              v-bind="cctv"
            />
          </tbody>
        </b-table-simple>
        <b-pagination
          align="center"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="my-table"
        ></b-pagination>
      </b-col>
      <div id="map" style="border-radius: 4em"></div>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import CctvListRow from "@/components/cctv/CctvListRow";
const cctvStore = "cctvStore";
export default {
  name: "CctvList",
  data() {
    return {
      sidoName: "",
      gugunName: "",
      sidoCode: null,
      gugunCode: null,
      perPage: 3,
      currentPage: 1,
    };
  },
  components: {
    CctvListRow,
  },
  computed: {
    ...mapState(cctvStore, ["cctvs", "sidos", "guguns"]),
    rows() {
      return this.cctvs.length;
    },
    itemsForList() {
      return this.cctvs.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      );
    },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=edb8fd787effaa06e51047e32092b808&libraries=services";
      document.head.appendChild(script);
    }
  },
  updated() {
    this.initMap();
    this.seachCctv(this.cctvs);
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.ps = new kakao.maps.services.Places(this.map);
      this.customOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    },
    seachCctv(data) {
      var bounds = new kakao.maps.LatLngBounds();
      for (var i = 0; i < data.length; i++) {
        this.displayCCTV(data[i]);
        bounds.extend(
          new kakao.maps.LatLng(data[i].latitude, data[i].longitude)
        );
      }
      this.map.setBounds(bounds);
    },
    displayCCTV(cctv) {
      // 마커를 생성하고 지도에 표시합니다
      var imageSrc = "https://cdn-icons-png.flaticon.com/512/3583/3583045.png";
      var imageSize = new kakao.maps.Size(24, 24);
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(cctv.latitude, cctv.longitude),
        title: cctv.lnmadr,
        image: markerImage,
      });
      marker.setMap(this.map);
    },
    ...mapActions(cctvStore, [
      "getSido",
      "getGugun",
      "getCctvList",
      "detailCctv",
    ]),
    ...mapMutations(cctvStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "SET_CCTV_LIST",
      "SET_DETAIL_CCTV",
    ]),
    gugunList() {
      for (let i = 0; i < this.sidos.length; i++) {
        if (this.sidos[i].value == this.sidoCode)
          this.sidoName = this.sidos[i].text;
      }
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    gugunNames() {
      for (let i = 0; i < this.guguns.length; i++) {
        if (this.guguns[i].value == this.gugunCode)
          this.gugunName = this.guguns[i].text;
      }
      var add = this.sidoName + " " + this.gugunName + "청";
      var add1 = this.sidoName + " " + this.gugunName;
      this.getCctvList(add);
      this.getCctvList(add1);
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
#map {
  width: 850px;
  height: 400px;
}
</style>
