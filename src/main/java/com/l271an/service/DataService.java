package com.l271an.service;

import com.l271an.entity.Data;

import java.util.List;
import java.util.Map;

public interface DataService {
    List<Data> getData();     // 获取所有预设数据
    int insertData(Map<String, Object> param);    // 添加新的预设数据
    int updateData(Map<String, Object> param);    // 更新已有的数据
    int deleteData(Map<String, Object> param);    // 删除指定的数据
}
