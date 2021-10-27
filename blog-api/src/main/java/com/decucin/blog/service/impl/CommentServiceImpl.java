package com.decucin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.decucin.blog.dao.mapper.CommentMapper;
import com.decucin.blog.dao.pojo.Article;
import com.decucin.blog.dao.pojo.Comment;
import com.decucin.blog.service.ArticleService;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.ResultEnum;
import com.decucin.blog.service.CommentService;
import com.decucin.blog.vo.params.CommentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 09:10
 * @description：这个类用于实现评论功能
 * @modified By：
 * @version: 1.0$
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleService articleService;


    /**
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:22
    **/
    @Override
    public Result findByArticleId(Long articleId) {
        /**
         *  TODO 根据文章id查询到文章所有的评论
         *  @author decucin
         *  @date 2021/10/20 19:10
         **/
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId);
        return Result.success(commentMapper.selectOne(queryWrapper));
    }

    /**
    *  @param userId
    *  @param commentId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:23
    **/
    @Override
    public Result likeComment(Long userId, Long commentId) {
        /**
         *  TODO userId对应用户点赞commentId对应评论
         *  @author decucin
         *  @date 2021/10/20 19:58
         **/
        Comment comment = commentMapper.selectById(commentId);
        if(comment == null){
            return Result.fail(407, "评论不存在");
        }
        Boolean ifLike = commentMapper.selectIfLike(userId, commentId);
        if(ifLike == null || !ifLike) {
            if(ifLike == null){
                // 进入这里表示还没有点赞（不存在点赞后又取消）
                commentMapper.insertLike(userId, commentId);
            }else{
                // 这里表示表中已有信息但if_like为0（点赞后又取消）
                commentMapper.updateLike(userId, commentId);
            }
            LambdaUpdateWrapper<Comment> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Comment::getId, commentId).set(Comment::getLikeCount, comment.getLikeCount() + 1);
            return Result.success(commentMapper.update(null, updateWrapper));
        }
        return Result.fail(301, "该评论已点赞");
    }

    /**
    *  @param userId
    *  @param commentId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:24
    **/
    @Override
    public Result notLikeComment(Long userId, Long commentId) {
        /**
         *  TODO userId对应用户取消点赞commentId对应评论
         *  @author decucin
         *  @date 2021/10/20 20:36
         **/
        Comment comment = commentMapper.selectById(commentId);
        if(comment == null){
            return Result.fail(404, "评论不存在！");
        }
        Boolean ifLike = commentMapper.selectIfLike(userId, commentId);
        if(ifLike){
            // 这里表示用户确实已经点赞了
            // 首先将like_comment表中的ifLiKe置为0
            commentMapper.cancelLike(userId, commentId);
            // 写到这里忽然想到取消点赞如果设置为删除对应记录，那之前点赞的判断部分就不需要判断isLike是否为false（不过我这里还是使用的是更新记录）
            // 更新文章点赞数
            LambdaUpdateWrapper<Comment> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Comment::getId, commentId).set(Comment::getLikeCount, comment.getLikeCount() - 1);
            return Result.success(commentMapper.update(null, updateWrapper));
        }
        // 这里表示用户还没有点赞
        return Result.fail(301, "用户已经点赞了！");
    }

    /**
    *  @param commentParams
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:24
    **/
    @Override
    public Result addCommentToArticle(CommentParams commentParams, Long articleId) {
        /**
         *  TODO 将评论添加到articleId对应文章中
         *  @author decucin
         *  @date 2021/10/20 21:08
         **/
        // 首先将评论插入到评论列表中
        Comment comment = new Comment(commentParams);
        comment.setCreateTime(new Date());
        comment.setLikeCount(0);
        comment.setArticleId(articleId);
        commentMapper.insert(comment);
        // 将文章的评论数加1
        return articleService.addCommentCount(articleId);
    }

    /**
    *  @param commentId
    *  @param articleId
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/25 12:26
    **/
    @Override
    public Result deleteCommentToArticle(Long commentId, Long articleId) {
        /**
         *  TODO 将评论从articleId对应文章中删除
         *  @author decucin
         *  @date 2021/10/20 21:40
         **/
        Comment comment = commentMapper.selectById(commentId);
        if(comment == null){
            return Result.fail(406, "评论不存在！");
        }
        // 首先将评论从评论表中删除
        commentMapper.deleteById(commentId);
        // 将文章的评论数减一
        return articleService.deleteCommentCount(articleId);
    }
}