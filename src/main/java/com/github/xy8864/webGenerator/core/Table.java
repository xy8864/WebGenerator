package com.github.xy8864.webGenerator.core;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 15:06
 * To change this template use File | Settings | File Templates.
 */
public class Table implements Serializable{
	private static final long serialVersionUID=-1509769104419614749L;
	private Column pk;
	private String name;
	private String domain;
	private Set<Column> columns;

	public Table(){}

	public Table(String name, String domain){
		this.name=name;
		this.domain=domain;
	}

	public Column getPk(){
		return pk;
	}

	public void setPk(Column pk){
		this.pk=pk;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getDomain(){
		return domain;
	}

	public void setDomain(String domain){
		this.domain=domain;
	}

	public Set<Column> getColumns(){
		return columns;
	}

	public void setColumns(Set<Column> columns){
		this.columns=columns;
	}

	public void addColumn(Column column){
		if(columns==null)columns=new LinkedHashSet<Column>();
		columns.add(column);
	}
}
