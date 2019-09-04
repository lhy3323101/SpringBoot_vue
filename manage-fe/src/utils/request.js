import axios from 'axios';
import { Message } from 'element-ui';

const service = axios.create({
    // process.env.NODE_ENV === 'development' 来判断是否开发环境
    baseURL: process.env.NODE_ENV === 'development' ? 'http://localhost:8081/' : '',
    timeout: 5000,
    headers: {
        'content-type': 'application/json',
    }
})

/*
service.interceptors.request.use( config => {
    return config;
}, error => {
    console.log(error);
    return Promise.reject();
})

service.interceptors.response.use(response => {
    if(response.status === 200){
        return response.data;
    }else{
        Promise.reject();
    }
}, error => {
    console.log(error);
    return Promise.reject();
})
*/



export default {
    get(url,param) {
        return new Promise((callback,reject) => {
            service({
                method:'get',
                url,
                param:param,
            })
                .then(res =>{
                    var res_code = res.status.toString();
                    if (res_code.charAt(0) == 2){
                        callback(res); //callback在Promise执行器内部
                    } else {
                        console.log(res,'异常1')
                    }
                })
                .catch(err => {
                    if (!err.response){
                        console.log('请求错误');
                        Message({
                            showClose:true,
                            message:'请求错误',
                            type:'error'
                        });
                    }else {
                        reject(err.response);
                        console.log(err.response,'异常2')
                    }
                })
        })
    },
    post(url,param) {
        return new Promise((callback,reject) => {
            service({
                method:'post',
                url,
                param:param,
            })
                .then(res =>{
                    var res_code = res.status.toString();
                    if (res_code.charAt(0) == 2){
                        callback(res); //callback在Promise执行器内部
                    } else {
                        console.log(res,'异常1')
                    }
                })
                .catch(err => {
                    if (!err.response){
                        console.log('请求错误');
                        Message({
                            showClose:true,
                            message:'请求错误',
                            type:'error'
                        });
                    }else {
                        reject(err.response);
                        console.log(err.response,'异常2')
                    }
                })
        })
    }
};
