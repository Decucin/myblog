package com.decucin.blog.dao.pojo;

import lombok.Data;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/19 10:53
 * @description：对应数据库表decucin_category
 * @modified By：
 * @version: 1.0$
 */
@Data
public class Category {
    private Long id;
    private String classifyName;
    private String introduction;
}
