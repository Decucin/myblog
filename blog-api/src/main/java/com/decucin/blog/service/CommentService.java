package com.decucin.blog.service;

import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.CommentParam;

public interface CommentService {

    Result findByArticleId(Long articleId);

    Result likeComment(String token, Long commentId);

    Result notLikeComment(String token, Long commentId);

    Result addCommentToArticle(CommentParam commentParam, Long articleId);

    Result deleteCommentToArticle(Long commentId, Long articleId);
}
