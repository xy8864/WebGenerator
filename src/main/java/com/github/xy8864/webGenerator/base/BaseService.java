package com.github.xy8864.webGenerator.base;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.18 0018 11:49
 * To change this template use File | Settings | File Templates.
 */
public interface BaseService<T>{
	/** 返回id */
	long create(Map<String,Object> params);

	T get(Long id);

	public void update(int updateCount,Map<String, Object> params);

	void toggle(Long id);

	void delete(Long id);

	List<T> list(Map<String,Object> filter);

	Long count(Map<String,Object> filter);

	public void page(Page<T> page);
}
