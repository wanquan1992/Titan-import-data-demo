package com.example.titan;

import com.example.model.EdgeDto;
import com.example.model.VertexDto;
import com.example.service.DataRetrievingService;
import com.example.service.impl.DataRetrievingServiceImpl;
import com.example.titan.common.IMEIVertex;
import com.example.titan.common.IUserEdgeParser;
import com.example.titan.common.IUserVertexParser;
import com.example.titan.common.MD5Edge;
import com.example.titan.constant.GraphConstants;
import com.example.titan.parser.DBIMEIVertexParser;
import com.example.titan.parser.DBUserEdgeParser;
import com.thinkaurelius.titan.core.Multiplicity;
import com.thinkaurelius.titan.core.PropertyKey;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanquan on 2017/5/31.
 */
public class TitanDataFactory {
//    @Resource
//    private DataRetrievingService dataRetrievingService;
    private  void loadEdge(List<EdgeDto> edgeDtoList, int batchCount, IUserEdgeParser parser, BatchLoader loader) throws Exception {
        int count = 0;
        for (EdgeDto edgeDto : edgeDtoList) {
            MD5Edge edge = parser.parse(edgeDto);
            if (edge == null) {
                continue;
            }
            loader.addEdge(edge.getFrom(),edge.getTo(),GraphConstants.SEND,edge.getProperties());
            count++;
            if (count % batchCount == 0) {
                loader.commit();
            }
        }
        loader.commit();
        System.out.println("load:" + count + " edges");
    }
    private  void loadVertex(List<VertexDto> vertexDtoList, int batchCount, IUserVertexParser parser, BatchLoader loader) throws Exception {
        int count = 0;
        for (VertexDto vertexDto : vertexDtoList) {
            IMEIVertex vertex = parser.parse(vertexDto);
            if (vertex == null) {
                continue;
            }
            loader.addVertex(vertex.getId(), vertex.getLabel(),vertex.getFields());
            count++;
            if (count % batchCount == 0) {
                loader.commit();
            }
        }
        loader.commit();
        System.out.println("load:" + count + " vertices");
    }
    public  void load(final TitanGraph graph, final int batchCount) throws Exception {
        ApplicationContext ctx = ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataRetrievingService dataRetrievingService = ctx.getBean("dataRetrievingServiceImpl", DataRetrievingServiceImpl.class);
        List<VertexDto> vertexDtoList = dataRetrievingService.getDataForCreatingVertex();
        IUserVertexParser parser = new DBIMEIVertexParser();
        BatchLoader loader = new BatchLoader(graph);
        loadVertex(vertexDtoList, batchCount, parser, loader);
        List<EdgeDto> edgeDtoList = dataRetrievingService.getDataForCreatingEdges();
        IUserEdgeParser parser1 = new DBUserEdgeParser();
        loadEdge(edgeDtoList, batchCount, parser1, loader);
    }
    public void makeIndex(final TitanGraph graph) {
        TitanManagement mgmt = graph.openManagement();
        if (mgmt.getGraphIndex(GraphConstants.C_IMEI) == null) {
            final PropertyKey characterKey = mgmt.makePropertyKey(GraphConstants.C_IMEI).dataType(Long.class).make();
            mgmt.buildIndex(GraphConstants.C_IMEI, Vertex.class).addKey(characterKey).unique().buildCompositeIndex();
        }
        mgmt.commit();
    }

    public static void main(String args[]) throws Exception {
        new TitanDataFactory().load(null, 1);
    }
}
