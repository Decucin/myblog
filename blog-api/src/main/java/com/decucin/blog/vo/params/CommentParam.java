package com.decucin.blog.vo.params;

import lombok.Data;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/24 19:53
 * @description：新增评论时所需数据
 * @modified By：
 * @version: 1.0$
 */
@Data
public class CommentParam {
    private String commentBody;
    private Long fromId;
    private Long toId;
    private Boolean level;
}
