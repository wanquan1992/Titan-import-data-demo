package com.example.service.impl;

import com.example.dao.DataRetrievingMapper;
import com.example.model.EdgeDto;
import com.example.model.VertexDto;
import com.example.service.DataRetrievingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanquan on 2017/6/1.
 */
@Service
public class DataRetrievingServiceImpl implements DataRetrievingService {
    @Resource
    private DataRetrievingMapper dataRetrievingMapper;
    public List<EdgeDto> getDataForCreatingEdges() {
        return dataRetrievingMapper.getDataForCreatingEdges();
    }

    public List<VertexDto> getDataForCreatingVertex() {
        return dataRetrievingMapper.getDataForCreatingVertex();
    }

    public List<EdgeDto> getDataForCreatingEdges(int startPos, int size) {
        return dataRetrievingMapper.getDataForCreatingEdgesBatch(startPos, size);
    }

    public List<VertexDto> getDataForCreatingVertex(int startPos, int size) {
        return dataRetrievingMapper.getDataForCreatingVertexBatch(startPos, size);
    }

    public int getTotalItemNum() {
        return dataRetrievingMapper.getTotalItemNum();
    }
}
