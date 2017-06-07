package com.example.service;

import com.example.model.EdgeDto;
import com.example.model.VertexDto;

import java.util.List;

/**
 * Created by wanquan on 2017/6/1.
 */
public interface DataRetrievingService {
    List<EdgeDto> getDataForCreatingEdges();
    List<VertexDto> getDataForCreatingVertex();
    List<EdgeDto> getDataForCreatingEdges(int startPos, int size);
    List<VertexDto> getDataForCreatingVertex(int startPos, int size);
    int getTotalItemNum();
}
