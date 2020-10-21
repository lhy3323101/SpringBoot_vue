package com.lhy.systemdemo.service.video;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 *
 * @since 2020/10/19 10:01
 */
public interface VideoService {

    void preView(HttpServletRequest request, HttpServletResponse response,String movieName);
}
