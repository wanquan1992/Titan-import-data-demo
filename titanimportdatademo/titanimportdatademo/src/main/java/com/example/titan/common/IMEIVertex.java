package com.example.titan.common;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangshixin on 2016/12/15.
 */
public class IMEIVertex {
    private long id;
    private String label;
    // object is value , list , set
    private Map<String, Object> fields = new HashMap<String, Object>();

    public IMEIVertex(long id, String label) {
        this.id = id;
        this.label = label;
    }

    public void setFields(Map<String, Object> fields) {
        assert fields != null;
        this.fields = fields;
    }

    public void addField(String name, Object value) {
        fields.put(name, value);
    }

    public long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Map<String, Object> getFields() {
        return fields;
    }
}
