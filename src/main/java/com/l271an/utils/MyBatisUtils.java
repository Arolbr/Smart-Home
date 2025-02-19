package com.l271an.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis_config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.fillInStackTrace();
            throw new RuntimeException("初始化SqlSessionFactory时出错: " + e.getMessage());
        }
    }

    // 获取 SqlSessionFactory
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    // 获取 SqlSession（手动提交事务）
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    // 获取 SqlSession（自动提交事务）
    public static SqlSession getSqlSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }
}
