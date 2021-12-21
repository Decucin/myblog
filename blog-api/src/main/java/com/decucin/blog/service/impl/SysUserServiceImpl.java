package com.decucin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.decucin.blog.dao.mapper.SysUserMapper;
import com.decucin.blog.dao.pojo.SysUser;
import com.decucin.blog.utils.JWTTokenUtils;
import com.decucin.blog.utils.PasswordUtils;
import com.decucin.blog.vo.LoginUserVo;
import com.decucin.blog.vo.Result;
import com.decucin.blog.service.SysUserService;
import com.decucin.blog.vo.UserVo;
import com.decucin.blog.vo.params.LoginParam;
import com.decucin.blog.vo.params.PasswordParam;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 23:58
 * @description：这个类用于实现用户功能
 * @modified By：
 * @version: 1.0$
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * @param id
    *  @return com.decucin.blog.dao.pojo.SysUser
    *  @author decucin
    *  @date 2021/10/23 16:10
    **/
    @Override
    public SysUser findUserById(Long id) {
        /**
         *  TODO 根据用户id找到用户
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        SysUser sysUser = sysUserMapper.selectById(id);
        if(sysUser == null){
            sysUser.setNickname("decucin");
        }
        return sysUser;
    }

    /**
     * @param userName
    *  @return com.decucin.blog.dao.pojo.SysUser
    *  @author decucin
    *  @date 2021/10/23 16:10
    **/
    @Override
    public SysUser findUserByUsername(String userName) {
        /**
         *  TODO 通过用户名找到用户
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, userName);
        queryWrapper.last("limit 1");
        SysUser user = sysUserMapper.selectOne(queryWrapper);
        return user;
    }

    /**
     * @param token
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/23 16:11
    **/
    @Override
    public Result findUserVoByToken(String token) {
        /**
         *  TODO 通过token找到用户
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        if(StringUtils.isNullOrEmpty(token)) {
            return Result.fail(200, "Token不合法！");
        }
        String id = JWTTokenUtils.getTokenBody(token).get("id").toString();
        if(id == null){
            return Result.fail(200, "Token不合法！");
        }
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getId, id);
        queryWrapper.last("limit 1");
        SysUser user = sysUserMapper.selectOne(queryWrapper);
        if(user == null){
            return Result.fail(400, "用户不存在！");
        }
        return Result.success(new LoginUserVo(user));
    }

    /**
     * @param loginParam
    *  @return java.lang.Integer
    *  @author decucin
    *  @date 2021/10/23 16:11
    **/
    @Override
    public Integer addUser(LoginParam loginParam) {
        /**
         *  TODO 添加用户（注册）
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        // 注意这里，前端传过来的信息中，用户名是正确的，但密码是经过AES加密的，因此注意此处需要处理
        SysUser user = new SysUser();
        user.setAccount(loginParam.getUsername());
        user.setPassword(PasswordUtils.formToDB(loginParam.getPassword()));
        user.setAdmin(false);
        user.setCreateDate(new Date());
        user.setNickname("用户" + user.getAccount());
        return sysUserMapper.insert(user);
    }

    /**
     * @param token
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/23 16:11
    **/
    @Override
    public Result showInfo(String token) {
        /**
         *  TODO 通过token查询用户自身信息
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        Long id = (Long) JWTTokenUtils.getTokenBody(token).get("id");
        SysUser user = sysUserMapper.selectById(id);
        // 判断用户id是否正确（数据库中是否存在）
        if(user == null){
            return Result.fail(404, "用户信息不存在！");
        }
        // 此时已经确保能查到user用户
        return Result.success(new UserVo(user));
    }

    /**
     * @description: 更新用户信息
     * @param token
     * @param userVo
     * @return: com.decucin.blog.vo.Result
     * @author: decucin
     * @date: 2021/12/21 12:48
     */
    @Override
    public Result updateInfo(String token, UserVo userVo) {
        Long userId = (Long) JWTTokenUtils.getTokenBody(token).get("id");
        if(JWTTokenUtils.getTokenBody(token).get("id") == null){
            return Result.fail(403, "token不合法！");
        }
        SysUser user = new SysUser();
        user.setId(userId);
        user.setAccount(userVo.getUsername());
        user.setNickname(userVo.getNickname());
        user.setAvatar(userVo.getAvatar());
        user.setEmail(userVo.getEmail());
        user.setMobilePhoneNumber(userVo.getMobilePhoneNumber());
        user.setSex(userVo.getSex());
        return Result.success(sysUserMapper.updateById(user));
    }

    /**
     * @description: 修改用户密码
     * @param token
     * @param params
     * @return: com.decucin.blog.vo.Result
     * @author: decucin
     * @date: 2021/12/21 12:51
     */
    @Override
    public Result changePassword(String token, PasswordParam params) {
        Long userId = (Long) JWTTokenUtils.getTokenBody(token).get("id");
        if(userId == null){
            return Result.fail(407, "token不合法！");
        }
        // 注意这里发过来的密码是前端经过AES加密的
        // 首先判断密码和确认的密码是否相等（应该在前端处理，但考虑到任务量便交给了后端处理，这会导致用户第一时间得不到反馈）
        if(!params.getPasswordConfirm().equals(params.getNewPassword())){
            return Result.fail(405, "用户两次输入的密码不一致！");
        }
        // 用户原密码与数据库中密码进行比对
        // 写到这里忽然想到将前端密码与数据库中密码比对部分单独提出一个工具类来实现，增加代码可用性
        SysUser user = sysUserMapper.selectById(userId);
        if(user == null){
            return Result.fail(404, "用户信息不存在！");
        }
        if(!PasswordUtils.formCompareDB(params.getRawPassword(), user.getPassword())){
            // 进入这个循环表示用户的密码输错了
            return Result.fail(406, "用户输入的密码错误！");
        }
        String dbPassword = PasswordUtils.formToDB(params.getNewPassword());
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId, userId).set(SysUser::getPassword, dbPassword);
        return Result.success(sysUserMapper.update(null, updateWrapper));
    }
}
