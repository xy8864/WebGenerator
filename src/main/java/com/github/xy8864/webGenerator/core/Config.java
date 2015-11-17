package com.github.xy8864.webGenerator.core;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 17:53
 * To change this template use File | Settings | File Templates.
 */
public class Config{
	private String dbType;
	private String basePath;
	private String schema;
	private String domain;
	private String javaMapper;
	private String xmlMapper;
	private String service;
	private String serviceImpl;
	private String controller;

	List<Column> columns;
}
