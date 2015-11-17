package com.github.xy8864.webGenerator.spring.config;



		import javax.annotation.Resource;
		import javax.sql.DataSource;

		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.ComponentScan;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.jdbc.core.JdbcTemplate;
		import org.springframework.jdbc.datasource.DataSourceTransactionManager;
		import org.springframework.transaction.PlatformTransactionManager;
		import org.springframework.transaction.annotation.EnableTransactionManagement;
		import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * <mvc:annotation-driven/>
 * 相当于注册了DefaultAnnotationHandlerMapping和AnnotationMethodHandlerAdapter两个bean，
 * 配置一些messageconverter,即解决了@Controller注解的使用前提配置。
 */

@Configuration
@EnableTransactionManagement
//@ComponentScan("yuan.example.spring3.service")
//@PropertySource("classpath:appConfig.properties")
//@Import(PropertySourcesConfig.class)
public class DaoConfig implements TransactionManagementConfigurer{
	private static final Logger log = LoggerFactory.getLogger(DaoConfig.class);
	@Autowired DataSource dataSource;

	@Bean@Resource(name="dataSource")
	public JdbcTemplate JdbcTemplate(DataSource dataSource)throws Exception{
		return new JdbcTemplate(dataSource);
	}
	/**
	 * 	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	 * 	 <property name="dataSource" ref="dataSource" />
	 * 	  </bean>
	 */
	@Bean//@Resource(name="dataSource")
	public PlatformTransactionManager txManager() throws Exception{
		return new DataSourceTransactionManager(dataSource);
	}

	public PlatformTransactionManager annotationDrivenTransactionManager() {
		try{
			return txManager();
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		return null;
	}

}

