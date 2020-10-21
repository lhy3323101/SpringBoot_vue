import request from '../utils/request';
import ar from "element-ui/src/locale/lang/ar";

let loginUrl = '/login';
let menuUrl = '/menu';
let adminUrl = '/admin';
let userUrl = '/user';
let articleUrl = '/article';
let permissionUrl = '/permission';

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
  initUserList:param =>{return request.post(`${adminUrl}/queryUsers`,param)},
  queryUsers:param =>{return request.post(`${adminUrl}/queryUsers`,param)},
  queryRoles:param =>{return request.post(`${adminUrl}/queryUsers`,param)},
  initRoles:param =>{return request.post(`${adminUrl}/queryRoles`,param)},
  saveUser:param => {return request.post(`${adminUrl}/saveUser`,param)},
  changeUserStatus:param =>{return request.post(`${adminUrl}/changeUserStatus`,param)},
  initPermissions:param => {return request.post(`${adminUrl}/initPermissions`,param)},
  initMenus:param => {return request.post(`${adminUrl}/initMenus`,param)},
  changeRoleStatus:param => {return request.post(`${adminUrl}/changeRoleStatus`,param)},
  updateRole:param => {return request.post(`${adminUrl}/updateRole`,param)},
  addPermission:param => {return request.post(`${adminUrl}/addPermission`,param)}
}

export const article ={
  saveArticle:param =>{return request.post(`${articleUrl}/saveArticle`,param)},
  queryList:param =>{return request.post(`${articleUrl}/queryList`,param)},
  initArticleList:param => {return request.post(`${articleUrl}/queryList`,param)},
  loadArticle:param => {return request.post(`${articleUrl}/loadArticle`,param.id)},
  deleteArticle:param => {return request.post(`${articleUrl}/deleteArticle`,param.id)}
}

export const video = {
  preview:param => {return request.get("video/preview",param)}
}
