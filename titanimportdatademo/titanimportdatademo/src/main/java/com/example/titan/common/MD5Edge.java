package com.example.titan.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangshixin on 2016/12/15.
 */
public class MD5Edge {
    private long from;
    private long to;
    private String label;
    private Map<String, Object> properties = new HashMap<String, Object>();

    public MD5Edge(long from, long to, String label) {
        this.from = from;
        this.to = to;
        this.label = label;
    }

    public void setProperties(Map<String, Object> properties) {
        assert properties != null;
        this.properties = properties;
    }

    public void addProperty(String name, Object value) {
        properties.put(name, value);
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }

    public String getLabel() {
        return label;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }
}
