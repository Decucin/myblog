package com.decucin.blog.controller;

import com.decucin.blog.service.CategotyService;
import com.decucin.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/25 11:52
 * @description：与类别有关功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategotyService categotyService;

    @GetMapping("findAll")
    public Result findAll(){
        return categotyService.findAll();
    }
}
