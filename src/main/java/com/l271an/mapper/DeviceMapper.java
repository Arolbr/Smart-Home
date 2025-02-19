package com.l271an.mapper;

import com.l271an.entity.Devices;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeviceMapper {
    List<Devices> getAllDevices();  // 查询所有设备
    List<Devices> getDeviceByRoom(@Param("params") Map<String, Object> params);    // 查询某房间的设备
    int addDevice(@Param("params") Map<String, Object> params);       // 添加设备
    int updateDevice(@Param("params") Map<String, Object> params);    // 修改设备信息
    int deleteDevice(@Param("params") Map<String, Object> params);    // 删除设备
    List<Devices> getDeviceById(@Param("params") Map<String, Object> params);  // 通过ID查询单个设备信息
    int runningDeviceById(@Param("params") Map<String,Object> params);    // 运行设备(ID)
    int stopDeviceById(@Param("params") Map<String,Object> params);    // 关闭设备(ID)
    int runningDeviceByIp(@Param("params") Map<String,Object> params);  // 运行设备(IP)
    int stopDeviceByIp(@Param("params") Map<String,Object> params);  // 关闭设备(IP)
    List<Devices> inspectDeviceStatusByIp(@Param("params") Map<String,Object> params); // 通过IP检查设备状态
    List<Devices> inspectDeviceStatusById(@Param("params") Map<String,Object> params); // 通过ID检查设备状态
}
