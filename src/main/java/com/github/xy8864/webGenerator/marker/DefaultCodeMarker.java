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
			map.put("domainName",table.getDomain());
			map.put("fieldList", table.getColumns());

			filePath=config.getOutput()+StringUtils.replace(config.getDomain(),".","/")+"/"+table.getDomain()+".java";
			engine.writeToFile("domain.ftl",map,filePath);
			//System.out.println(engine.toString("domain.ftl", map));
		}

	}


	void javaMapper(){
		Map<String,Object> map=new HashMap<String, Object>();

		map.put("StringUtil", FreeMarkerEngine.useStaticPackage("com.github.xy8864.webGenerator.util.StringUtil"));
		map.put("StringUtils", FreeMarkerEngine.useStaticPackage("org.apache.commons.lang3.StringUtils"));
		String filePath;

		for(Table table:config.getTableMap().values()){
			map.put("package",config.getJavaMapper());
			map.put("domainPackage",config.getDomain());
			map.put("domainName",table.getDomain());
			//map.put("fieldList", table.getColumns());

			filePath=config.getOutput()+StringUtils.replace(config.getJavaMapper(),".","/")+"/"+table.getDomain()+"Mapper.java";
			engine.writeToFile("javaMapper.ftl",map,filePath);
		}
	}


	void xmlMapper(){
		Map<String,Object> map=new HashMap<String, Object>();

		map.put("StringUtil", FreeMarkerEngine.useStaticPackage("com.github.xy8864.webGenerator.util.StringUtil"));
		map.put("StringUtils", FreeMarkerEngine.useStaticPackage("org.apache.commons.lang3.StringUtils"));
		String filePath;

		for(Table table:config.getTableMap().values()){
			map.put("package",config.getXmlMapper());
			map.put("domainPackage",config.getDomain());
			map.put("domainName",table.getDomain());
			map.put("tableName",table.getName());
			map.put("fieldList", table.getColumns());
			map.put("pk", table.getPk());

			filePath=config.getOutput()+StringUtils.replace(config.getXmlMapper(),".","/")+"/mapping/"+table.getDomain()+".xml";
			engine.writeToFile("xmlMapper.ftl",map,filePath);
			//System.out.println(engine.toString("domain.ftl", map));
		}
	}


	void service(){
		Map<String,Object> map=new HashMap<String, Object>();

		map.put("StringUtil", FreeMarkerEngine.useStaticPackage("com.github.xy8864.webGenerator.util.StringUtil"));
		String filePath;

		for(Table table:config.getTableMap().values()){
			map.put("package",config.getService());
			map.put("domainPackage",config.getDomain());
			map.put("domainName",table.getDomain());

			filePath=config.getOutput()+StringUtils.replace(config.getService(),".","/")+"/"+table.getDomain()+"Service.java";
			engine.writeToFile("service.ftl",map,filePath);
			//System.out.println(engine.toString("domain.ftl", map));
		}
	}


	void serviceImpl(){
		Map<String,Object> map=new HashMap<String, Object>();

		map.put("StringUtil", FreeMarkerEngine.useStaticPackage("com.github.xy8864.webGenerator.util.StringUtil"));
		String filePath;

		for(Table table:config.getTableMap().values()){
			map.put("package",config.getServiceImpl());
			map.put("domainPackage",config.getDomain());
			map.put("javaMapperPackage",config.getJavaMapper());
			map.put("servicePackage",config.getService());

			map.put("domainName",table.getDomain());


			filePath=config.getOutput()+StringUtils.replace(config.getServiceImpl(),".","/")+"/"+table.getDomain()+"ServiceImpl.java";
			engine.writeToFile("service.impl.ftl",map,filePath);
			//System.out.println(engine.toString("domain.ftl", map));
		}
	}


	void controller(){
		if(StringUtils.isBlank(config.getController())){
			return;
		}

		Map<String,Object> map=new HashMap<String, Object>();

		map.put("StringUtil", FreeMarkerEngine.useStaticPackage("com.github.xy8864.webGenerator.util.StringUtil"));
		String filePath;

		for(Table table:config.getTableMap().values()){
			map.put("package",config.getController());
			map.put("domainPackage",config.getDomain());
			map.put("javaMapperPackage",config.getJavaMapper());
			map.put("servicePackage",config.getService());

			map.put("domainName",table.getDomain());

			filePath=config.getOutput()+StringUtils.replace(config.getController(),".","/")+"/"+table.getDomain()+"Controller.java";
			engine.writeToFile("controller.ftl",map,filePath);
			//System.out.println(engine.toString("domain.ftl", map));
		}
	}
}
