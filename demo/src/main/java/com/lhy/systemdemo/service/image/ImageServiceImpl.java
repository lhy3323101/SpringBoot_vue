package com.lhy.systemdemo.service.image;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.lhy.systemdemo.base.constant.BaseConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * Description:图片service实现类
 *
 * @author haoyuan.li@joymo.tech
 * @since 2021/4/13 9:56
 */
@Service
public class ImageServiceImpl implements ImageService {



    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Override
    public BufferedImage getValidImage(String uuid) {
        //1.生成验证码数字
        String validCode = defaultKaptcha.createText();
        //2.生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(validCode);
        //3.使用redis保存验证码，有效时间为60秒
        redisTemplate.opsForValue().set(BaseConstant.IMAGE + uuid, validCode,2, TimeUnit.MINUTES);
        return image;
    }
}
