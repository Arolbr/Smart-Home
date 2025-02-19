package com.l271an.service;

import com.l271an.entity.Devices;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    List<Devices> getAllDevices();  // 查询所有设备
    List<Devices> getDeviceByRoom(Map<String, Object> params);    // 查询某房间的设备
    int addDevice(Map<String, Object> params);       // 添加设备
    int updateDevice(Map<String, Object> params);    // 修改设备信息
    int deleteDevice(Map<String, Object> params);    // 删除设备
    List<Devices> getDeviceById(Map<String, Object> params);  // 通过ID查询单个设备信息
    int runningDeviceById(Map<String,Object> params);    // 运行设备(ID)
    int stopDeviceById(Map<String,Object> params);    // 关闭设备(ID)
    int runningDeviceByIp(Map<String,Object> params);  // 运行设备(IP)
    int stopDeviceByIp(Map<String,Object> params);  // 关闭设备(IP)
    boolean inspectDeviceStatusByIp(Map<String,Object> params); // 通过IP检查设备状态
    boolean inspectDeviceStatusById(Map<String,Object> params); // 通过ID检查设备状态
}
