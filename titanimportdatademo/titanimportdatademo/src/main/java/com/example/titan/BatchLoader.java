package com.example.titan;

import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanTransaction;
import com.thinkaurelius.titan.core.VertexLabel;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.*;

/**
 * Created by zhangshixin on 2016/12/15.
 */
public class BatchLoader {
    private TitanGraph graph;
    private GraphTraversalSource g;
    private volatile TitanTransaction transaction = null;

    public BatchLoader(TitanGraph graph) {
        this.graph = graph;
        g = graph.traversal();
    }

    private void createTransaction() {
        if (transaction == null) {
            transaction = graph.newTransaction();
        }
    }

    private Edge getEdgeIfExists(Vertex out, Vertex in, String label) {
        Traversal<Vertex, Edge> it = g.V(out).outE(label).filter(__.inV().is(in));
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }


    //    public Vertex addVertex(long id, String label, Map<String, Object> fields) {
//        createTransaction();
//        VertexLabel vertexLabel = transaction.getVertexLabel(label);
//        Vertex vertex = transaction.addVertex(id, vertexLabel);
//        attachProperties(vertex, fields);
//        return vertex;
//    }
    public Vertex addVertex(long imei, String label, Map<String, Object> fields) {
        createTransaction();
        Vertex vertex = graph.addVertex(label);
        attachProperties(vertex, fields);
        return vertex;
    }

    private static void attachProperties(Element element, Map<String, Object> properties) {
        for (String key : properties.keySet()) {
            Object value = properties.get(key);
            if (value instanceof List || value instanceof Set) {
                Collection<?> collection = (Collection<?>) value;
                for (Object v : collection) {
                    element.property(key, v);
                }
            } else {
                element.property(key, value);
            }
        }
    }

    //    public Edge addEdge(long from, long to, String label, Map<String, Object> properties) {
//        createTransaction();
//        Vertex outVertex = transaction.getVertex(from);
//        Vertex inVertex = transaction.getVertex(to);
//        Edge edge = outVertex.addEdge(label, inVertex);
//
//        attachProperties(edge, properties);
//
//        return edge;
//    }
    public Edge addEdge(long c_oimei, long c_rimei, String label, Map<String, Object> properties) {
        createTransaction();
        Vertex outVertex = get(graph, "c_imei", c_oimei);
        Vertex inVertex = get(graph, "c_imei", c_rimei);
        Edge edge = outVertex.addEdge(label,inVertex);
        attachProperties(edge, properties);
        return edge;
    }
    private  Vertex get(TitanGraph graph, String key, Object value) {
        final GraphTraversalSource g = graph.traversal();
        final Iterator<Vertex> it = g.V().has(key, value);
        return it.hasNext() ? it.next() : null;
    }

    public void commit() {
        if (transaction == null) {
            return;
        }
        transaction.commit();
        transaction = null;
    }

}
