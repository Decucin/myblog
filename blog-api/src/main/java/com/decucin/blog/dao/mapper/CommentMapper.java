package com.decucin.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.decucin.blog.dao.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    Boolean selectIfLike(Long userId, Long commentId);

    Long insertLike(Long userId, Long commentId);

    Long updateLike(Long userId, Long commentId);

    Long cancelLike(Long userId, Long commentId);
}
