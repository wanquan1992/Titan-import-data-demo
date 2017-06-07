package com.example.titan.common;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.thinkaurelius.titan.graphdb.idmanagement.IDManager.VertexIDType;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by zhangshixin on 2016/12/15.
 */
public class TitanUtil {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static TitanGraph createGraph(String conf) {
        TitanGraph g = TitanFactory.open(conf);
        return g;
    }

    public static TitanGraph createGraph(Configuration conf) {
        TitanGraph g = TitanFactory.open(conf);
        return g;
    }

    public static Vertex getVertex(TitanGraph graph, String key, String value) {
        @SuppressWarnings("unchecked")
        Iterator<TitanVertex> it = graph.query().has(key, value).limit(1).vertices()
                                   .iterator();
        if (it.hasNext()) {
            return (Vertex) it.next();
        }
        return null;
    }

    public static Configuration setStorageName(Configuration conf, String name) {
        conf.setProperty("storage.cassandra.keyspace", name);
        conf.setProperty("index.search.index-name", name);
        return conf;
    }

    public static Configuration setBackendHost(Configuration conf, String host) {
        conf.setProperty("storage.hostname", host);
        conf.setProperty("index.search.hostname", host);
        return conf;
    }

    public static void sleepRandomTimes(boolean atLeastOneSeconds) {
        try {
            long times = (long) (RANDOM.nextDouble() * 1000);
            if (atLeastOneSeconds) {
                times += 1000;
            }
            Thread.sleep(times);
        } catch (InterruptedException e) {
        }
    }

    public static void closeOtherInstance(TitanGraph graph) {
        TitanManagement mgmt = graph.openManagement();

        for (String i : mgmt.getOpenInstances()) {
            if (!i.contains("current")) {
                mgmt.forceCloseInstance(i);
            }
        }
        mgmt.commit();
    }

    public static boolean isVertexID(long id) {
        return VertexIDType.NormalVertex.is(id);
    }

}