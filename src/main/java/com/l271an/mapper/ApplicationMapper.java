package com.l271an.mapper;

import com.l271an.entity.Applications;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApplicationMapper {
    List<Applications> getApplications();   // 获取所有申请
    int addApplication(@Param("param")Map<String, Object> param);   // 添加申请
    int updateApplicationStatus(@Param("param")Map<String, Object> param);  // 更新申请状态
    List<Applications> getApplicationByUserId(@Param("param")Map<String, Object> param);    // 查询该用户的所有申请
    int deleteApplication(@Param("param")Map<String, Object> param);    // 删除申请
}
