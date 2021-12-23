package com.decucin.blog.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/24 19:53
 * @description：新增评论时所需数据
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentParam {
    private String commentBody;
    private Long toId;
    private Boolean level;
}
