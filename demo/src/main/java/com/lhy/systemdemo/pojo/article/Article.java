package com.lhy.systemdemo.pojo.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String articleAbstract;

    private String cover;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    private String contentHtml;

    private String contentMd;

    private Integer status = 1;

}
