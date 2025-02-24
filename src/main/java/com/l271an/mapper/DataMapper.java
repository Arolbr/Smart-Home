package com.l271an.mapper;

import com.l271an.entity.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataMapper {
    List<Data> getData();     // 获取所有预设数据
    int insertData(@Param("param")Map<String, Object> param);    // 添加新的预设数据
    int updateData(@Param("param")Map<String, Object> param);    // 更新已有的数据
    int deleteData(@Param("param")Map<String, Object> param);    // 删除指定的数据
}
