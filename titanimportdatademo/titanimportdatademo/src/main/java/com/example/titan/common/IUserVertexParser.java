package com.example.titan.common;

import com.example.model.VertexDto;
import org.apache.log4j.Logger;

/**
 * Created by zhangshixin on 2016/12/15.
 */
public interface IUserVertexParser {
    public static final Logger LOGGER = Logger.getLogger(IUserVertexParser.class);

    IMEIVertex parse(VertexDto vertexDto)throws Exception;
}
