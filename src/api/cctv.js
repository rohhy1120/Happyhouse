import { apiInstance, cctvInstance } from "./index.js";

const api = apiInstance();
const cctv = cctvInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function cctvList(params, success, fail) {
  cctv.get(``, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, cctvList };
