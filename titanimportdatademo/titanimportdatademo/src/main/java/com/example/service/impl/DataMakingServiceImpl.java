package com.example.service.impl;

import com.example.dao.DataMakingMapper;
import com.example.model.DataForTitan;
import com.example.service.DataMakingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wanquan on 2017/5/31.
 */
@Service
public class DataMakingServiceImpl implements DataMakingService {
    @Resource
    private DataMakingMapper dataMakingMapper;
    public void insertItem(DataForTitan data) {
        dataMakingMapper.insertItem(data);
    }

    public DataForTitan selectOne(int id) {
        return dataMakingMapper.selectOne(id);
    }

    public void modifyRIMEI(String sendImei, String recvImei) {
        dataMakingMapper.modifyRIMEI(sendImei, recvImei);
    }

    public void modifyMd5(String sendImei, String newMd5) {
        dataMakingMapper.modifyMd5(sendImei, newMd5);
    }
}
