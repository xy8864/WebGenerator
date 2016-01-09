package com.github.xy8864.webGenerator.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 14:51
 * To change this template use File | Settings | File Templates.
 */
public enum JdbcType{
	ARRAY(null,null),
	BIT("Boolean","BOOLEAN"),
	TINYINT("Integer","INTEGER"),
	SMALLINT("Integer","INTEGER"),
	INTEGER("Integer","INTEGER"),
	INT("Integer","INTEGER"),
	BIGINT("Long","BIGINT"),
	FLOAT("Float","FLOAT"),
	REAL(null,null),
	DOUBLE("Double","DOUBLE"),
	NUMERIC(null,null),
	DECIMAL("java.math.BigDecimal","DECIMAL"),
	CHAR("Char","CHAR"),
	VARCHAR("String","VARCHAR"),
	VARCHAR2("String","VARCHAR2"),
	LONGVARCHAR("String","LONGVARCHAR"),
	DATETIME("java.util.Date","TIMESTAMP"),
	DATE("java.util.Date","TIMESTAMP"),
	TIME("java.util.Date","TIMESTAMP"),
	TIMESTAMP("java.util.Date","TIMESTAMP"),
	BINARY("byte[]","BINARY"),
	VARBINARY("byte[]","VARBINARY"),
	LONGVARBINARY("byte[]","LONGVARBINARY"),
	NULL(null,null),
	OTHER(null,null),
	BLOB("byte[]","BLOB"),
	CLOB("char[]","CLOB"),
	BOOLEAN("Boolean","BOOLEAN"),
	CURSOR(null,null), // Oracle
	UNDEFINED(null,null),
	NVARCHAR("String","NVARCHAR"), // JDK6
	NCHAR("char[]","NCHAR"), // JDK6
	NCLOB("char[]","NCLOB"), // JDK6
	TEXT("String","TEXT"), // JDK6
	LONGTEXT("String","LONGTEXT"), // JDK6
	STRUCT(null,null);

	final String javaType;
	final String jdbcType;
	private static Map<String,JdbcType> codeLookup = new HashMap<String,JdbcType>();

	static {
		for (JdbcType type : JdbcType.values()) {
			codeLookup.put(type.name(), type);
		}
	}

	JdbcType(String javaType,String jdbcType) {
		this.javaType = javaType;
		this.jdbcType = jdbcType;
	}

	public static JdbcType forCode(String type)  {
		return codeLookup.get(type);
	}

	/**
	 * @param dbType 数据库的类型,大部分和jdbcType一样,一些不一样
	 * @return
	 */
	public static String forJavaType(String dbType)  {
		if(dbType==null||dbType.length()<1)return null;
		JdbcType e=codeLookup.get(dbType.toUpperCase());
		return e==null?null:e.javaType;
	}
}
