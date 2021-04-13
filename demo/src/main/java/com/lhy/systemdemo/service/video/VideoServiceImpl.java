package com.lhy.systemdemo.service.video;

import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.utils.NoStaticResourceHttpRequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description:影音处理类
 *
 * @since 2020/10/19 10:01
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService{

    @Autowired
    private NoStaticResourceHttpRequestHandler noStaticResourceHttpRequestHandler;

    @Override
    public void preView(HttpServletRequest request, HttpServletResponse response, String movieName) {
        String sourcePath = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
        String name = StringUtils.defaultString("123");
        //判断是否有传入预览片名
        if (StringUtils.isNotBlank(movieName)){
            name = movieName;
        }
        String realPath = sourcePath + "video/" + name + ".mp4";
        Path filePath = Paths.get(realPath);
        if (Files.exists(filePath)){
            try {
                String mineType = Files.probeContentType(filePath);
                if (StringUtils.isNotBlank(mineType)){
                    response.setContentType(mineType);
                }
                request.setAttribute(NoStaticResourceHttpRequestHandler.ATTR_FILE,filePath);
                noStaticResourceHttpRequestHandler.handleRequest(request,response);
            } catch (Exception e) {
                log.error("在线预览播放异常",e);
                throw new BusinessRuntimeException("在线预览播放异常");
            }
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }
}
