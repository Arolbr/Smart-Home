package com.l271an.service.impl;

import com.l271an.entity.Applications;
import com.l271an.entity.Devices;
import com.l271an.mapper.ApplicationMapper;
import com.l271an.mapper.DeviceMapper;
import com.l271an.service.ApplicationService;
import com.l271an.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;


public class ApplicationServiceImpl implements ApplicationService {

    @Override
    public List<Applications> getApplications() {   // 获取所有申请
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            ApplicationMapper mapper = sqlSession.getMapper(ApplicationMapper.class);
            List<Applications> applications = mapper.getApplications();
            sqlSession.commit();
            sqlSession.close();
            return applications;
        }
    }

    @Override
    public int addApplication(Map<String, Object> param) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            ApplicationMapper mapper = sqlSession.getMapper(ApplicationMapper.class);
            int n = mapper.addApplication(param);
            sqlSession.commit();
            sqlSession.close();
            return n;
        }
    }

    @Override
    public int updateApplicationStatus(Map<String, Object> param) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            ApplicationMapper mapper = sqlSession.getMapper(ApplicationMapper.class);
            int n = mapper.updateApplicationStatus(param);
            sqlSession.commit();
            sqlSession.close();
            return n;
        }
    }

    @Override
    public List<Applications> getApplicationByUserId(Map<String, Object> param) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            ApplicationMapper mapper = sqlSession.getMapper(ApplicationMapper.class);
            List<Applications> applications = mapper.getApplicationByUserId(param);
            sqlSession.commit();
            sqlSession.close();
            return applications;
        }
    }

    @Override
    public int deleteApplication(Map<String, Object> param) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            ApplicationMapper mapper = sqlSession.getMapper(ApplicationMapper.class);
            int n  = mapper.deleteApplication(param);
            sqlSession.commit();
            sqlSession.close();
            return n;
        }
    }
}
