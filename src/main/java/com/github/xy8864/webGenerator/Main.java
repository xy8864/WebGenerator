package com.github.xy8864.webGenerator;

import com.github.xy8864.webGenerator.marker.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main{
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args){
		try{
			new Generator().build();
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}

		//ClassPathXmlApplicationContext app=new ClassPathXmlApplicationContext("classpath:webGenerator.xml");
		//app.getBean(Generator.class).build();
		//AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
		//需找类型为LogonService,名字为logonService1的bean,如果没有指定名字，默认寻找匹配的类型.

	}
}
