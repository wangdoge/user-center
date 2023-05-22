package com.usercenter.model.domain.request;

/**
 * @author CSJ
 * @version 1.0
 * @decription
 * @createTime 2023/5/15 星期一 22:03
 */

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户更新请求
 *
 * @author Shier
 */
@Data
public class UserSearchRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;


    /**
     * 性别 男 女
     */
    private Integer gender;


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
     * 创建时间
     */
    private Date createTime;

    /**
     * user-普通用户 admin-管理员 ban-封号
     */
    private Integer userRole;

    /**
     * 用户编号
     */
    private String planetCode;

    private int current;

    private int pageSize;

    private int keyWord;

    private static final long serialVersionUID = 1L;

    public Object getUserCode() {
        return planetCode;
    }
}
