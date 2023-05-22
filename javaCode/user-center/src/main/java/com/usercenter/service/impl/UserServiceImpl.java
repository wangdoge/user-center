package com.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usercenter.common.ErrorCode;
import com.usercenter.exception.BusinessException;
import com.usercenter.model.domain.User;
import com.usercenter.model.domain.request.UserAddRequest;
import com.usercenter.model.domain.request.UserModifyPasswordRequest;
import com.usercenter.service.UserService;
import com.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
* @author wang
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-05-08 22:24:15
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    /**
     * 加密密码
     */
    private static final String SALT="wang";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword,String username,String planetCode) {

        //1.校验
        //校验非空
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,username,planetCode)){
            return -1;
        }

        //账号不小于4位
        if(userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"账号不可小于4位");
        }

        //密码不小于8位
        if(userPassword.length()<8||checkPassword.length()<8){
            return -1;
        }

        //账户不能包含特殊字符
        String validPattern = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            return -1;
        }

        if(!userPassword.equals(checkPassword)){
            return -1;
        }

        if(planetCode.length()>5){
            return -1;
        }

        //账户不能重复
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount,userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if(count>0){
            return -1;
        }

        //编号不能重复
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPlanetCode,planetCode);
        count = userMapper.selectCount(queryWrapper);
        if(count>0){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"床位已被注册");
        }

        //2.密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUsername(username);
        user.setPlanetCode(planetCode);
        boolean saveResult = this.save(user);
        if(!saveResult){
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1.校验
        //校验非空
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"参数为空");
        }

        //账号不小于4位
        if(userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"账号小于四位");
        }

        //密码不小于8位
        if(userPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"用户密码小于8位");
        }

        //账户不能包含特殊字符
        String validPattern = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"用户账号有特殊字符");
        }

        //2.密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT +   userPassword).getBytes());
        //查询用户是否存在
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getUserAccount,userAccount);
        User user = userMapper.selectOne(userQueryWrapper);
        //用户不存在
        if(user==null){
            log.info("user login failed,user account cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAM_ERROR,"用户不存在");
        }
        userQueryWrapper.eq(User::getUserPassword,encryptPassword);
        user = userMapper.selectOne(userQueryWrapper);
        //用户不存在
        if(user==null){
            log.info("user login failed,user account cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAM_ERROR,"用户密码有误");
        }


        //3.用户脱敏,隐藏返回给前端的敏感信息
        User safetyUser=getSafeUser(user);

        //4.记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);


        return safetyUser;
    }

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    @Override
    public User getSafeUser(User user){
        if(user==null){
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        safetyUser.setUserRole(user.getUserRole());
        safetyUser.setPlanetCode(user.getPlanetCode());
        return safetyUser;
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

    /**
     * 新增用户
     * @param addUser
     * @return
     */
    @Override
    public int addUser(UserAddRequest addUser) {
        User user = new User();
        BeanUtils.copyProperties(addUser,user);
        String userPassword=addUser.getUserPassword();
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        user.setUserPassword(encryptPassword);
        return userMapper.insert(user);
    }

    @Override
    public boolean checkPassword(UserModifyPasswordRequest passwordRequest,HttpServletRequest request) {
        String password = passwordRequest.getPassword();
        //获取用户输入的密码的加密状态
        String userPassword = DigestUtils.md5DigestAsHex((SALT +password).getBytes());
        //获取当前用户
        User currentUser = getLoginUser(request);
        String checkPassword = userMapper.getUserPassword(currentUser.getId());

        if(userPassword.equals(checkPassword)){
            String newPassword = passwordRequest.getNewPassword();
            String newPassword1 = DigestUtils.md5DigestAsHex((SALT +newPassword).getBytes());
            currentUser.setUserPassword(newPassword1);
            userMapper.updateById(currentUser);
            return true;
        } else {
            throw new BusinessException(ErrorCode.PARAM_ERROR,"输入密码有误");
        }
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        Object objUser = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) objUser;
        if(user==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN,"用户未登录");
        }
        return user;
    }

    
}




