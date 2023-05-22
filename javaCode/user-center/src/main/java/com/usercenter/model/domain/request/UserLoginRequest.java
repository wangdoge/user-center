package com.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Date 2023/5/10 19:09
 * author:wyf
 * 用户注册请求体
 */

@Data
public class UserLoginRequest implements Serializable{

    private static final long serialVersionUID=1L;

    private String userAccount;
    private String password;
}
