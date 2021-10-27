package com.decucin.blog.vo.params;

import lombok.Data;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/23 15:35
 * @description：这个类用来保存分页的简略信息
 * @modified By：
 * @version: 1.0$
 */
@Data
public class PageParams {

    private int page = 1;
    private int pageSize = 10;
}
