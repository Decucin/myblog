package com.decucin.blog.service;

import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.PageParams;

import java.io.IOException;


public interface ArticleService {
    Result findAll(PageParams pageParams);

    Result findArticleByCategory(PageParams pageParams, Long categoryId);

    Result findArticleInfo(Long id);

    Result likeArticle(String token, Long articleId);

    Result notLikeArticle(String token, Long articleId);

    Result addCommentCount(Long articleId);

    Result deleteCommentCount(Long articleId);

    Result search(String key) throws IOException;
}
