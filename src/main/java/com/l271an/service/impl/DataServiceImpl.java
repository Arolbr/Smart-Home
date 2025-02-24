package com.l271an.service.impl;

import com.l271an.entity.Applications;
import com.l271an.entity.Data;
import com.l271an.mapper.ApplicationMapper;
import com.l271an.mapper.DataMapper;
import com.l271an.service.DataService;
import com.l271an.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class DataServiceImpl implements DataService {
    @Override
    public List<Data> getData() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DataMapper mapper = sqlSession.getMapper(DataMapper.class);
            List<Data> data = mapper.getData();
            sqlSession.commit();
            sqlSession.close();
            return data;
        }
    }

    @Override
    public int insertData(Map<String, Object> param) {
       try(SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
           DataMapper mapper = sqlSession.getMapper(DataMapper.class);
           int result = mapper.insertData(param);
           sqlSession.commit();
           sqlSession.close();
           return result;
       }
    }

    @Override
    public int updateData(Map<String, Object> param) {
        try(SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DataMapper mapper = sqlSession.getMapper(DataMapper.class);
            int result = mapper.updateData(param);
            sqlSession.commit();
            sqlSession.close();
            return result;
        }
    }

    @Override
    public int deleteData(Map<String, Object> param) {
        try(SqlSession sqlSession = MyBatisUtils.getSqlSession()){
            DataMapper mapper = sqlSession.getMapper(DataMapper.class);
            int result = mapper.deleteData(param);
            sqlSession.commit();
            sqlSession.close();
            return result;
        }
    }
}
