package com.lhy.systemdemo.controller;

import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.base.result.ResponseResult;
import com.lhy.systemdemo.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:书籍controller
 *
 * @author Angel
 * @since 2020/5/25 14:49
 */
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/uploadCover")
    public Object uploadCover(MultipartFile file){
        ResponseResult result = ResponseResult.createSuccessResult();
        if (file == null){
            result = ResponseResult.createFailResult("文件为空");
            return result;
        }
        String url;
        try {
            url = bookService.dealCoverImg(file);
        }catch (BusinessRuntimeException e){
            log.error("内部异常",e);
            result = ResponseResult.createFailResult(e.getMessage());
            return result;
        }
        result.setRe(url);
        return result;
    }

}
