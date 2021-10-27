package com.decucin.blog.dao.pojo;

import com.decucin.blog.vo.params.CommentParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/19 10:55
 * @description：对应数据库表decucin_comment
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String commentBody;
    private Long fromId;
    private Long toId;
    private Boolean level;
    private Date createTime;
    private Integer likeCount;
    private Long articleId;

    public Comment(CommentParams commentParams){
        commentBody = commentParams.getCommentBody();
        fromId = commentParams.getFromId();
        toId = commentParams.getToId();
        level = commentParams.getLevel();
    }
}
