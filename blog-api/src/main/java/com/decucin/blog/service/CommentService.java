package com.decucin.blog.service;

import com.decucin.blog.dao.pojo.Comment;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.CommentParams;

public interface CommentService {

    Result findByArticleId(Long articleId);

    Result likeComment(Long userId, Long commentId);

    Result notLikeComment(Long userId, Long commentId);

    Result addCommentToArticle(CommentParams commentParams, Long articleId);

    Result deleteCommentToArticle(Long commentId, Long articleId);
}
