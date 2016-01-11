package com.github.xy8864.webGenerator.core;

import java.util.HashMap;
import java.util.Map;

/**
 * ARRAY			Array
 * BIGINT			long
 * BINARY			byte[]
 * BIT				boolean
 * BLOB				Blob
 * BOOLEAN			boolean
 * CHAR				String
 * CLOB				Clob
 * DATALINK			java.net.URL
 * DATE				java.sql.Date
 * DECIMAL			java.math.BigDecimal
 * DISTINCT			mapping of underlying type
 * DOUBLE			double
 * FLOAT			double
 * INTEGER			int
 * JDBC Type			Java Type
 * LONGVARBINARY		byte[]
 * LONGVARCHAR			String
 * NUMERIC			java.math.BigDecimal
 * REAL				float
 * REF	         Ref
 * SMALLINT			short
 * STRUCT			Struct
 * TIME				java.sql.Time
 * TIMESTAMP			java.sql.Timestamp
 * TINYINT			byte
 * VARBINARY			byte[]
 * VARCHAR			String
 */
public enum JdbcType{
	ARRAY("ARRAY","Array"),
	BIGINT("BIGINT","Long"),
	BINARY("BINARY","byte[]"),
	BIT("BOOLEAN","Boolean"),
	BLOB("BLOB","byte[]"),
	BOOLEAN("BOOLEAN","Boolean"),
	CHAR("CHAR","Char"),
	CLOB("CLOB","char[]"),
	CURSOR(null,null), // Oracle
	DATE("TIMESTAMP","java.util.Date"),
	DATETIME("TIMESTAMP","java.util.Date"),
	DECIMAL("DECIMAL","java.math.BigDecimal"),
	DOUBLE("DOUBLE","Double"),
	FLOAT("FLOAT","Float"),
	INT("INTEGER","Integer"),
	INTEGER("INTEGER","Integer"),
	LONGTEXT("VARCHAR","String"),
	LONGVARBINARY("LONGVARBINARY","byte[]"),
	LONGVARCHAR("LONGVARCHAR","String"),
	NCHAR("NCHAR","char[]"), // JDK6
	NCLOB("NCLOB","char[]"), // JDK6
	NULL(null,null),
	NUMERIC("NUMERIC","java.math.BigDecimal"),
	NVARCHAR("NVARCHAR","String"), // JDK6
	OTHER(null,null),
	SMALLINT("INTEGER","Integer"),
	STRUCT(null,null),
	TEXT("VARCHAR","String"),
	TIME("TIMESTAMP","java.util.Date"),
	TIMESTAMP("TIMESTAMP","java.util.Date"),
	TINYINT("INTEGER","Integer"),
	UNDEFINED(null,null),
	VARBINARY("VARBINARY","byte[]"),
	VARCHAR("VARCHAR","String"),
	VARCHAR2("VARCHAR2","String");

	final String jdbcType;
	final String javaType;

	private static Map<String,JdbcType> codeLookup = new HashMap<String,JdbcType>();

	static {
		for (JdbcType type : JdbcType.values()) {
			codeLookup.put(type.name(), type);
		}
	}

	JdbcType(String jdbcType,String javaType) {
		this.javaType = javaType;
		this.jdbcType = jdbcType;
	}

	public String getJavaType(){
		return javaType;
	}

	public String getJdbcType(){
		return jdbcType;
	}

	public static JdbcType forCode(String dataType)  {
		if(dataType==null||dataType.length()<1)return null;
		return codeLookup.get(dataType);
	}

	@Override
	public String toString(){
		return String.format("%s:%s",name(),jdbcType);
	}

	/**
	 * @param dataType 数据库的类型,大部分和jdbcType一样,一些不一样
	 * @return
	 */
	public static String forJavaType(String dataType)  {
		if(dataType==null||dataType.length()<1)return null;
		JdbcType e=codeLookup.get(dataType.toUpperCase());
		return e==null?null:e.javaType;
	}

	public static void main(String[] args){
		System.out.println(forJavaType("INT"));
	}
}
