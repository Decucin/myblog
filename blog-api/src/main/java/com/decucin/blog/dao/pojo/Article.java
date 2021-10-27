package com.decucin.blog.dao.pojo;

import lombok.Data;

import java.util.Date;


/**
 * @author ：decucin
 * @date ：Created in 2021/10/19 10:36
 * @description：对应数据库表decucin_article
 * @modified By：
 * @version: 1.0$
 */
@Data
public class Article {
    public static final int Article_TOP = 1;
    public static final int Article_Common = 0;

    private Long id;
    private Integer commentCount;
    private Date createDate;
    private String summary;
    private String title;
    private Integer viewCount;
    private Integer weight;
    private Long authorId;
    private Long bodyId;
    private Long categoryId;
    private Integer likeCount;

}
