import { appStoreActions } from "./appStoreActions";
export const appStore = {
  namespaced: true,
  state: {
    accessToken: "", // null
    userInfo: {
      email: '',
      name: '',
      userType: 0,
    },
  },
  getters: {
    accessToken: (state) => {
      if (state.accessToken === undefined || state.accessToken === null || state.accessToken === "") {
        const jsonStr = localStorage.getItem("accessToken");
        if (jsonStr === undefined || jsonStr === null) {
          state.accessToken = null;
          return state.accessToken;
        } else {
          state.accessToken = JSON.parse(jsonStr);
        }
      }
      return state.accessToken;
    },
    userInfo: state => {
      return state.userInfo;
    },
  },
  mutations: {
    accessToken: (state, accessToken) => {
      if (accessToken === undefined || accessToken === null) {
        localStorage.removeItem("accessToken");
      } else {
        localStorage.setItem("accessToken", JSON.stringify(accessToken));
      }
      state.accessToken = accessToken;
    },
    userInfo: (state, userInfo) => {
      state.userInfo = userInfo;
    },
  },
  actions: appStoreActions,
};