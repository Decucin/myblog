package com.decucin.blog.vo;

import com.decucin.blog.dao.pojo.SysUser;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 20:38
 * @description：这个类用来保存登录用户的简略信息
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String nickname;
    private String avatar;
    private String account;

    public LoginUserVo(SysUser user){
        id = user.getId();
        nickname = user.getNickname();
        avatar = user.getAvatar();
        account = user.getAccount();
    }
}
