package com.example.titan.parser;

import com.example.model.EdgeDto;
import com.example.titan.common.IUserEdgeParser;
import com.example.titan.common.MD5Edge;
import com.example.titan.common.TitanUtil;
import com.example.titan.constant.GraphConstants;

/**
 * Created by wanquan on 2017/6/1.
 */
public class DBUserEdgeParser implements IUserEdgeParser{
    public MD5Edge parse(EdgeDto edgeDto) throws Exception {
        long from = edgeDto.getC_oimei();
        long to = edgeDto.getC_rimei();
        String label = edgeDto.getC_hmd5();
        MD5Edge edge = new MD5Edge(from, to, label);
        addProperty(edge, GraphConstants.C_DGST, edgeDto.getC_dgst());
        addProperty(edge, GraphConstants.C_T, edgeDto.getC_t());
        addProperty(edge, GraphConstants.C_N, edgeDto.getC_n());
        addProperty(edge, GraphConstants.C_HMD5, edgeDto.getC_hmd5());
        addProperty(edge, GraphConstants.C_PKG, edgeDto.getC_pkg());
        return edge;
    }
    private void addProperty(MD5Edge edge, String propertyName, Object value) {
        if(value != null) {
            edge.addProperty(propertyName, value);
        }
    }
}
