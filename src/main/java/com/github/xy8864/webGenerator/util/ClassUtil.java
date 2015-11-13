package com.github.xy8864.webGenerator.util;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 15:31
 * To change this template use File | Settings | File Templates.
 */
public class ClassUtil{
	// 获取类路径
	public static String getClassPath() {
		String classpath = "";
		URL resource = Thread.currentThread().getContextClassLoader().getResource("");
		if (resource != null) {
			classpath = resource.getPath();
		}
		return classpath;
	}
}
