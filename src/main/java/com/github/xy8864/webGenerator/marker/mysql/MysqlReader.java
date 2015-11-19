package com.github.xy8864.webGenerator.marker.mysql;

import java.sql.SQLException;

import com.github.xy8864.webGenerator.core.Config;
import com.github.xy8864.webGenerator.core.GeneratorException;
import com.github.xy8864.webGenerator.core.Table;
import com.github.xy8864.webGenerator.marker.DatabaseInfoReader;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 17:38
 * To change this template use File | Settings | File Templates.
 */
public class MysqlReader implements DatabaseInfoReader{
	private static final Logger log = LoggerFactory.getLogger(MysqlReader.class);

	MysqlDataSource dataSource;


	@Override
	public void read(Config config){
		init(config);

		readInfo(config);
	}

	void init(Config config){
		dataSource=new MysqlDataSource();

		dataSource.setUrl(config.getConfig("jdbc.url"));
		dataSource.setUser(config.getConfig("jdbc.username"));
		dataSource.setPassword(config.getConfig("jdbc.password"));
	}


	void readInfo(Config config){
		QueryRunner runner=new QueryRunner(dataSource);

		String sql = "SELECT COLUMN_NAME,upper(DATA_TYPE) DATA_TYPE,COLUMN_COMMENT,IF(COLUMN_KEY='PRI',1,0) isPri" +
				" FROM INFORMATION_SCHEMA.COLUMNS where INFORMATION_SCHEMA.COLUMNS.TABLE_SCHEMA=? and INFORMATION_SCHEMA.COLUMNS.TABLE_NAME=?";

		try{
			for(Table table:config.getTableMap().values()){
				runner.query(sql, new MysqlResultHandler(table),config.getSchema(),table.getName());
			}
		}catch(SQLException e){
			log.info("查询数据库失败", e);
			throw new GeneratorException("查询数据库失败",e);
		}
	}
}
