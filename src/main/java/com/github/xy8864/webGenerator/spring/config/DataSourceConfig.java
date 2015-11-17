package com.github.xy8864.webGenerator.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <mvc:annotation-driven/>
 * 相当于注册了DefaultAnnotationHandlerMapping和AnnotationMethodHandlerAdapter两个bean，
 * 配置一些messageconverter,即解决了@Controller注解的使用前提配置。
 */

@Configuration
//@PropertySource("classpath:appConfig.properties")
//@Import(PropertySourcesConfig.class)
public class DataSourceConfig{
	private static final Logger log=LoggerFactory.getLogger(DataSourceConfig.class);
	@Value("${dataSource.driverClassName}")
	String driverClassName;
	@Value("${dataSource.url}")
	String url;
	@Value("${dataSource.username}")
	String username;
	@Value("${dataSource.password}")
	String password;

	@Bean(name="dataSource", destroyMethod="close")
	public BasicDataSource dataSource() throws Exception{
		log.info("driverClassName:{}", driverClassName);
		log.info("url:{}", url);
		try{
			BasicDataSource dataSource=new BasicDataSource();
			dataSource.setDriverClassName(driverClassName);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setTestWhileIdle(true);
			dataSource.setTestOnBorrow(false);
			dataSource.setTestOnReturn(false);
			dataSource.setValidationQuery("SELECT NOW()");
			dataSource.setValidationQueryTimeout(1);
			dataSource.setTimeBetweenEvictionRunsMillis(30000L);
			dataSource.setNumTestsPerEvictionRun(16);
			dataSource.setInitialSize(2);
			dataSource.setMaxActive(10);
			dataSource.setMaxIdle(8);
			dataSource.setMinIdle(8);
			return dataSource;
		}catch(Exception e){
			log.error("初始化dataSource失败", e);
			System.exit(0);
		}
		return null;
	}

}
