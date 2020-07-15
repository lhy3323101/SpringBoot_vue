import request from '../utils/request';

let loginUrl = '/login';
let menuUrl = '/menu';
let adminUrl = '/admin';
let userUrl = '/user';

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
  authentication:param=>{return request.post(`${loginUrl}/authentication`,param)},
  /** 获取当前用户菜单权限列表 */
  queryMenus:() => {return request.get(`${adminUrl}/queryMenus`,{})}
}
export const admin = {
  queryUsers:() =>{return request.get(`${adminUrl}/queryUsers`,{})},
  saveArticle:param =>{return request.post(`${adminUrl}/saveArticle`,param)}
}
