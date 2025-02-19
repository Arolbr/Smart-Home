package com.l271an.service.impl;

import com.l271an.mapper.UserMapper;
import com.l271an.entity.Users;
import com.l271an.service.UsersService;
import com.l271an.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class UsersServiceImpl implements UsersService {

    // 普通用户登录
    @Override
    public List<Users> userLogin(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Users> users = mapper.userLogin(params);
            sqlSession.commit();
            sqlSession.close();
            return users;
        }
    }

    // 管理员登录
    @Override
    public List<Users> administratorLogin(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Users> users = mapper.administratorLogin(params);
            sqlSession.commit();
            sqlSession.close();
            return users;
        }
    }

    // 添加用户
    @Override
    public int addUser(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.addUser(params);
            sqlSession.commit();
            sqlSession.close();
            return i;
        }
    }

    // 修改用户信息
    @Override
    public int updateUser(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.updateUser(params);
            sqlSession.commit();
            sqlSession.close();
            return i;
        }
    }

    // 删除用户
    @Override
    public int deleteUser(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.deleteUser(params);
            sqlSession.commit();
            sqlSession.close();
            return i;
        }
    }

    // 查询所有用户
    @Override
    public List<Users> getAllUsers() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Users> users = mapper.getAllUsers();
            sqlSession.commit();
            sqlSession.close();
            return users;
        }
    }
}
