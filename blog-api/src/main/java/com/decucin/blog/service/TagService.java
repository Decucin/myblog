package com.decucin.blog.service;

import com.decucin.blog.dao.pojo.Tag;
import com.decucin.blog.vo.Result;

import java.util.List;

public interface TagService {
    Result findAll();

    List<Tag> findTagsByArticleId(Long articleId);


    Result hotTags(int limit);

    Result addTags(String[] tagNames);
}
