import axios from '../plugins/axios';
import qs from 'query-string';

export class ApiService {
  setAccessToken(accessToken) {
    axios.defaults.headers.common['Authorization'] = accessToken;
  }
  setRefreshToken(refreshToken) {
    axios.defaults.headers.common["RefreshToken"] = refreshToken;
  }
  addCount(req) {
    return new Promise((resolve, reject) => {
      axios.post('/api/v1/add', req).then(res => {
        if (res.status === 200) {
          resolve(res.data);
          return;
        }

        console.error(res.status, res.statusText);
        reject(null);
      })
      .catch((err) => {
        reject(err);
      })
    });
  }
  signIn(req) {
    return new Promise((resolve, reject) => {
      axios.post('/api/v1/auth/signin', req).then(res => {
        if (res.status === 200) {
          resolve(res.data);
          return;
        }
        
        console.error(res.status, res.statusText);
        reject(null);
      })
      .catch((err) => {
        reject(err);
      })
    });
  }
  signUp(req) {
    return new Promise((resolve, reject) => {
      axios.post('/api/v1/auth/signup', req).then(res => {
        if (res.status === 200) {
          resolve(res.data);
          return;
        }
        console.error(res.status, res.statusText);
        reject(null);
      })
      .catch((err) => {
        reject(err);
      })
    });
  }
  getProfile(req) {
    const url = '/api/v1/user/profile?' + qs.stringify(req);
    return new Promise((resolve, reject) => {
      axios.get(url).then(res => {
        if (res.status === 200) {
          resolve(res.data);
          return;
        }

        console.error(res.status, res.statusText);
        reject(null);
      })
      .catch((err) => {
        reject(err);
      })
    });
  }
  getItemsList(req) {
    return new Promise((resolve, reject) => {
      axios.get('/api/v1/item/user-items', req).then(res => {
        if (res.status === 200) {
          resolve(res.data);
          return;
        }

        console.error(res.status, res.statusText);
        reject(null);
      })
      .catch((err) => {
        reject(err);
      })
    });
  }
  itemDetail(req) {
    const url = '/api/v1/item/item-details?' + qs.stringify(req);
    return new Promise((resolve, reject) => {
      axios.get(url).then(res => {
        if (res.status === 200) {
          resolve(res.data);
          return;
        }
        console.error(res.status, res.statusText);
        reject(null);
      })
      .catch((err) => {
        reject(err);
      })
    });
  }
<<<<<<< HEAD
  getProductsList(req) {
    return new Promise((resolve, reject) => {
      axios.get('/api/v1/seller/product/list', req).then(res => {
        if (res.status === 200) {
          resolve(res.data);
=======

  mainPage() {
    return new Promise((resolve, reject) => {
      axios.get('/api/v1/item/mainpage').then(res => {
        if (res.status === 200) {
          resolve(res.data);
          console.log(res.data);
>>>>>>> d30866524399b4551e43ea73e42a8e70113c2fc2
          return;
        }

        console.error(res.status, res.statusText);
        reject(null);
      })
<<<<<<< HEAD
      .catch((err) => {
        reject(err);
      })
    });
  }
=======
          .catch((err) => {
            reject(err);
          })
    });
  }

>>>>>>> d30866524399b4551e43ea73e42a8e70113c2fc2
}
const _api = new ApiService(axios);
export default {
  install: (app) => {
    app.config.globalProperties.$api = _api;
    app.config.globalProperties.api = _api;
  }
}
