package com.github.xy8864.webGenerator.base;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.18 0018 10:05
 * To change this template use File | Settings | File Templates.
 */
public interface MybatisBaseMapper<T>{
	long create(Map<String,Object> parameter);

	T get(Long id);

	int update(Map<String,Object> parameter);

	void toggle(Long id);



	int delete(Long id);

	List<T> list(Map<String,Object> filter);

	Long count(Map<String,Object> filter);
}