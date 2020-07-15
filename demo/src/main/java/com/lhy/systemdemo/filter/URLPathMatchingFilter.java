package com.lhy.systemdemo.filter;

import com.lhy.systemdemo.service.permission.PermissionService;
import com.lhy.systemdemo.service.role.RoleService;
import com.lhy.systemdemo.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/3 16:43
 */
@Slf4j
public class URLPathMatchingFilter extends PathMatchingFilter {

    private static PermissionService permissionService;

    private static RoleService roleService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //放行options请求
        if (HttpMethod.OPTIONS.toString().equals((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        if (permissionService == null){
            permissionService = SpringContextUtils.getContext().getBean(PermissionService.class);
        }

        if (roleService == null){
            roleService = SpringContextUtils.getContext().getBean(RoleService.class);
        }

        String requestApi = getPathWithinApplication(request);
        log.info("访问接口：" + requestApi);
        Subject subject = SecurityUtils.getSubject();
        //1.未登录用户禁止访问
        if (!subject.isAuthenticated() && !subject.isRemembered()){
            log.info("用户未登录");
            return false;
        }
        Boolean filterFlag = permissionService.existUrl(requestApi);
        if (!filterFlag){
            log.info("接口:" + requestApi + "不需要访问权限即可访问");
            return true;
        }
        // 2.验证访问权限
        // 2.1判断当前用户是否有相应权限 - 无权限则不允许访问
        String userName = subject.getPrincipal().toString();
        boolean flag = judgePermission(requestApi,userName);
        if (!flag){
            ((HttpServletResponse) response).setStatus(HttpStatus.FORBIDDEN.value());
        }
        return flag;
    }

    /**
     * 判断当前用户是否拥有该连接访问权限
     * @param url 链接
     * @param userName 用户名
     * @return 是否拥有权限
     */
    private boolean judgePermission(String url,String userName){
        boolean permissionFlag = false;
        List<Long> rids = roleService.queryRoleListByUserName(userName);
        Set<String> urls =  permissionService.queryPermissionsByRoleIds(rids);
        if (urls.contains(url)){
            permissionFlag = true;
        }
        return permissionFlag;
    }
}
