package com.decucin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.decucin.blog.dao.mapper.ArticleMapper;
import com.decucin.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadService{

    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        Integer viewCount = article.getViewCount();
        Article articleToUpdate = new Article();
        articleToUpdate.setViewCount(viewCount + 1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, article.getId());
        // CAS
        updateWrapper.eq(Article::getViewCount, viewCount);
        articleMapper.update(article, updateWrapper);
    }
}
