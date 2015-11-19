package com.github.xy8864.webGenerator.base;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015-05-19 10:07
 * To change this template use File | Settings | File Templates.
 */
public class Parameter extends LinkedHashMap<String,Object>{
	private static final long serialVersionUID=-801254691324609161L;

	public Parameter(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public Parameter(int initialCapacity) {
		super(initialCapacity);
	}

	public Parameter() {}

	public Parameter(Map<String, ?> m) {
		super(m);
	}

	public Parameter setParameter(String key, Object value) {
		put(key, value);
		return this;
	}
	public Parameter add(String key, Object value) {
		put(key, value);
		return this;
	}
	public <T> T get(String key,T defaultValue){
		T t=(T)get(key);
		return t==null?defaultValue:t;
	}
	public String getString(String key,String defaultValue){
		String val=(String)get(key);
		return val==null?defaultValue:val;
	}
	public int getInt(String key,int defaultValue){
		return NumberUtils.toInt(String.valueOf(get(key)) ,defaultValue);
	}
	public long getLong(String key,long defaultValue){
		return NumberUtils.toLong(String.valueOf(get(key)), defaultValue);
	}

	/**
	 *
	 * getBoolean(any,null)=false
	 */
	public boolean getBoolean(String key,String target){
		String val=(String) get(key);
		return target!=null && target.equals(val);

	}
}
