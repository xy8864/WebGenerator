package com.github.xy8864.webGenerator.marker.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.xy8864.webGenerator.marker.DatabaseInfoReader;
import com.github.xy8864.webGenerator.core.Config;
import com.github.xy8864.webGenerator.core.GeneratorException;
import com.github.xy8864.webGenerator.core.Table;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.DbUtils;
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

	Connection conn;


	@Override
	public void read(Config config){
		openConnect(config);

		readInfo(config);
	}

	void openConnect(Config config){


		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(config.getConfig("jdbc.driver"));
		basicDataSource.setUrl(config.getConfig("jdbc.url"));
		basicDataSource.setUsername(config.getConfig("jdbc.username"));
		basicDataSource.setPassword(config.getConfig("jdbc.password"));
		try{
			conn=basicDataSource.getConnection();
		}catch(SQLException e){
			log.info("连接数据库[{}]失败",config.getConfig("jdbc.url"),e);
			throw new GeneratorException("连接数据库失败",e);
		}
	}


	void readInfo(Config config){
		QueryRunner runner=new QueryRunner();

		String sql = "SELECT COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT,IF(COLUMN_KEY='PRI',1,0) isPri" +
				" FROM INFORMATION_SCHEMA.COLUMNS where INFORMATION_SCHEMA.COLUMNS.TABLE_SCHEMA= '?' and INFORMATION_SCHEMA.COLUMNS.TABLE_NAME='?'";

		try{
			for(Table table:config.getTableMap().values()){
				runner.query(conn, sql, new MysqlResultHandler(table),config.getSchema(),table.getName());
			}
		}catch(SQLException e){
			log.info("查询数据库失败", e);
			throw new GeneratorException("查询数据库失败",e);
		}finally {
			try{
				DbUtils.close(conn);
			}catch(SQLException e){
				log.info("关闭数据库连接失败",e);
			}
		}
	}
}
