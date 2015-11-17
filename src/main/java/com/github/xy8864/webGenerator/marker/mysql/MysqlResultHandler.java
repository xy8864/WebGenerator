package com.github.xy8864.webGenerator.marker.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.xy8864.webGenerator.core.Column;
import com.github.xy8864.webGenerator.core.Table;
import org.apache.commons.dbutils.ResultSetHandler;

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
		Column column=new Column();
		column.setName(rs.getString("COLUMN_NAME"));
		column.setType(rs.getString("DATA_TYPE"));
		column.setComment(rs.getString("COLUMN_COMMENT"));
		column.setPk("1".equals(rs.getString("isPri")));
		table.addColumn(column);
		return null;
	}
}
