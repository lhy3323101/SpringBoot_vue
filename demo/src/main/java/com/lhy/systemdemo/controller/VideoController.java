package com.lhy.systemdemo.controller;

import com.lhy.systemdemo.service.video.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:视频相关操作
 *
 * @since 2020/10/16 10:53
 */
@RequestMapping("/video")
@RestController
@Slf4j
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/preview/{movieName}")
    public void videoPreview(HttpServletRequest request, HttpServletResponse response, @PathVariable("movieName") String movieName){
        log.error("进入视频播放调用");
        try {
            videoService.preView(request,response,movieName);
        }catch (Exception e){
             log.error("播放视频异常,message:" + e.getMessage());
        }
    }




}
