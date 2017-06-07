package com.example.titan.common;

import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.Parameter;
import com.thinkaurelius.titan.core.schema.TitanGraphIndex;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.thinkaurelius.titan.core.schema.TitanManagement.IndexBuilder;
import org.apache.log4j.Logger;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

public class SchemaUtil {
    private static final Logger LOGGER = Logger.getLogger(SchemaUtil.class);

    public static final String backendName = "search";
    private static final String compositeIndexPrefix = "by";
    private static final String mixedIndexPrefix = "byWild";
    
    // PropertyKey

    public static PropertyKey getOrCreatePropertyKey(TitanManagement mgmt, String name) {
        return getOrCreatePropertyKey(mgmt, name, String.class);
    }

    public static PropertyKey getOrCreatePropertyKey(TitanManagement mgmt, String name, Class<?> clz) {
        return getOrCreatePropertyKey(mgmt, name, clz, Cardinality.SINGLE);
    }

    public static PropertyKey getOrCreatePropertyKey(TitanManagement mgmt, String name, Class<?> clz, Cardinality car) {
        PropertyKey key = mgmt.getPropertyKey(name);
        if (key != null) {
            return key;
        }
        return mgmt.makePropertyKey(name).dataType(clz).cardinality(car).make();
    }
    
    public static PropertyKey getPropertyKey(TitanManagement mgmt, String name) {
        PropertyKey key = mgmt.getPropertyKey(name);
        if (key == null)
        	throw new IllegalArgumentException("Can not find property key for name:" + name);
        return key;
    }
    
    private static String toTitleString(String value) {
        assert value.length() > 0;
        return (value.charAt(0)+"").toUpperCase() + value.substring(1);
    }
    
    public static void buildBothVertexCompositeMixedIndex(TitanManagement mgmt, String baseIndexName, PropertyKey ...keys) {
        buildBothVertexCompositeMixedIndex(mgmt, baseIndexName, false, keys);
    }
    
    public static void buildBothVertexCompositeMixedIndex(TitanManagement mgmt, String baseIndexName, boolean isUniq, PropertyKey ...keys) {
        String titleName = toTitleString(baseIndexName);
        String compositeName = compositeIndexPrefix + titleName;
        String mixedName = mixedIndexPrefix + titleName;
        buildVertexCompositeIndex(mgmt, compositeName, isUniq, keys);
        buildVertexMixedIndex(mgmt, mixedName, backendName, keys);
    }
    
    // vertex index

    public static void buildVertexCompositeIndex(TitanManagement mgmt, String indexName, PropertyKey ...keys) {
        buildVertexCompositeIndex(mgmt, indexName, false, keys);
    }

    public static void buildVertexCompositeIndex(TitanManagement mgmt, String indexName, boolean isUniq, PropertyKey ...keys) {
        buildVertexCompositeIndex(mgmt, indexName, isUniq, null, keys);
    }
    
    public static void buildVertexCompositeIndex(TitanManagement mgmt, String indexName, boolean isUniq, VertexLabel label, PropertyKey ...keys) {
        if (mgmt.containsGraphIndex(indexName)) {
            LOGGER.warn(indexName + " already exists");
            return;
        }
        IndexBuilder builder = mgmt.buildIndex(indexName, Vertex.class);
        if (isUniq) {
            builder  = builder.unique();
        }
        for (PropertyKey k : keys) {
            builder = builder.addKey(k);
        }
        if (label != null) {
            builder = builder.indexOnly(label);
        }
        TitanGraphIndex index = builder.buildCompositeIndex();
    }
    
    public static void buildVertexMixedIndex(TitanManagement mgmt, String indexName, String backendName, PropertyKey ...keys) {
        buildVertexMixedIndex(mgmt, indexName, backendName, null, keys);
    }
    
    public static void buildVertexMixedIndex(TitanManagement mgmt, String indexName, String backendName, VertexLabel label, PropertyKey ...keys) {
        if (mgmt.containsGraphIndex(indexName)) {
            LOGGER.warn(indexName + " already exists");
            return;
        }
        IndexBuilder builder = mgmt.buildIndex(indexName, Vertex.class);
        for (PropertyKey k : keys) {
            // new Parameter<>(ParameterType.MAPPING.getName(), Mapping.TEXTSTRING)
            builder = builder.addKey(k);
        }
        if (label != null) {
            builder = builder.indexOnly(label);
        }
        builder.buildMixedIndex(backendName);
    }

    public static void buildVertexMixedIndex(TitanManagement mgmt, String indexName, PropertyKey key, Parameter ...vars) {
        buildVertexMixedIndex(mgmt, indexName, backendName, key, vars);
    }

    public static void buildVertexMixedIndex(TitanManagement mgmt, String indexName, String backendName, PropertyKey key, Parameter ...vars) {
        if (mgmt.containsGraphIndex(indexName)) {
            LOGGER.warn(indexName + " already exists");
            return;
        }
        IndexBuilder builder = mgmt.buildIndex(indexName, Vertex.class);
        builder = builder.addKey(key, vars);
        builder.buildMixedIndex(backendName);
    }

    //edge index
    public static void buildVertexCentricIndex(TitanManagement mgmt, String edgeLabel, String indexName, PropertyKey ...keys) {
        EdgeLabel label = mgmt.getEdgeLabel(edgeLabel);
        buildVertexCentricIndex(mgmt, label, indexName, keys);
    }

    public static void buildEdgeCompositeIndex(TitanManagement mgmt, String indexName, boolean isUniq, PropertyKey ...keys) {
        if (mgmt.containsGraphIndex(indexName)) {
            return;
        }
        IndexBuilder builder = mgmt.buildIndex(indexName, Edge.class);
        if(isUniq)
        	builder.unique();
        for (PropertyKey key : keys) {
            builder = builder.addKey(key);
        }
        builder.buildCompositeIndex();
    }

    public static void buildEdgeMixedIndex(TitanManagement mgmt, String indexName, String backendName, PropertyKey key, Parameter ...vars) {
        if (mgmt.containsGraphIndex(indexName)) {
        	LOGGER.warn(indexName + " already exists");
            return;
        }
        IndexBuilder builder = mgmt.buildIndex(indexName, Edge.class);
        builder = builder.addKey(key, vars);
        builder.buildMixedIndex(backendName);
    }

    public static void buildVertexCentricIndex(TitanManagement mgmt, EdgeLabel label, String indexName, PropertyKey ...keys) {
        if (mgmt.containsRelationIndex(label, indexName)) {
            return;
        }
        mgmt.buildEdgeIndex(label, indexName, Direction.BOTH, keys);
    }

    // Edge Label
    public static EdgeLabel createEdgeLabel(TitanManagement mgmt, String labelName) {
        return createEdgeLabel(mgmt, labelName, Multiplicity.SIMPLE);
    }

    public static EdgeLabel createEdgeLabel(TitanManagement mgmt, String labelName, Multiplicity multiplicity) {
        if (!mgmt.containsEdgeLabel(labelName)) {
            return mgmt.makeEdgeLabel(labelName).multiplicity(multiplicity).make();
        } else {
            return mgmt.getEdgeLabel(labelName);
        }
    }

    // Vertex Label
    public static VertexLabel createVertexLabel(TitanManagement mgmt, String labelName) {
        if (!mgmt.containsVertexLabel(labelName)) {
            return mgmt.makeVertexLabel(labelName).make();
        } else {
            return mgmt.getVertexLabel(labelName);
        }
    }
    
    public static VertexLabel ensureVertexLabel(TitanManagement mgmt, String labelName) {
        VertexLabel label = mgmt.getVertexLabel(labelName);
        if (label == null) {
            throw new IllegalArgumentException("Can not find label vertex for labl:" + labelName);
        }
        return label;
    }
}