package com.github.xy8864.webGenerator.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import com.google.common.collect.Lists;
import org.apache.commons.lang.ArrayUtils;
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

	public static <K, V> V mapValue(Map<K, V> map, K key, V defaultValue){
		if(isEmpty(map)) return defaultValue;
		V val=map.get(key);
		return val==null ? defaultValue : val;
	}

	public static int mapIntValue(Map<String, Object> map, String key, int defaultValue){
		if(isEmpty(map)) return defaultValue;
		Integer val=(Integer) map.get(key);
		return val==null ? defaultValue : val;
	}

	public static long mapLongValue(Map<String, Object> map, String key, long defaultValue){
		if(isEmpty(map)) return defaultValue;
		Long val=(Long) map.get(key);
		return val==null ? defaultValue : val;
	}

	public static double mapDoubleValue(Map<String, Object> map, String key, double defaultValue){
		if(isEmpty(map)) return defaultValue;
		Double val=(Double) map.get(key);
		return val==null ? defaultValue : val;
	}

	public static boolean mapBooleanValue(Map<String, Object> map, String key, boolean defaultValue){
		if(isEmpty(map)) return defaultValue;
		Boolean val=(Boolean) map.get(key);
		return val==null ? defaultValue : val;
	}

	/** 生出新的map */
	public static Map<String, Object> newMap(Object... keyValues){
		if(keyValues==null || keyValues.length<1 || keyValues.length%2!=0){
			throw new IllegalArgumentException("参数个数必须是2的倍数!");
		}
		int length=keyValues.length/2;
		Map<String, Object> map=new HashMap<String, Object>(length);
		for(int i=0; i<length; i++){
			map.put((String) keyValues[i*2], keyValues[i*2+1]);
		}
		return map;
	}

	public static <T> Set<T> newHashSet(T... values){
		if(values==null || values.length<1){
			return null;
		}
		Set<T> set=new HashSet<T>(values.length);
		for(T t : values){
			if(t!=null) set.add(t);
		}
		return set;
	}

	/** 数组args中加一个add */
	public static Object[] addArray(Object[] args, Object add){
		if(args==null) return new Object[]{add};
		Object[] no=new Object[args.length+1];
		//arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		//src:源数组； srcPos:源数组要复制的起始位置； dest:目的数组； destPos:目的数组放置的起始位置； length:复制的长度
		System.arraycopy(args, 0, no, 0, args.length);
		no[args.length+1]=add;
		return no;
	}

	public static <T> void addAll(Collection<T> target, Collection<T> add){
		if(target==null){
			throw new IllegalArgumentException("要添加的集合为null");
		}
		if(isNotEmpty(add)) target.addAll(add);
	}

	public static <T> T defaultIfNull(T object, T defaultValue){
		return object!=null ? object : defaultValue;
	}

	public static Set<Long> splitIds(String idsStr, char separatorChar){
		if(isBlank(idsStr)) return null;
		String[] arr=org.apache.commons.lang.StringUtils.split(idsStr, separatorChar);
		if(isEmpty(arr)) return null;
		Set<Long> result=new HashSet<Long>(arr.length);
		long id;
		for(String idStr : arr){
			id=org.apache.commons.lang.math.NumberUtils.toLong(idStr);
			if(id<1){
				log.debug("有无效或者小于1的id[{}],原数据:{}", idStr, idsStr);
				continue;
			}
			result.add(id);
		}
		if(arr.length!=result.size()){
			log.info("原数据{}:{}和结果{}:[{}]的数目不相同", arr.length, Arrays.toString(arr), result.size(), result);
		}
		return result;
	}

	/** 分割成小集合 */

	public static <T> List<List<T>> split(List<T> list, int limit){
		if(isEmpty(list)) return null;
//		List<List<T>> result=new ArrayList<List<T>>();
//		int size=list.size();
//		if(size>limit){
//			int fromIndex=0, toIndex;
//			//int max=list.size();
//			while(fromIndex<size){
//				toIndex=Math.min( fromIndex+limit ,size) ;
//				result.add(list.subList(fromIndex,toIndex));
//				fromIndex=toIndex;
//			}
//		}else{
//			result.add(list);
//		}

		return Lists.partition(list, limit);
	}

}