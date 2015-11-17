package com.github.xy8864.webGenerator.spring;

import com.github.xy8864.webGenerator.spring.config.DaoConfig;
import com.github.xy8864.webGenerator.spring.config.DataSourceConfig;
import com.github.xy8864.webGenerator.spring.config.PropertySourcesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <mvc:annotation-driven/>
 * 相当于注册了DefaultAnnotationHandlerMapping和AnnotationMethodHandlerAdapter两个bean�?
 * 配置�?��messageconverter,即解决了@Controller注解的使用前提配置�?
 *
 * spring.xml
 * <beans>
 *    <!-- Spring configuration -->
 *    <bean class="org.springframework.samples.petclinic.JdbcConfiguration"/>
 *    <!-- Java Configuration post processor -->
 *    <bean class="org.springframework.config.java.process.ConfigurationPostProcessor"/>
 * </beans>
 *
 * 或�?web.xml
 * 	<context-param>
 * 	  <param-name>contextClass</param-name>
 * 	   <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
 * 	  </context-param>
 * 	  <context-param>
 * 	   <param-name>contextConfigLocation</param-name>
 * 	   <param-value>yuan.example.spring3.mvc.ApplicationInitializer</param-value>
 * 	  </context-param>
 */

@Configuration
//@EnableWebMvc // <mvc:annotation-driven/>
//@EnableTransactionManagement
//@ComponentScan("yuan.example.spring3.mvc")
//@PropertySource("classpath:appConfig.properties")
@Import({PropertySourcesConfig.class, DataSourceConfig.class, DaoConfig.class})
//@ImportResource("WEB-INF/config/webInterface.xml")
public class ApplicationInitializer{
	private static final Logger log = LoggerFactory.getLogger(ApplicationInitializer.class);

	@Value("${redis.hostName}")String redisHost;



	public static void main(String[] args){
		new AnnotationConfigApplicationContext(ApplicationInitializer.class).registerShutdownHook();
	}


}
