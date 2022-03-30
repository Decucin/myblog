package com.decucin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.decucin.blog.dao.mapper.BodyMapper;
import com.decucin.blog.dao.pojo.Body;
import com.decucin.blog.vo.ArticleBodyVo;
import com.decucin.blog.vo.Result;
import com.decucin.blog.vo.ResultEnum;
import com.decucin.blog.service.BodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 12:35
 * @description：这个类用于实现文章主体功能
 * @modified By：
 * @version: 1.0$
 */
@Service
@Transactional
public class BodyServiceImpl implements BodyService {

    @Autowired
    private BodyMapper bodyMapper;


    /**
    *  @param bodyId
    *  @return com.decucin.blog.vo.ArticleBodyVo
    *  @author decucin
    *  @date 2021/10/25 12:20
    **/
    @Override
    public ArticleBodyVo findBodyById(Long bodyId) {
        /**
         *  TODO 根据文章id查询到文章主体
         *  @author decucin
         *  @date 2021/10/20 18:36
         **/
        Body body = bodyMapper.selectById(bodyId);
        ArticleBodyVo bodyVo = new ArticleBodyVo();
        bodyVo.setId(body.getId());
        bodyVo.setBody(body.getContentHtml());
        return bodyVo;
    }
}
