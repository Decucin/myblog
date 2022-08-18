package com.decucin.blog.service;

public interface ArticleLikeService {

    void likeArticle(Long articleId, Long userId) throws RuntimeException;

    void dislikeArticle(Long articleId, Long userId) throws RuntimeException;

    int articleLikeCount(Long articleId) throws RuntimeException;

    void addArticleViewCount(Long articleId) throws RuntimeException;

    int articleViewCount(Long articleId) throws RuntimeException;

    boolean ifLikeArticle(Long articleId, Long userId);

}
