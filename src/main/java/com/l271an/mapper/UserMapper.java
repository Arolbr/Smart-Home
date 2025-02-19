package com.l271an.mapper;

import com.l271an.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<Users> userLogin(@Param("params") Map<String, Object> params);     // 普通用户登录
    List<Users> administratorLogin(@Param("params") Map<String, Object> params);    // 管理员登录
    int addUser(@Param("params") Map<String, Object> params);       // 添加用户
    int updateUser(@Param("params") Map<String, Object> params);    // 修改用户信息
    int deleteUser(@Param("params") Map<String, Object> params);    // 删除用户
    List<Users> getAllUsers();  // 查询所有用户
}
