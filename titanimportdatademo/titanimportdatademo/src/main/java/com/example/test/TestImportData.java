package com.example.test;

import com.example.titan.TitanDataFactory;
import com.example.titan.common.TitanUtil;
import com.example.titan.constant.GraphConstants;
import com.thinkaurelius.titan.core.TitanGraph;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Iterator;


/**
 * Created by wanquan on 2017/6/1.
 */
public class TestImportData {

    public static void main(String[] args) throws Exception {
        TitanGraph graph = TitanUtil.createGraph("E:\\data\\project\\titanimportdatademo\\titanimportdatademo\\titanimportdatademo\\src\\main\\resources\\titan-cassandra-es.properties");
        TitanDataFactory titanDataFactory = new TitanDataFactory();
//        titanDataFactory.makeIndex(graph);
//        titanDataFactory.load(graph, GraphConstants.DEFAULT_BATCTH_COUNT);
        GraphTraversalSource g = graph.traversal();
//        Iterator iterator = g.V().has(GraphConstants.C_IMEI, 1L).next().values("c_userid", "c_ip", "c_network", "c_imei", "c_mac");
//        while (iterator.hasNext()) {
//            Object o = iterator.next();
//            System.out.println("======" + o);
//        }
        Vertex pluto = g.V().has(GraphConstants.C_IMEI, 28L).next();
        System.out.println("***************************" + pluto);
        GraphTraversal<Vertex,Vertex> vertexVertexGraphTraversal = g.V(pluto).in(GraphConstants.SEND).has(GraphConstants.C_HMD5);
        while (vertexVertexGraphTraversal.hasNext()) {
            Vertex vertex = vertexVertexGraphTraversal.next();
            System.out.println("======"+vertex.value(GraphConstants.C_IMEI));
        }
        System.out.println("--------------------------" + g.V(pluto).out(GraphConstants.SEND).values(GraphConstants.C_DGST,GraphConstants.C_T,GraphConstants.C_N,GraphConstants.C_HMD5));
        graph.close();
    }
}
