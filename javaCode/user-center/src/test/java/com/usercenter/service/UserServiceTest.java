package com.usercenter.service;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Date 2023/5/8 22:33
 * author:wyf
 * 用户服务测试
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser(){
        User user = new User();
        user.setUsername("awang");
        user.setAvatarUrl("123");
        user.setUserAccount("chicken");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("123");
        user.setEmail("234");
        boolean save = userService.save(user);
        Assertions.assertTrue(save);
        userService.listMaps();
    }

    @Test
    void selectList(){
        userService.listMaps();
    }

    @Test
    void check1(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount,"1");
        long count = userService.count(queryWrapper);
        System.out.println(count);
    }

//    @Test
//    void userRegister() {
//        String userAccount="wangdie123";
//        String userPassword="56";
//        String checkPassword="123456";
//        long result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1,result);
//        userAccount="w";
//        userPassword="123456";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1,result);
//        userAccount="chicken";
//        userPassword="123456789";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1,result);
//        userAccount="chicken@!-";
//        userPassword="123456789";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1,result);
////        userAccount="2541277577";
////        userPassword="123456789";
////        checkPassword="123456789";
////        result = userService.userRegister(userAccount, userPassword, checkPassword);
////        Assertions.assertTrue(result>0);
//    }

}