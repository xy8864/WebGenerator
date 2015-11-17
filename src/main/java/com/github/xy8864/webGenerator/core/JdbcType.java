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
	ARRAY(""),
	BIT("Boolean"),
	TINYINT("Integer"),
	SMALLINT("Integer"),
	INTEGER("Integer"),
	INT("Integer"),
	BIGINT("Long"),
	FLOAT("Float"),
	REAL(null),
	DOUBLE("Double"),
	NUMERIC(""),
	DECIMAL("java.math.BigDecimal"),
	CHAR("Char"),
	VARCHAR("String"),
	VARCHAR2("String"),
	LONGVARCHAR("String"),
	DATE("java.util.Date"),
	TIME("java.util.Date"),
	TIMESTAMP("java.util.Date"),
	BINARY("byte[]"),
	VARBINARY("byte[]"),
	LONGVARBINARY("byte[]"),
	NULL(null),
	OTHER(null),
	BLOB("byte[]"),
	CLOB("char[]"),
	BOOLEAN("Boolean"),
	CURSOR(null), // Oracle
	UNDEFINED(null),
	NVARCHAR("String"), // JDK6
	NCHAR("char[]"), // JDK6
	NCLOB("char[]"), // JDK6
	STRUCT(null);

	final String javaType;
	private static Map<String,JdbcType> codeLookup = new HashMap<String,JdbcType>();

	static {
		for (JdbcType type : JdbcType.values()) {
			codeLookup.put(type.name(), type);
		}
	}

	JdbcType(String javaType) {
		this.javaType = javaType;
	}

	public static JdbcType forCode(String type)  {
		return codeLookup.get(type);
	}
	public static String forJavaType(String type)  {
		if(type==null||type.length()<1)return null;
		JdbcType e=codeLookup.get(type.toUpperCase());
		return e==null?null:e.javaType;
	}
}
