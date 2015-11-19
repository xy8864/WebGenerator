package com.github.xy8864.webGenerator.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 17:53
 * To change this template use File | Settings | File Templates.
 */
public class Config{
	private String dbType;
	private String output;
	private String templateDir;
	private String schema;
	private String domain;
	private String javaMapper;
	private String xmlMapper;
	private String service;
	private String serviceImpl;
	private String controller;

	private List<Column> columns;

	Map<String,String> dbConfig;
	/** 表名 */
	Map<String,Table> tableMap;

	public String getDbType(){
		return dbType;
	}

	public void setDbType(String dbType){
		this.dbType=dbType;
	}

	public String getOutput(){
		return output;
	}

	public void setOutput(String output){
		this.output=output;
	}

	public String getSchema(){
		return schema;
	}

	public void setSchema(String schema){
		this.schema=schema;
	}

	public String getDomain(){
		return domain;
	}

	public void setDomain(String domain){
		this.domain=domain;
	}

	public String getJavaMapper(){
		return javaMapper;
	}

	public void setJavaMapper(String javaMapper){
		this.javaMapper=javaMapper;
	}

	public String getXmlMapper(){
		return xmlMapper;
	}

	public void setXmlMapper(String xmlMapper){
		this.xmlMapper=xmlMapper;
	}

	public String getService(){
		return service;
	}

	public void setService(String service){
		this.service=service;
	}

	public String getServiceImpl(){
		return serviceImpl;
	}

	public void setServiceImpl(String serviceImpl){
		this.serviceImpl=serviceImpl;
	}

	public String getController(){
		return controller;
	}

	public void setController(String controller){
		this.controller=controller;
	}

	public String getTemplateDir(){
		return templateDir;
	}

	public void setTemplateDir(String templateDir){
		this.templateDir=templateDir;
	}

	public List<Column> getColumns(){
		return columns;
	}

	public void setColumns(List<Column> columns){
		this.columns=columns;
	}

	public String getConfig(String key){
		if(dbConfig==null||dbConfig.isEmpty())return null;
		return dbConfig.get(key);
	}

	public void setDbConfig(Map<String, String> dbConfig){
		this.dbConfig=dbConfig;
	}

	public void addDbConfig(String key,String val){
		if(dbConfig==null)dbConfig=new LinkedHashMap<String, String>();
		dbConfig.put(key,val);
	}

	public Map<String, Table> getTableMap(){
		return tableMap;
	}

	public void setTableMap(Map<String, Table> tableMap){
		this.tableMap=tableMap;
	}

	public void addTable(String table,String domain){
		if(tableMap==null)tableMap=new LinkedHashMap<String, Table>();
		tableMap.put(table,new Table(table,domain));
	}
}
