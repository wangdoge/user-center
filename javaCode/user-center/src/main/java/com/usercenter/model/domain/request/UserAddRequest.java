package com.usercenter.model.domain.request;

/**
 * @author CSJ
 * @version 1.0
 * @decription
 * @createTime 2023/5/15 星期一 22:02
 */

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户创建请求
 *
 */
@Data
public class UserAddRequest implements Serializable {


    private Long id;
    /**
     * 用户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别 男 女
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态  0-正常 1-注销 2-封号
     */
    private Integer userStatus;


    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除  逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * user-普通用户 admin-管理员 ban-封号
     */
    private Integer userRole;

    /**
     * 用户编号
     */
    private String planetCode;

    private static final long serialVersionUID = 1L;
}
