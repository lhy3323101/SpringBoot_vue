import request from '../utils/request';

let loginUrl = '/login';

export const api = {

  /**登入api*/
  loginTest: param =>{return request.post(`${loginUrl}/test`,param)},
  /**登录*/
  login:param =>{return request.post(`${loginUrl}/login`,param)},
  /**注册*/
  register:param =>{return request.post(`${loginUrl}/register`,param)},
  /**登出*/
  logout:param =>{return request.post(`${loginUrl}/logout`,param)},
  /** 校验用户登录状态*/
  authentication:param=>{return request.post(`${loginUrl}/authentication`,param)}
}
