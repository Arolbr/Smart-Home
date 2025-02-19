package com.l271an.service.impl;

import com.l271an.mapper.DeviceMapper;
import com.l271an.entity.Devices;
import com.l271an.service.DeviceService;
import com.l271an.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class DeviceServiceImpl implements DeviceService {
    // 获取所有设备
    @Override
    public List<Devices> getAllDevices() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            List<Devices> devices = mapper.getAllDevices();
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 根据房间名称获取设备
    @Override
    public List<Devices> getDeviceByRoom(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            List<Devices> devices = mapper.getDeviceByRoom(params);
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 添加设备
    @Override
    public int addDevice(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            int devices = mapper.addDevice(params);
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 修改设备信息
    @Override
    public int updateDevice(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            int devices = mapper.updateDevice(params);
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 删除设备
    @Override
    public int deleteDevice(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            int devices = mapper.deleteDevice(params);
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 根据ID查询设备
    @Override
    public List<Devices> getDeviceById(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            List<Devices> devices = mapper.getDeviceById(params);
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 根据ID运行设备
    @Override
    public int runningDeviceById(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            int devices = mapper.runningDeviceById(params);
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 根据ID停止设备
    @Override
    public int stopDeviceById(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            int devices = mapper.stopDeviceById(params);
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 根据IP运行设备
    @Override
    public int runningDeviceByIp(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            int devices = mapper.runningDeviceByIp(params);
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 根据IP停止设备
    @Override
    public int stopDeviceByIp(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            int devices = mapper.stopDeviceByIp(params);
            sqlSession.commit();
            sqlSession.close();
            return devices;
        }
    }

    // 通过IP检查设备状态(0为关闭，1为运行，2为待命，-1为错误)
    @Override
    public boolean inspectDeviceStatusByIp(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            List<Devices> devices = mapper.inspectDeviceStatusByIp(params);
            sqlSession.commit();
            sqlSession.close();
            if(devices != null && !devices.isEmpty()){
                Devices device = devices.getFirst();
                return device.getStatus() == 1;
            }
            return false;
        }
    }

    // 通过ID检查设备状态
    @Override
    public boolean inspectDeviceStatusById(Map<String, Object> params) {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            List<Devices> devices = mapper.inspectDeviceStatusById(params);
            sqlSession.commit();
            sqlSession.close();
            if(devices != null && !devices.isEmpty()){
                Devices device = devices.getFirst();
                return device.getStatus() == 1;
            }
            return false;
        }
    }
}
