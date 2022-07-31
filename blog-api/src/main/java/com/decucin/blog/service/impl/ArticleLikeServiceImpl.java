package com.decucin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.decucin.blog.dao.mapper.ArticleMapper;
import com.decucin.blog.dao.pojo.Article;
import com.decucin.blog.service.ArticleLikeService;
import com.decucin.blog.service.ArticleService;
import com.decucin.blog.service.BodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class ArticleLikeServiceImpl implements ArticleLikeService {

    // 怎么做点赞
    // 首先，点赞数有一个string
    // 其次，点赞操作有一个hash结构，里面key是user_id，value是1/0表示是否点赞

    private static final String REDIS_ARTICLE_LIKE_COUNT_PRE = "article::like::count::";
    private static final String REDIS_ARTICLE_LIKE_ACTION_PRE = "article::like::action::";
    private static final String REDIS_ARTICLE_VIEW_COUNT_PRE = "article::view::count::";


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void likeArticle(Long articleId, Long userId) throws RuntimeException {
        // 判断是否已经点赞过了
        if(ifLikeArticle(articleId, userId)){
            throw new RuntimeException("已经点过赞了！");
        }
        // 点赞数 + 1
        // 没有默认从0加到1
        redisTemplate.opsForValue().increment(REDIS_ARTICLE_LIKE_COUNT_PRE + articleId);

        // 点赞存redis中
        redisTemplate.opsForHash().put(REDIS_ARTICLE_LIKE_ACTION_PRE + articleId, userId, "1");
    }

    @Override
    public void dislikeArticle(Long articleId, Long userId) throws RuntimeException {
        if(!ifLikeArticle(articleId, userId)){
            throw new RuntimeException("还未点赞！");
        }
        // 这里如果没有，默认从0减到-1
        redisTemplate.opsForValue().decrement(REDIS_ARTICLE_LIKE_COUNT_PRE + articleId);
        redisTemplate.opsForHash().put(REDIS_ARTICLE_LIKE_ACTION_PRE + articleId, userId, "0");
    }

    @Override
    public int articleLikeCount(Long articleId) throws RuntimeException {
        // 先查redis
        int likeCount = 0;
        Object value = redisTemplate.opsForValue().get(REDIS_ARTICLE_LIKE_COUNT_PRE + articleId);
        likeCount += value == null ? 0 : Integer.parseInt((String) value);
        likeCount += articleMapper.selectById(articleId).getLikeCount();
        return likeCount;
    }

    @Override
    public void addArticleViewCount(Long articleId) throws RuntimeException {
        redisTemplate.opsForValue().increment(REDIS_ARTICLE_VIEW_COUNT_PRE + articleId);
    }

    @Override
    public int articleViewCount(Long articleId) throws RuntimeException {
        int viewCount = 0;
        Object value = redisTemplate.opsForValue().get(REDIS_ARTICLE_VIEW_COUNT_PRE + articleId);
        viewCount += value == null ? 0 : Integer.parseInt((String) value);
        viewCount += articleMapper.selectById(articleId).getViewCount();
        return viewCount;
    }

    @Override
    public boolean ifLikeArticle(Long articleId, Long userId) {
        Object value = redisTemplate.opsForHash().get(articleId, REDIS_ARTICLE_LIKE_ACTION_PRE + userId);
        if(value == null){
            // 去数据库里面查是否点过赞
            return articleMapper.selectIfLike(userId, articleId);
        }
        else if(value.equals("1")){
            return true;
        }else{
            return false;
        }
    }
}
