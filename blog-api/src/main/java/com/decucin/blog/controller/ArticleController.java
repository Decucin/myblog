package com.decucin.blog.controller;


import com.decucin.blog.vo.params.ArticleParam;
import com.decucin.blog.vo.params.PageParam;
import com.decucin.blog.vo.Result;
import com.decucin.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


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
    public Result findAll(@RequestBody PageParam pageParam){
        return articleService.findAll(pageParam);
    }

    @PostMapping("findCategory")
    public Result findArticleByCategory(@RequestBody PageParam pageParam, @RequestParam("id") Long categoryId){
        return articleService.findArticleByCategory(pageParam, categoryId);
    }

    @GetMapping("{articleId}")
    public Result findArticleInfo(@PathVariable("articleId") Long id){
        return articleService.findArticleInfo(id);
    }

    @GetMapping("like")

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

    }

    @PostMapping("add")
    public Result addArticle(@RequestHeader String token, @RequestBody ArticleParam articleParam){
        return articleService.addArticle(token, articleParam);
    }
}
