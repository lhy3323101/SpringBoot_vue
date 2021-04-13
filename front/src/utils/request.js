import axios from 'axios';
import router from '../router'
import store from '../store'

const service = axios.create({
  // process.env.NODE_ENV === 'development' 来判断是否开发环境
  baseURL: process.env.NODE_ENV === 'development' ? 'http://localhost:8082/' : 'http://114.55.171.17:8082/',
  timeout: 10000,
  headers: {
    'content-type': 'application/json',
  },
  withCredentials: true
})

service.interceptors.request.use(
  (config) => {
    if (config.method === 'post' || config.method === 'put'){
      config.data = JSON.stringify(config.data);
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
)
service.interceptors.response.use(
  response => {
    return response
},
  error => {
    if (error) {
      alert("登录过期或无访问权限")
      store.commit('logout')
      router.replace('/login').catch(error =>{
      })
    }
    // 返回接口返回的错误信息
    return Promise.reject(error)
  })



export default {
  get(url,param) {
    return new Promise((callback,reject) => {
      service.get(url,param)
        .then(response => {
            callback(response.data);
          },
          err => {
            reject(err);
          }
        )
    })
  },
  post(url,param) {
    return new Promise((callback,reject) => {
      service.post(url,param)
        .then(response => {
            callback(response.data);
          },
          err => {
            reject(err);
          }
        )
    })
  }
};
