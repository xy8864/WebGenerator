package com.github.xy8864.webGenerator.core;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 14:49
 * To change this template use File | Settings | File Templates.
 */
public class Column implements Serializable{
	private static final long serialVersionUID=-1509769104419614749L;
	private String name;
	/** */
	private String jdbcType;
	private String comment;

	private String javaType;
	private boolean pk;

	public Column(){}

	public Column(String name, String jdbcType, String comment){
		this.name=name;
		this.jdbcType=jdbcType;
		this.comment=comment;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getJdbcType(){
		return jdbcType;
	}

	public void setJdbcType(String jdbcType){
		this.jdbcType=jdbcType;
	}

	public String getComment(){
		return comment;
	}

	public void setComment(String comment){
		this.comment=comment;
	}

	public String getJavaType(){
		return javaType;
	}

	public void setJavaType(String javaType){
		this.javaType=javaType;
	}

	public boolean isPk(){
		return pk;
	}

	public void setPk(boolean pk){
		this.pk=pk;
	}

}
