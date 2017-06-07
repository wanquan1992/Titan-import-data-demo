package com.example.model;

/**
 * Created by wanquan on 2017/6/1.
 */
public class VertexDto {
    private long c_ip;
    private String c_network;
    private String c_userid;
    private int c_port;
    private long c_time;
    private String c_ua;
    private String c_uuid;
    private String c_loc;
    private String c_mac;
    private long c_imei;
    private String c_zid;
    public String getC_network() {
        return c_network;
    }

    public void setC_network(String c_network) {
        this.c_network = c_network;
    }

    public String getC_userid() {
        return c_userid;
    }

    public void setC_userid(String c_userid) {
        this.c_userid = c_userid;
    }

    public int getC_port() {
        return c_port;
    }

    public void setC_port(int c_port) {
        this.c_port = c_port;
    }

    public long getC_time() {
        return c_time;
    }

    public void setC_time(long c_time) {
        this.c_time = c_time;
    }

    public String getC_ua() {
        return c_ua;
    }

    public void setC_ua(String c_ua) {
        this.c_ua = c_ua;
    }

    public String getC_uuid() {
        return c_uuid;
    }

    public void setC_uuid(String c_uuid) {
        this.c_uuid = c_uuid;
    }

    public String getC_loc() {
        return c_loc;
    }

    public void setC_loc(String c_loc) {
        this.c_loc = c_loc;
    }

    public long getC_ip() {
        return c_ip;
    }

    public void setC_ip(long c_ip) {
        this.c_ip = c_ip;
    }

    public String getC_mac() {
        return c_mac;
    }

    public void setC_mac(String c_mac) {
        this.c_mac = c_mac;
    }

    public long getC_imei() {
        return c_imei;
    }

    public void setC_imei(long c_imei) {
        this.c_imei = c_imei;
    }

    public String getC_zid() {
        return c_zid;
    }

    public void setC_zid(String c_zid) {
        this.c_zid = c_zid;
    }

    @Override
    public String toString() {
        return "VertexDto{" +
                "c_ip=" + c_ip +
                ", c_network='" + c_network + '\'' +
                ", c_userid='" + c_userid + '\'' +
                ", c_port=" + c_port +
                ", c_time=" + c_time +
                ", c_ua='" + c_ua + '\'' +
                ", c_uuid='" + c_uuid + '\'' +
                ", c_loc='" + c_loc + '\'' +
                ", c_mac='" + c_mac + '\'' +
                ", c_imei=" + c_imei +
                ", c_zid='" + c_zid + '\'' +
                '}';
    }
}
