package com.l271an.service;

import com.l271an.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UsersService {
    List<Users> userLogin(Map<String, Object> params) throws IOException;     // 普通用户登录
    List<Users> administratorLogin(Map<String, Object> params);    // 管理员登录
    int addUser(Map<String, Object> params);       // 添加用户
    int updateUser(Map<String, Object> params);    // 修改用户信息
    int deleteUser(Map<String, Object> params);    // 删除用户
    List<Users> getAllUsers();  // 查询所有用户
}
