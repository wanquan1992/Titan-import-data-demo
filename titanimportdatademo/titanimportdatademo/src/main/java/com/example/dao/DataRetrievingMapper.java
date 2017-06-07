package com.example.dao;

import com.example.model.EdgeDto;
import com.example.model.VertexDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanquan on 2017/6/1.
 */
public interface DataRetrievingMapper {
    List<EdgeDto> getDataForCreatingEdges();

    List<VertexDto> getDataForCreatingVertex();

    List<EdgeDto> getDataForCreatingEdgesBatch(@Param("startPos") int startPos, @Param("size") int size);

    List<VertexDto> getDataForCreatingVertexBatch(@Param("startPos") int startPos, @Param("size") int size);

    int getTotalItemNum();
}
