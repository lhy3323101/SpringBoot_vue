package com.lhy.systemdemo.service.image;

import java.awt.image.BufferedImage;

/**
 * Description:图片service
 *
 * @author haoyuan.li@joymo.tech
 * @since 2021/4/13 9:55
 */
public interface ImageService {

    /**
     * 获取验证码图片
     * @param uuid 唯一标识
     * @return 验证码图片
     */
    BufferedImage getValidImage(String uuid);
}
