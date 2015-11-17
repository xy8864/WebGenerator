package com.github.xy8864.webGenerator.code;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 17:31
 * To change this template use File | Settings | File Templates.
 */
public interface CodeMarker {
	public void setTemplate(JdbcTemplate template);

	public void init();

	void genDomain();
	void genJavaMapper();
	void genXmlMapper();
	void genService();
	void genController();


}
