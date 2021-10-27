package com.decucin.blog.controller;

import com.decucin.blog.vo.Result;
import com.decucin.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/20 20:29
 * @description：与标签有关功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("findAll")
    public Result findAll(){
        return tagService.findAll();
    }

    @GetMapping("hot")
    public Result hotTags(){
        int limit = 6;
        return tagService.hotTags(limit);
    }

    @PostMapping("add")
    public Result addTags(String[] tagNames){
        return tagService.addTags(tagNames);
    }
}
