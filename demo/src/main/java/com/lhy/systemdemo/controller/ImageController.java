package com.lhy.systemdemo.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.lhy.systemdemo.base.result.ResponseResult;
import com.lhy.systemdemo.service.image.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * Description:
 *
 * @author haoyuan.li@joymo.tech
 * @since 2021/4/13 9:40
 */
@RestController
@RequestMapping("/image")
@Slf4j
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping("/getValidImage")
    public void getValidImage(HttpServletRequest request, HttpServletResponse response){
        ResponseResult result = ResponseResult.createSuccessResult();
        String uuid = request.getParameter("uuid");
        try {
            BufferedImage image = imageService.getValidImage(uuid);
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setContentType("image/jpeg");
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image,"jpg",out);
            out.flush();
            out.close();
        }catch (Exception e){
            log.error("内部异常",e);
        }
    }
}
