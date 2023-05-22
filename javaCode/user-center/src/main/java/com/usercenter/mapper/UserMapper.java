package com.usercenter.mapper;

import com.usercenter.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author wang
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-05-08 22:24:15
* @Entity generator.domain.User
*/

public interface UserMapper extends BaseMapper<User> {

    String getUserPassword(@Param("id")Long id);
}




