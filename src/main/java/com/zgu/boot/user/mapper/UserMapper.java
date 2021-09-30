package com.zgu.boot.user.mapper;

import com.zgu.boot.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findUserByPhone(@Param("phone") String phone);
    
}
