package com.github.xy8864.webGenerator.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2014-09-05 11:53
 * To change this template use File | Settings | File Templates.
 */
public class CheckUtil{
	private static final Logger log=LoggerFactory.getLogger(CheckUtil.class);
	public static final int NULL_LENGTH=-1;

	public static boolean isEmpty(Collection collection){
		return (collection==null || collection.isEmpty());
	}

	public static boolean isEmpty(Map map){
		return (map==null || map.isEmpty());
	}

	public static boolean isEmpty(Object[] objects){
		return (objects==null || objects.length<1);
	}

	public static boolean isNotEmpty(Collection collection){
		return !isEmpty(collection);
	}

	public static boolean isNotEmpty(Map map){
		return !isEmpty(map);
	}

	public static boolean isNotEmpty(Object[] objects){
		return !isEmpty(objects);
	}

	public static int length(Collection objects){
		return objects==null ? NULL_LENGTH : objects.size();
	}

	public static int length(Map objects){
		return objects==null ? NULL_LENGTH : objects.size();
	}

	public static int length(Object[] objects){
		return objects==null ? NULL_LENGTH : objects.length;
	}

	public static <T> T getIndexOf(List<T> list, int index){
		if(isEmpty(list)) return null;
		if(list.size()>=index) return null;
		return list.get(index);
	}

	public static <T> T getIndexOf(T[] list, int index){
		if(isEmpty(list)) return null;
		if(list.length>=index) return null;
		return list[index];
	}

	public static boolean eq(Integer num, int in){
		return !(num==null) && num==in;
	}

	public static boolean eq(Long num, long in){
		return !(num==null) && num==in;
	}

	public static boolean in(Integer num, int... ins){
		return !(num==null || ins==null || ins.length<1) && ArrayUtils.contains(ins, num);
	}

	public static boolean in(Long num, long... ins){
		return !(num==null || ins==null || ins.length<1) && ArrayUtils.contains(ins, num);
	}

	/** 返回 id!=null&&id>0; */
	public static boolean notNull(Long id){
		return id!=null && id>0;
	}

	/** 返回 id!=null&&id>0; */
	public static boolean notNull(Integer id){
		return id!=null && id>0;
	}

	/** 判断num==Enum.ordinal() */
	public static boolean in(Integer num, Enum... ins){
		if(num==null || ins==null || ins.length<1){
			return false;
		}
		for(Enum e : ins){
			if(e!=null && e.ordinal()==num) return true;
		}
		return false;
	}

	public static boolean in(String str, String... tars){
		if(isBlank(str) || tars.length<1){
			return false;
		}
		for(String tar : tars){
			if(str.equals(tar)) return true;
		}
		return false;
	}

	public static boolean contains(String str, String... tars){
		if(isBlank(str) || tars.length<1){
			return false;
		}
		for(String tar : tars){
			if(isNotBlank(tar) && str.contains(tar)) return true;
		}
		return false;
	}





}