package com.example.titan;


import com.example.titan.common.SchemaUtil;
import com.example.titan.common.TitanUtil;
import com.thinkaurelius.titan.core.PropertyKey;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.schema.Mapping;
import com.thinkaurelius.titan.core.schema.Parameter;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.thinkaurelius.titan.graphdb.types.ParameterType;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.List;

/*
 * Created by Icarus on 2017/03/07.
 */
public class TitanGraphDB {
	private TitanGraph graph;
	private TitanManagement mgmt;
	
	public TitanGraphDB(String graphConfPath) throws Exception {
        Configuration graphConf = new PropertiesConfiguration(graphConfPath);
		graph = TitanUtil.createGraph(graphConf);
		mgmt = graph.openManagement();
	}
	
	public void CreateProperty(String propertyName, Class<?> dataType) {
		SchemaUtil.getOrCreatePropertyKey(mgmt, propertyName, dataType);
	}
	
	public void CreateVertexLabels(List<String> vertexLabels) {
		for (String vertexLabel : vertexLabels)
			SchemaUtil.createVertexLabel(mgmt, vertexLabel);
	}
	
	public void CreateEdgeLabels(List<String> edgeLabels) {
		for (String edgeLabel : edgeLabels)
			SchemaUtil.createEdgeLabel(mgmt, edgeLabel);
	}
	
	public void CreateCompositeIndex(String propertyName, Boolean uniq) {
		PropertyKey propertyKey = SchemaUtil.getPropertyKey(mgmt, propertyName);
		String indexName = "by_" + propertyName;
		SchemaUtil.buildVertexCompositeIndex(mgmt, indexName, uniq, propertyKey);
	}
	
	public void CreateMixedIndex(String propertyName) {
		PropertyKey propertyKey = SchemaUtil.getPropertyKey(mgmt, propertyName);
		String indexName = "byWild_" + propertyName;
		SchemaUtil.buildVertexMixedIndex(mgmt, indexName, propertyKey, new Parameter<Mapping>(ParameterType.MAPPING.getName(), Mapping.TEXTSTRING));
	}
	
	public void Commit(){
		graph.tx().commit();
	}
	
}
