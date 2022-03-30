package com.decucin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.decucin.blog.dao.mapper.TagMapper;
import com.decucin.blog.dao.pojo.Tag;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.ResultEnum;
import com.decucin.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 23:58
 * @description：这个类用于实现标签功能
 * @modified By：
 * @version: 1.0$
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
    *  @param
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:35
    **/
    @Override
    public Result findAll() {
        /**
         *  TODO 找到所有标签
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        List<Tag> tags = tagMapper.selectList(new LambdaQueryWrapper<>());
        return new Result(ResultEnum.SUCCESS, tags);
    }

    /**
    *  @param articleId
    *  @return java.util.List<com.decucin.blog.dao.pojo.Tag>
    *  @author decucin
    *  @date 2021/10/25 12:36
    **/
    @Override
    public List<Tag> findTagsByArticleId(Long articleId) {
        /**
         *  TODO 根据文章id找到其标签列表
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        List<Tag> tagList = tagMapper.findTagsByArticleId(articleId);
        List<Tag> tags= new ArrayList<>();
        for (Tag tag : tagList) {
            tags.add(tag);
        }
        return tags;
    }

    /**
    *  @param limit
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:37
    **/
    @Override
    public Result hotTags(int limit) {
        /**
         *  TODO 找到最热标签
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        /**
         *  1.标签所拥有的文章数量最多-->最热标签
         *  2.根据tagid进行分组计数，从大到小排列
        **/
        List<Long> idList = tagMapper.findHotTagIds(limit);
        ArrayList<Tag> tags = new ArrayList<>();
        for (Long id : idList) {
            tags.add(tagMapper.selectById(id));
        }
        return Result.success(tags);
    }

    /**
    *  @param tagNames
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:38
    **/
    @Override
    public Result addTags(String[] tagNames) {
        /**
         *  TODO 增加标签
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        for (String tagName : tagNames) {
            Tag tag = new Tag(null, tagName);
            tagMapper.insert(tag);
        }
        return Result.success(tagNames.length);
    }

    @Override
    public Tag findTagByTagName(String tagName) {
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Tag::getTagName, tagName).last("limit 1");
        Tag tag = tagMapper.selectOne(lambdaQueryWrapper);
        return tag;
    }

    @Override
    public Long insertTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tagMapper.insert(tag);
        return tag.getId();
    }
}
