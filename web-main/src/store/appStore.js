import { appStoreActions } from "./appStoreActions";
export const appStore = {
  namespaced: true,
  state: {
    accessToken: "", // null
    refreshToken: "",
    userInfo: {
      birth: '',
      email: '',
      gender: '',
      name: '',
      phone: '',
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
    refreshToken: (state) => {
      if(state.refreshToken === undefined || state.refreshToken === null || state.refreshToken === "") {
         const jsonStr = localStorage.getItem("refreshToken");
        if (jsonStr === undefined || jsonStr === null) {
          state.refreshToken = null;
          return state.refreshToken;
        } else {
          state.refreshToken = JSON.parse(jsonStr);
        }
      }
      return state.refreshToken;
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
    refreshToken: (state, refreshToken) => {
      if(refreshToken === undefined || refreshToken === null) {
        localStorage.removeItem("refreshToken");
      } else {
        localStorage.setItem("refreshToken", JSON.stringify(refreshToken));
      }
      state.refreshToken = refreshToken;
    },
  },
  actions: appStoreActions,
};