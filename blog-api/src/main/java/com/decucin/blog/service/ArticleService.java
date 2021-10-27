package com.decucin.blog.service;

import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.PageParams;

public interface ArticleService {
    Result findAll(PageParams pageParams);

    Result findArticleByCategory(PageParams pageParams, Long categoryId);

    Result findArticleInfo(Long id);

    Result likeArticle(Long userId, Long articleId);

    Result notLikeArticle(Long userId, Long articleId);

    Result addCommentCount(Long articleId);

    Result deleteCommentCount(Long articleId);
}
