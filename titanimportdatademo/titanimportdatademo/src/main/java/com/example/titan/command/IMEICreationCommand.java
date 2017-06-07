package com.example.titan.command;

import com.example.titan.constant.GraphConstants;
import com.thinkaurelius.titan.core.TitanGraph;
import org.apache.tinkerpop.gremlin.structure.Vertex;

/**
 * Created by wanquan on 2017/5/31.
 */
public class IMEICreationCommand implements Runnable{
    final String imei;
    final TitanGraph graph;
    public IMEICreationCommand(final TitanGraph graph, final String imei) {
        this.imei = imei;
        this.graph = graph;
    }
    private static Vertex createIMEIVertex(TitanGraph graph, String value) {
        Vertex vertex = graph.addVertex();
        vertex.property(GraphConstants.IMEI, value);
        return vertex;
    }
    public void run() {
        createIMEIVertex(graph, imei);
    }
}
