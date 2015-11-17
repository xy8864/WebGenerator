package com.github.xy8864.webGenerator.code.mysql;

import com.github.xy8864.webGenerator.code.CodeMarker;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 17:38
 * To change this template use File | Settings | File Templates.
 */
public class MysqlCodeMarker implements CodeMarker{
	JdbcTemplate template;


	public void setTemplate(JdbcTemplate template){
		this.template=template;
	}

	@Override
	public void init(){

	}

	@Override
	public void genDomain(){

	}

	@Override
	public void genJavaMapper(){

	}

	@Override
	public void genXmlMapper(){

	}

	@Override
	public void genService(){

	}

	@Override
	public void genController(){

	}

	@Override
	public void build(){

	}
}
