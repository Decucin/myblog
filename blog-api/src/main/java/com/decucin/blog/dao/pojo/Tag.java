package com.decucin.blog.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/19 11:07
 * @description：对应数据库表decucin_tag
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String tagName;
}
