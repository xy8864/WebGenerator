/*****************************************************************
 *仿佛兮若轻云之蔽月，飘飘兮若流风之回雪
 *@filename ITemplateEngine2.java
 *@author WYY
 *@date 2013年11月26日
 *@copyright (c) 2013  wyyft@163.com All rights reserved.
 *****************************************************************/
package com.github.xy8864.webGenerator.engine;

import java.util.Map;

public interface Engine{
	// 合并模板到文件中
	public void writeToFile(String ftlPath, Map<String, Object> model, String filePath);

	// 合并模板并返回字符串
	public String toString(String ftlPath, Map<String, Object> model);

	public String getTemplateSuffix();

}