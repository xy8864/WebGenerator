package com.github.xy8864.webGenerator.marker;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.github.xy8864.webGenerator.core.Config;
import com.github.xy8864.webGenerator.core.GeneratorException;
import com.github.xy8864.webGenerator.marker.mysql.MysqlReader;
import com.github.xy8864.webGenerator.util.CheckUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class Generator{
	private static final Logger log = LoggerFactory.getLogger(Generator.class);

	Connection connection;
	//@Autowired JdbcTemplate jdbcTemplate;


	public void build(){
		Config config;
		try{
			config=readConfig();
		}catch(Exception e){
			log.error("读取配置文件失败", e);
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

		List<Element> list=root.element("config").elements("property");
		if(CheckUtil.isEmpty(list)){
			throw new GeneratorException("没有找到配置");
		}
		Config config=new Config();
		for(Element e:list){
			config.addDbConfig(e.attributeValue("name"), e.attributeValue("value"));
		}


		List<Element> tables=root.element("tables").elements("table");
		if(CheckUtil.isEmpty(tables)){
			throw new GeneratorException("没有找到table");
		}
		for(Element e:tables){
			config.addTable(e.attributeValue("table"), e.attributeValue("domain"));
		}

		config.setDbType(config.getConfig("jdbc.type"));
		config.setSchema(config.getConfig("jdbc.schema"));
		config.setBasePath(config.getConfig("basePath"));
		config.setTemplateDir(config.getConfig("templateDir"));
		config.setDomain(config.getConfig("domain"));
		config.setJavaMapper(config.getConfig("javaMapper"));
		return config;
	}


	void buildCode(Config config){
		//DatabaseInfoReader infoReader=new MysqlReader();
		new MysqlReader().read(config);

		new DefaultCodeMarker(config).build();

	}
}
