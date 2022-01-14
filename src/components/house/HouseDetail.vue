<template>
  <b-container v-if="house" class="bv-example-row">
    <div>
      <img
        src="@/assets/aptdetailimg1.png"
        class="d-inline-block align-left"
        width="500px"
        alt="Kitten"
      />
    </div>
    <br />
    <b-row>
      <b-col
        ><h3>{{ house.아파트 }}</h3></b-col
      >
    </b-row>
    <br />
    <b-row>
      <b-table-simple hover responsive style="border-radius: 16px">
        <b-tr>
          <b-th class="table-success">일련번호</b-th>
          <b-td>{{ house.일련번호 }}</b-td>
        </b-tr>
        <b-tr>
          <b-th class="table-success">법정동</b-th>
          <b-td>{{ house.법정동 }}</b-td>
        </b-tr>
        <b-tr>
          <b-th class="table-success">층수</b-th>
          <b-td>{{ house.층 }}층</b-td>
        </b-tr>
        <b-tr>
          <b-th class="table-success">거래금액</b-th>
          <b-td
            >{{
              (parseInt(house.거래금액.replace(",", "")) * 10000) | price
            }}원</b-td
          >
        </b-tr>
        <!-- <b-tr class="table-success">
            <b-th>일련번호</b-th>
            <b-th>법정동</b-th>
            <b-th>층수</b-th>
            <b-th>거래금액</b-th>
          </b-tr>
          <b-tr>
            <b-th>{{ house.일련번호 }}</b-th>
            <b-td>{{ house.법정동 }}</b-td>
            <b-td>{{ house.층 }}층</b-td>
            <b-td>
              {{
                (parseInt(house.거래금액.replace(",", "")) * 10000) | price
              }}원
            </b-td>
          </b-tr> -->
      </b-table-simple>
    </b-row>
    <b-row> </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  data() {
    return {
      map: null,
      markerPositions1: [],
      markers: [],
      infowindow: null,
    };
  },
  updated() {
    this.initMap();
    this.add = this.house.법정동 + " " + this.house.지번;
    this.apt = this.house.아파트;
    this.geocoder = new kakao.maps.services.Geocoder();
    this.geocoder.addressSearch(this.add, (result, status) => {
      // 정상적으로 검색이 완료됐으면
      if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        this.markerPositions1.push(coords);
        var marker = new kakao.maps.Marker({
          map: this.map,
          position: coords,
        });
        marker.setMap(this.map);
        var customOverlay = new kakao.maps.CustomOverlay({
          map: this.map,
          position: marker.getPosition(),
          content:
            '<div class="placeinfo_wrap" style="position: absolute; bottom: 28pxleft: -150px; width: 300px">' +
            '<div class="placeinfo" style="position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background: #fff;">' +
            '   <p class="title" href="' +
            '" style="font-weight: bold; font-size:14px;border-radius: 6px 6px 0 0;margin: -1px -1px 0 -1px;padding:10px; color: #fff;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center; display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">' +
            this.house.아파트 +
            "</p>" +
            "</span>" +
            "    <span " +
            '" style="display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap; margin:5px 5px 0 5px;cursor: default;font-size:13px;"> 법정동 : ' +
            this.house.법정동 +
            "</span>" +
            "    <span " +
            '" style="display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap; margin:5px 5px 0 5px;cursor: default;font-size:13px;"> 층수 : ' +
            this.house.층 +
            "층</span>" +
            "    <span " +
            '" style="display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap; margin:5px 5px 0 5px;cursor: default;font-size:13px;"> 가격 : ' +
            this.house.거래금액 +
            "만원</span>" +
            "</div>" +
            '<div class="after" style="content:"";position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png")"></div>' +
            "</div>",
          yAnchor: 1,
        });
        // var infowindow = new kakao.maps.InfoWindow({
        //   content:
        //     '<div style="bottom: 28pxleft: -150px; width: 300px">' +
        //     '<div style="position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background: #fff;">' +
        //     '    <span title="' +
        //     this.house.아파트 +
        //     '" style="display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap; margin:5px 5px 0 5px;cursor: default;font-size:13px;">' +
        //     this.house.아파트 +
        //     "</span>" +
        //     '  <span class="jibun" title="' +
        //     this.house.아파트 +
        //     '" style="display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap; margin:5px 5px 0 5px;cursor: default;font-size:13px; color:#999;font-size:11px;margin-top:0;">(지번 : ' +
        //     this.house.아파트 +
        //     ")</span>" +
        //     '    <span class="tel" style="color:#0f7833;display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap; margin:5px 5px 0 5px;cursor: default;font-size:13px;">' +
        //     this.house.아파트 +
        //     "</span>" +
        //     "</div>" +
        //     '<div class="after" style="content:"";position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png")"></div>' +
        //     "</div>",
        // });
        // infowindow.open(this.map, marker);
        customOverlay.setMap(this.map);
        this.map.setCenter(coords);
      }
    });
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map2");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style>
#map2 {
  width: 1150px;
  height: 500px;
}
</style>
