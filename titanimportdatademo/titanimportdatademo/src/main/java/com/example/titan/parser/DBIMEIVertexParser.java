package com.example.titan.parser;

import com.example.model.VertexDto;
import com.example.titan.common.IUserVertexParser;
import com.example.titan.common.TitanUtil;
import com.example.titan.common.IMEIVertex;
import com.example.titan.constant.GraphConstants;

import java.util.Date;

/**
 * Created by wanquan on 2017/6/1.
 */
public class DBIMEIVertexParser implements IUserVertexParser{
    private final String LABEL = "imei";
    public IMEIVertex parse(VertexDto vertexDto) throws Exception {
        long id = vertexDto.getC_imei();
        IMEIVertex vertex = new IMEIVertex(id, LABEL);
        addProperty(vertex, GraphConstants.C_IP, vertexDto.getC_ip());
        addProperty(vertex, GraphConstants.C_NETWORK, vertexDto.getC_userid());
        addProperty(vertex, GraphConstants.C_USERID, vertexDto.getC_userid());
        addProperty(vertex, GraphConstants.C_PORT,vertexDto.getC_port());
        addProperty(vertex, GraphConstants.C_TIME, vertexDto.getC_time());
        addProperty(vertex, GraphConstants.C_UA, vertexDto.getC_ua());
        addProperty(vertex, GraphConstants.C_UUID, vertexDto.getC_uuid());
        addProperty(vertex, GraphConstants.C_LOC, vertexDto.getC_loc());
        addProperty(vertex, GraphConstants.C_MAC, vertexDto.getC_loc());
        addProperty(vertex, GraphConstants.C_IMET, vertexDto.getC_imei());
        addProperty(vertex, GraphConstants.C_ZID, vertexDto.getC_zid());
        return vertex;
    }
    private void addProperty(IMEIVertex vertex, String fieldName, Object value) {
        if(value != null)
            vertex.addField(fieldName, value);
    }

}
