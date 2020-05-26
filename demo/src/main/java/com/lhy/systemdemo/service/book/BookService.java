package com.lhy.systemdemo.service.book;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description:书籍service
 *
 * @author Angel
 * @since 2020/5/25 14:51
 */
public interface BookService {

    /**
     * 处理书籍封面
     * @param file 书籍封面
     * @return 处理后的书籍封面图片保存url
     */
    String dealCoverImg(MultipartFile file);
}
