package com.github.xy8864.webGenerator;

import com.github.xy8864.webGenerator.marker.Generator;

public class Main{
	public static void main(String[] args){
		new Generator().build();
		//ClassPathXmlApplicationContext app=new ClassPathXmlApplicationContext("classpath:webGenerator.xml");
		//app.getBean(Generator.class).build();
		//AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
		//需找类型为LogonService,名字为logonService1的bean,如果没有指定名字，默认寻找匹配的类型.

	}
}
