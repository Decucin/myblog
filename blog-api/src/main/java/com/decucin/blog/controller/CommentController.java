package com.decucin.blog.controller;


import com.decucin.blog.vo.Result;
import com.decucin.blog.service.CommentService;
import com.decucin.blog.vo.params.CommentParam;
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
    public Result likeComment(@RequestHeader("Authorization") String token, Long commentId){
        return commentService.likeComment(token, commentId);
    }

    @GetMapping("notLike")
    public Result notLikeComment(@RequestHeader("Authorization") String token, Long commentId) {
        return commentService.notLikeComment(token, commentId);
    }

    @PostMapping("add/{articleId}")
    public Result addCommentToArticle(@RequestHeader("Authorization") String token, @RequestBody CommentParam commentParam, @PathVariable("articleId") Long articleId){
        return commentService.addCommentToArticle(token, commentParam, articleId);
    }

    @GetMapping("delete/{articleId}")
    public Result deleteCommentToArticle(@RequestHeader("Authorization") String token, Long commentId, @PathVariable("articleId") Long articleId){
        return commentService.deleteCommentToArticle(token, commentId, articleId);
    }
}
