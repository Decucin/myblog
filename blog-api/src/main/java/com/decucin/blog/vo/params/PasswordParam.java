package com.decucin.blog.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/23 16:12
 * @description：用于保存用户更改密码时提交的信息
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordParam {

    private String rawPassword;
    private String newPassword;
    private String passwordConfirm;

}
