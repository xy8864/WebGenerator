package com.github.xy8864.webGenerator.marker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.xy8864.webGenerator.core.Config;
import com.github.xy8864.webGenerator.util.CheckUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

public class Generator{
	private static final Logger log = LoggerFactory.getLogger(Generator.class);
	@Autowired JdbcTemplate jdbcTemplate;


	public void build(){
		Config config=null;
		try{
			config=readConfig();
		}catch(Exception e){
			log.info("读取配置文件失败");
			System.exit(0);
			return;
		}

		buildCode(config);
	}

	Config readConfig() throws Exception{
		File file=ResourceUtils.getFile("classpath:tableConfigTemplate.xml");

		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);

		Element root = doc.getRootElement();

		List<Element> list=root.element("config").element("property").elements();

		Map<String,String> config=new HashMap<String, String>();
		for(Element e:list){
			config.put(e.attributeValue("name"),e.attributeValue("value"));
		}


		List<Element> tables=root.element("tables").element("table").elements();
		if(CheckUtil.isEmpty(tables)){
			return null;
		}
		Map<String,String> tableMap=new HashMap<String, String>();
		for(Element e:list){
			config.put(e.attributeValue("table"),e.attributeValue("domain"));
		}

		return new Config();
	}

	public void connectDb(){
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@<database>");
		basicDataSource.setUsername("user");
		basicDataSource.setPassword("password");
		try{
			Connection connection=basicDataSource.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}



	void buildCode(Config config){

	}
}
