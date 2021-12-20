package com.decucin.blog.vo.params;

import lombok.Data;

import java.util.List;

/**
 * @author ：decucin
 * @date ：Created in 2021/12/20 19:26
 * @description：这个类用来保存前端传过来的用户信息
 * @modified By：
 * @version: 1.0$
 */
@Data
public class ArticleParam {

    private String summary;
    private String title;
    private String content;
    private String contentHtml;
    private Long categoryId;
    private List<String> tags;

}
