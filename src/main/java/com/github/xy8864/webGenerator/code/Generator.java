package com.github.xy8864.webGenerator.code;

import com.github.xy8864.webGenerator.core.Config;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 17:51
 * To change this template use File | Settings | File Templates.
 */
public class Generator{
	CodeMarker marker;

	public void build(){
		Config config=readConfig();

		buildCode(config);
	}

	Config readConfig(){
		return null;
	}
	void buildCode(Config config){

	}


}
