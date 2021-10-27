package com.decucin.blog.controller;


import com.decucin.blog.vo.Result;
import com.decucin.blog.service.CommentService;
import com.decucin.blog.vo.params.CommentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 10:55
 * @description：与评论有关功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("{articleId}")
    public Result findByArticleId(@PathVariable("articleId") Long articleId){
        return commentService.findByArticleId(articleId);
    }

    @GetMapping("like")
    public Result likeComment(Long userId, Long commentId){
        return commentService.likeComment(userId, commentId);
    }

    @GetMapping("notLike")
    public Result notLikeComment(Long userId, Long commentId){
        return commentService.notLikeComment(userId, commentId);
    }

    @PostMapping("add/{articleId}")
    public Result addCommentToArticle(@RequestBody CommentParams commentParams, @PathVariable("articleId") Long articleId){
        return commentService.addCommentToArticle(commentParams, articleId);
    }

    @GetMapping("delete/{articleId}")
    public Result deleteCommentToArticle(Long commentId, @PathVariable("articleId") Long articleId){
        return commentService.deleteCommentToArticle(commentId, articleId);
    }
}
