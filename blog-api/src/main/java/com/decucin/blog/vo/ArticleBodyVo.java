package com.decucin.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 21:35
 * @description：这个类用来保存文章主体的简略信息
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBodyVo {

    private Long id;
    private String body;

}
