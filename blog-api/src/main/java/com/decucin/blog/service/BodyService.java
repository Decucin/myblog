package com.decucin.blog.service;

import com.decucin.blog.vo.ArticleBodyVo;
import com.decucin.blog.vo.Result;

public interface BodyService {

    ArticleBodyVo findBodyById(Long bodyId);
}
