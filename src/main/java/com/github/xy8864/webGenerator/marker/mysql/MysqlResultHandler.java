package com.github.xy8864.webGenerator.marker.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.xy8864.webGenerator.core.Column;
import com.github.xy8864.webGenerator.core.GeneratorException;
import com.github.xy8864.webGenerator.core.JdbcType;
import com.github.xy8864.webGenerator.core.Table;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.17 0017 10:18
 * To change this template use File | Settings | File Templates.
 */
public class MysqlResultHandler implements ResultSetHandler{
	Table table;
	public MysqlResultHandler(Table table){
		this.table=table;
	}

	@Override
	public Object handle(ResultSet rs) throws SQLException{
		while(rs.next()){
			Column column=new Column();
			column.setName(rs.getString("COLUMN_NAME"));
			column.setComment(StringUtils.defaultString(rs.getString("COLUMN_COMMENT"),null));
			String dataType=rs.getString("DATA_TYPE");
			JdbcType jdbcType=JdbcType.forCode(dataType);
			if(jdbcType==null){
				throw new GeneratorException(String.format("表[%s]的字段[%s:%s]没有找到映射的JdbcType",table,column.getName(),dataType));
			}
			column.setJdbcType(jdbcType.getJdbcType());
			column.setJavaType(jdbcType.getJavaType());
			if(StringUtils.isEmpty(column.getJavaType())){
				throw new GeneratorException(String.format("表[%s]的字段[%s:%s]没有找到映射的javaType",table,column.getName(),column.getJdbcType()));
			}

			if("1".equals(rs.getString("isPri"))){
				column.setPk(true);
				table.setPk(column);
			}
			table.addColumn(column);
		}

		return null;
	}
}
