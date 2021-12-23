package com.decucin.blog.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/23 15:35
 * @description：这个类用来保存分页的简略信息
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {

    private int page = 1;
    private int pageSize = 10;
}
