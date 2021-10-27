package com.decucin.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.decucin.blog.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    Boolean selectIfLike(Long userId, Long articleId);

    Long insertLike(Long userId, Long articleId);

    Long updateLike(Long userId, Long articleId);

    Long cancelLike(Long userId, Long articleId);
}
