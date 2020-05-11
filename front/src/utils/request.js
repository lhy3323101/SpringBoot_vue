import axios from 'axios';

const service = axios.create({
  // process.env.NODE_ENV === 'development' 来判断是否开发环境
  baseURL: process.env.NODE_ENV === 'development' ? 'http://localhost:8080/' : '',
  timeout: 10000,
  headers: {
    'content-type': 'application/json',
  }
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
