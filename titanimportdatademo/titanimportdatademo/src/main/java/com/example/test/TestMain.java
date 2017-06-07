package com.example.test;

import com.example.model.DataForTitan;
import com.example.model.User;
import com.example.service.DataMakingService;
import com.example.service.UserService;
import com.example.service.impl.DataMakingServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by wanquan on 2017/5/31.
 */
public class TestMain {
    @Resource
    private UserService userService;
    private static final int NUM_OF_ITEM = 100;
    private static ApplicationContext ctx = ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private static DataMakingService dataMakingService = ctx.getBean("dataMakingServiceImpl", DataMakingServiceImpl.class);
    public static void insertData() {

        DataForTitan data = new DataForTitan();
        for (int i = 0; i < NUM_OF_ITEM; i++) {
            data.setC_SIP(i);
            data.setC_NETWORK(i + "");
            data.setC_DIP(i);
            data.setC_USERID(i + "");
            data.setC_DPORT(i);
            data.setC_SPORT(i);
            data.setC_TIME(i);
            data.setC_UA(i + "");
            data.setC_UUID(i + "");
            data.setC_LOC(i + "");
            data.setC_RSTIME(i);
            data.setC_TS(i);
            data.setC_OMAC(i + "");
            data.setC_OIMEI(i);
            data.setC_RMAC(i + "");
            data.setC_RIMEI(i);
            data.setC_OZID(i + "");
            data.setC_RZID(i + "");
            data.setC_OUID(i + "");
            data.setC_RUID(i + "");
            data.setC_DGST(i + "");
            data.setC_T(i + "");
            data.setC_N(i + "");
            data.setC_HMD5(i + "");
            data.setC_PKG(i + "");
            dataMakingService.insertItem(data);
        }
        System.out.println("insertData finish");
    }
    public static void modifyRIMEI() {
        Random random = new Random(1234);
        for (int i = 0; i < NUM_OF_ITEM; i++) {
            int randomRIMEI = random.nextInt(NUM_OF_ITEM);
            if (randomRIMEI == i) {
                randomRIMEI += 1;
            }
            dataMakingService.modifyRIMEI(i + "", randomRIMEI + "");
        }
        System.out.println("modifyRIMEI finish");
    }

    public static void modifyMd5() {
        Random random = new Random(1234);
        for (int i = 0; i < NUM_OF_ITEM; i++) {
            int newMd5 = random.nextInt(NUM_OF_ITEM / 10);
            dataMakingService.modifyMd5(i + "", newMd5 + "");
        }
        System.out.println("modifyMd5 finish");
    }

    public static void main(String[] args) {
        insertData();
        modifyRIMEI();
        modifyMd5();
    }

    public User getUser(int id) {
        return userService.selectUserById(id);
    }
}
