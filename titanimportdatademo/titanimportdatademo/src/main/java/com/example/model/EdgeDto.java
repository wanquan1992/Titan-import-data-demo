package com.example.model;

/**
 * Created by wanquan on 2017/6/1.
 */
public class EdgeDto {
    private long c_oimei;
    private long c_rimei;
    private String c_dgst;
    private String c_t;
    private String c_n;
    private String c_hmd5;
    private String c_pkg;

    public long getC_oimei() {
        return c_oimei;
    }

    public void setC_oimei(long c_oimei) {
        this.c_oimei = c_oimei;
    }

    public long getC_rimei() {
        return c_rimei;
    }

    public void setC_rimei(long c_rimei) {
        this.c_rimei = c_rimei;
    }

    public String getC_dgst() {
        return c_dgst;
    }

    public void setC_dgst(String c_dgst) {
        this.c_dgst = c_dgst;
    }

    public String getC_t() {
        return c_t;
    }

    public void setC_t(String c_t) {
        this.c_t = c_t;
    }

    public String getC_n() {
        return c_n;
    }

    public void setC_n(String c_n) {
        this.c_n = c_n;
    }

    public String getC_hmd5() {
        return c_hmd5;
    }

    public void setC_hmd5(String c_hmd5) {
        this.c_hmd5 = c_hmd5;
    }

    public String getC_pkg() {
        return c_pkg;
    }

    public void setC_pkg(String c_pkg) {
        this.c_pkg = c_pkg;
    }

    @Override
    public String toString() {
        return "EdgeDto{" +
                "c_oimei=" + c_oimei +
                ", c_rimei=" + c_rimei +
                ", c_dgst='" + c_dgst + '\'' +
                ", c_t='" + c_t + '\'' +
                ", c_n='" + c_n + '\'' +
                ", c_hmd5='" + c_hmd5 + '\'' +
                ", c_pkg='" + c_pkg + '\'' +
                '}';
    }
}
