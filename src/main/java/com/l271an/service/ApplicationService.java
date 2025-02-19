package com.l271an.service;

import com.l271an.entity.Applications;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApplicationService {
    List<Applications> getApplications();   // 获取所有申请
    int addApplication(Map<String, Object> param);   // 添加申请
    int updateApplicationStatus(Map<String, Object> param);  // 更新申请状态
    List<Applications> getApplicationByUserId(Map<String, Object> param);    // 查询该用户的所有申请
    int deleteApplication(Map<String, Object> param);    // 删除申请
}
