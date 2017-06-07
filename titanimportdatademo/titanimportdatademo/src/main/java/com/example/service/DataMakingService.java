package com.example.service;

import com.example.model.DataForTitan;

/**
 * Created by wanquan on 2017/5/31.
 */
public interface DataMakingService {
    void insertItem(DataForTitan data);
    DataForTitan selectOne(int id);
    void modifyRIMEI(String sendImei, String recvImei);
    void modifyMd5(String sendImei, String newMd5);
}
