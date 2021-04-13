package com.lhy.systemdemo.service.book;

import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.utils.ImgUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/5/25 14:51
 */
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Override
    public String dealCoverImg(MultipartFile file) {
        String folder = "D:/Person/Project/front_back/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, ImgUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        try{
            file.transferTo(f);
            return "http://localhost:8082/book/file/" + f.getName();
        }catch (IOException e){
            log.error("图片转复制失败",e);
            throw new BusinessRuntimeException("内部系统错误，请联系管理员");
        }
    }
}
