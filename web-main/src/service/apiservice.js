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
  deleteUser(req) {
    return new Promise((resolve, reject) => {
      axios.delete("/api/v1/auth/withdrawal", {data : req}).then(res => {
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
  updateProfile(req) {
    return new Promise((resolve, reject) => {
      axios.patch('/api/v1/user/profile', req).then(res => {
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
    const url = '/api/v1/item/user-items?' + qs.stringify(req);
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
  search(req) {
    const url = '/api/v1/search/search?' + qs.stringify(req);
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
  addLike(req) {
    return new Promise((resolve, reject) => {
      axios.post("/api/v1/user/wishs", req).then(res => {
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
  deleteLike(req) {
    return new Promise((resolve, reject) => {
      axios.delete("/api/v1/user/wishs", {data : req}).then(res => {
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
  getLikeList(req) {
    return new Promise((resolve, reject) => {
      axios.get("/api/v1/user/wishs", req).then(res => {
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
  addProduct(req) {
    return new Promise((resolve, reject) => {
      axios.post('/api/v1/seller/product', req).then(res => { 
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
  getProductsList(req) {
    const url = "/api/v1/seller/product/list?" + qs.stringify(req);
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
  deleteProduct(req) {
    return new Promise((resolve, reject) => {
      axios.delete("/api/v1/seller/product/delete", {data : req}).then(res => {
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
  productDetail(req) {
    const url = "/api/v1/seller/product/modify?" + qs.stringify(req);
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
  updateProduct(req) {
    return new Promise((resolve, reject) => {
      axios.put('/api/v1/seller/product/modify', req).then(res => {
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
  getCartList(req) {
    return new Promise((resolve, reject) => {
      axios.get('/api/v1/cart/cart-info', req).then(res => {
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
  
  addCart(req) {
    return new Promise((resolve, reject) => {
      axios.post('/api/v1/cart/cartin', req).then(res => {
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

  deleteCart(req) {
    return new Promise((resolve, reject) => {
      axios.delete('/api/v1/cart/cart-delete', {data : req}).then(res => {
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

  updateCart(req) {
    return new Promise((resolve, reject) => {
      axios.patch('/api/v1/cart/cart-update', req).then(res => {
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

  mainPage() {
    return new Promise((resolve, reject) => {
      axios.get('/api/v1/item/mainpage').then(res => {
        if (res.status === 200) {
          resolve(res.data);
          console.log(res.data);
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

}
const _api = new ApiService(axios);
export default {
  install: (app) => {
    app.config.globalProperties.$api = _api;
    app.config.globalProperties.api = _api;
  }
}
