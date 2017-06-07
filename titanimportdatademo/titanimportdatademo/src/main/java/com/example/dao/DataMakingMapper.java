package com.example.dao;

import com.example.model.DataForTitan;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wanquan on 2017/5/31.
 */
public interface DataMakingMapper {
    void insertItem(DataForTitan data);
    DataForTitan selectOne(int id);
    void modifyRIMEI(@Param("sendImei")String sendImei, @Param("recvImei") String recvImei);
    void modifyMd5(@Param("sendImei") String sendImei, @Param("newMd5") String newMd5);
}
