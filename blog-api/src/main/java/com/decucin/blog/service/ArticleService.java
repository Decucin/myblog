package com.decucin.blog.service;

import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.PageParams;

<<<<<<< HEAD
=======
import java.io.IOException;

>>>>>>> master
public interface ArticleService {
    Result findAll(PageParams pageParams);

    Result findArticleByCategory(PageParams pageParams, Long categoryId);

    Result findArticleInfo(Long id);

<<<<<<< HEAD
    Result likeArticle(Long userId, Long articleId);

    Result notLikeArticle(Long userId, Long articleId);
=======
    Result likeArticle(String token, Long articleId);

    Result notLikeArticle(String token, Long articleId);
>>>>>>> master

    Result addCommentCount(Long articleId);

    Result deleteCommentCount(Long articleId);
<<<<<<< HEAD
=======

    Result search(String key) throws IOException;
>>>>>>> master
}
