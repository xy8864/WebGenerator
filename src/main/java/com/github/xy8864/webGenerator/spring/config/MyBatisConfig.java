//package com.github.xy8864.webGenerator.spring.config;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//
///**
// * Created with IntelliJ IDEA.
// * User: yuanwei
// * Date: 2015/7/15 9:56
// * To change this template use File | Settings | File Templates.
// */
//@Configuration
//@MapperScan(basePackages = { "com.doctor.spring4.common.mapper" },
//		annotationClass=Mapper.class,
//		sqlSessionFactoryRef ="sessionFactoryBean")
//public class MyBatisConfig{
//	//@Resource DataSource dataSource;
//	/**
//	 * 	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
//	 * 	  <property name="dataSource" ref="dataSource" />
//	 * 	  <property name="configLocation" value="classpath:mybatis-config.xml" />
//	 * 	  <property name="mapperLocations" value="classpath*:com/cnlot/appInterface/dao/*.xml" />
//	 * 	  <property name="typeAliasesPackage" value="com.cnlot.domain" />
//	 * 	  <property name="typeHandlersPackage" value="com.cnlot.appInterface.dao.type" />
//	 * 	</bean>
//	 */
//	@Bean(name = "SqlSessionFactory")
//	@Resource(name="dataSource")
//	public SqlSessionFactory sessionFactoryBean(DataSource dataSource,ApplicationContext applicationContext) throws Exception {
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setDataSource(dataSource);
//		bean.setConfigLocation(new ClassPathResource("classpath:mybatis-config.xml"));//加入下面的mybatis-config.xml�?��都不用加
//		//bean.setMapperLocations(applicationContext.getResources("classpath*:com/cnlot/appInterface/dao/*.xml"));
//		bean.setTypeAliasesPackage("com.cnlot.domain");
//		//bean.setTypeHandlersPackage("com.cnlot.appInterface.dao.type");
//		//bean.setTypeHandlersPackage("yuan.example.spring3.config.tool.type");
//
//		return bean.getObject();
//	}
////	@Bean
////	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
////		return new SqlSessionTemplate(sqlSessionFactory);
////	}
//
////	@Bean
////	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
////		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
////		databasePopulator.addScript(new ClassPathResource("db/migration/create_member_table.sql"));
////		databasePopulator.addScript(new ClassPathResource("db/migration/insert_member_table.sql"));
////
////		ResourceDatabasePopulator databaseCleaner = new ResourceDatabasePopulator();
////		databaseCleaner.addScript(new ClassPathResource("db/migration/drop_member_table.sql"));
////		databaseCleaner.setIgnoreFailedDrops(true);
////
////		DataSourceInitializer initializer = new DataSourceInitializer();
////		initializer.setDataSource(dataSource);
////		initializer.setDatabasePopulator(databasePopulator);
////		initializer.setDatabaseCleaner(databaseCleaner);
////
////		return initializer;
////	}
//}
