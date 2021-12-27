package com.decucin.blog.dao.pojo;

import com.decucin.blog.vo.params.CommentParam;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String commentBody;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long fromId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long toId;
    private Boolean level;
    private Date createTime;
    private Integer likeCount;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    public Comment(CommentParam commentParam){
        commentBody = commentParam.getCommentBody();
        toId = commentParam.getToId();
        level = commentParam.getLevel();
    }
}
