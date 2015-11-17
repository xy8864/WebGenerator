package com.github.xy8864.webGenerator.marker;

import java.util.HashMap;
import java.util.Map;

import com.github.xy8864.webGenerator.core.Config;
import com.github.xy8864.webGenerator.core.Table;
import com.github.xy8864.webGenerator.engine.Engine;
import com.github.xy8864.webGenerator.engine.FreeMarkerEngine;
import org.apache.commons.lang3.StringUtils;

public class DefaultCodeMarker implements CodeMarker{
	Engine engine;
	Config config;
	public DefaultCodeMarker(Config config){
		this.config=config;
		this.engine=new FreeMarkerEngine(config.getTemplateDir());
	}


	public void build(){
		domain();
		javaMapper();
		xmlMapper();
		service();
		serviceImpl();
		controller();
	}


	void domain(){
		Map<String,Object> map=new HashMap<String, Object>();

		map.put("StringUtil", FreeMarkerEngine.useStaticPackage("com.github.xy8864.webGenerator.util.StringUtil"));
		map.put("StringUtils", FreeMarkerEngine.useStaticPackage("org.apache.commons.lang3.StringUtils"));
		String filePath;

		for(Table table:config.getTableMap().values()){
			map.put("package",config.getDomain());
			map.put("className",table.getDomain());
			map.put("fieldList", table.getColumns());

			filePath=config.getBasePath()+StringUtils.replace(config.getDomain(),".","/")+"/"+table.getDomain()+".java";
			engine.writeToFile("domain.ftl",map,filePath);
			//System.out.println(engine.toString("domain.ftl", map));
		}

	}


	void javaMapper(){

	}


	void xmlMapper(){

	}


	void service(){

	}


	void serviceImpl(){

	}


	void controller(){

	}
}
