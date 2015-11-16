package com.github.xy8864.webGenerator.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015/7/15 9:14
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@PropertySource("classpath:appConfig.properties")
public class PropertySourcesConfig{
	/**
	 * 对于纯java的配置方案,
	 * 你还需要在SpringConfig类中设置接下来的bean, 否则这些不会被@Value注解正确的
	 * 这个类必须有，而且必须声明为static，否则不能正常解析
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
}
