package com.decucin.blog.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/19 10:44
 * @description：对应数据库表decucin_body
 * @modified By：
 * @version: 1.0$
 */
@Data
public class Body {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String content;
    private String contentHtml;
}
