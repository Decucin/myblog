package com.decucin.blog.controller;


<<<<<<< HEAD
import com.decucin.blog.vo.params.PageParams;
import com.decucin.blog.vo.Result;
import com.decucin.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

=======
import com.decucin.blog.service.ArticleService;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

>>>>>>> master

/**
 * @author ：decucin
 * @date ：Created in 2021/10/20 11:22
 * @description：与文章有关功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result findAll(@RequestBody PageParams pageParams){
        return articleService.findAll(pageParams);
    }

    @PostMapping("findCategory")
    public Result findArticleByCategory(@RequestBody PageParams pageParams, @RequestParam("id") Long categoryId){
        return articleService.findArticleByCategory(pageParams, categoryId);
    }

    @GetMapping("{articleId}")
    public Result findArticleInfo(@PathVariable("articleId") Long id){
        return articleService.findArticleInfo(id);
    }

    @GetMapping("like")
<<<<<<< HEAD
    public Result likeArticle(Long userId, Long articleId){
        return articleService.likeArticle(userId, articleId);
    }

    @GetMapping("notLike")
    public Result notLikeArticle(Long userId, Long articleId){
        return articleService.notLikeArticle(userId, articleId);
=======
    public Result likeArticle(@RequestHeader("Authorization") String token, Long articleId){
        return articleService.likeArticle(token, articleId);
    }

    @GetMapping("notLike")
    public Result notLikeArticle(@RequestHeader("Authorization") String token, Long articleId){
        return articleService.notLikeArticle(token, articleId);
    }
    @RequestMapping("/key")
    public Result search(@PathVariable("key")String key) throws IOException {
        return articleService.search(key);
>>>>>>> master
    }
}
