package com.decucin.blog.service;

import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.ArticleParam;
import com.decucin.blog.vo.params.PageParam;

import java.io.IOException;


public interface ArticleService {
    Result findAll(PageParam pageParam);

    Result findArticleByCategory(PageParam pageParam, Long categoryId);

    Result findArticleInfo(Long id);

    Result likeArticle(String token, Long articleId);

    Result notLikeArticle(String token, Long articleId);

    Result addCommentCount(Long articleId);

    Result deleteCommentCount(Long articleId);

    Result search(String key) throws IOException;

    Result addArticle(String token, ArticleParam articleParam);
}
