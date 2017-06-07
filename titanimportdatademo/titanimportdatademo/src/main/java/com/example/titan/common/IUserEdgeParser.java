package com.example.titan.common;

import com.example.model.EdgeDto;
import org.apache.log4j.Logger;

/**
 * Created by zhangshixin on 2016/12/15.
 */
public interface IUserEdgeParser {
    public static final Logger LOGGER = Logger.getLogger(IUserEdgeParser.class);
    MD5Edge parse(EdgeDto edgeDto) throws Exception;
}
