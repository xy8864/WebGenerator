package com.github.xy8864.webGenerator.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015-05-19 10:07
 * To change this template use File | Settings | File Templates.
 */
public class Page<T> implements Serializable{
	private static final long serialVersionUID=8972820382795778996L;
//"pageNo": 1, "pageSize": 20, "totalCount": 100
	private Long pageNo;
	private Long pageSize=20L;
	private Long totalCount;
	/** 是否需要查总数 */
	private boolean calcTotalCount=true;
	/** 是否需要分页 */
	private boolean pageable=true;
	private Collection<T> list;
	Map<String, Object> filter;

	public Long getTotalCount(){
		return totalCount;
	}

	public void setTotalCount(Long totalCount){
		this.totalCount=totalCount;
	}

	public Long getPageNo(){
		return pageNo;
	}

	public void setPageNo(Long pageNo){
		this.pageNo=pageNo;
	}

	public Long getPageSize(){
		return pageSize;
	}

	public void setPageSize(Long pageSize){
		this.pageSize=pageSize;
	}

	public Collection<T> getList(){
		return list;
	}

	public void setList(Collection<T> list){
		this.list=list;
	}

	public boolean isCalcTotalCount(){
		return calcTotalCount;
	}

	/** 是否需要查总数 */
	public void setCalcTotalCount(boolean calcTotalCount){
		this.calcTotalCount=calcTotalCount;
	}

	public boolean isPageable(){
		return pageable;
	}

	public void setPageable(boolean pageable){
		this.pageable=pageable;
	}

	public long getPageOffset() {
		if(pageNo==null||pageSize==null)return 0;
		if (pageNo < 1) {
			pageNo = 1L;
		}
		if (pageSize < 1) {
			pageSize = 20L;
		}
		return (pageNo - 1) * pageSize;
	}

	public Map<String, Object> getFilter(){
		return filter;
	}

	public Map<String, Object> buildFilter(){
		add("offset",getPageOffset());
		add("pageSize",pageSize);
		if(!pageable){
			add("pageable",0);
		}

		return filter;
	}

	public void setFilter(Map<String, Object> filter){
		this.filter=filter;
	}
	public Map<String, Object> add(String key, Object value) {
		if(filter==null){
			filter=new HashMap<java.lang.String, java.lang.Object>();
		}
		filter.put(key, value);
		return filter;
	}
	public Map<String, Object> add(String key, Object value,boolean isAdd) {
		if(filter==null){
			filter=new HashMap<String, Object>();
		}
		if(isAdd)return filter;

		filter.put(key, value);
		return filter;
	}
	public Object get(String key){
		if(filter==null){
			return null;
		}

		return filter.get(key);
	}
	public String getString(String key,String defaultValue){
		String val=(String)get(key);
		return val==null?defaultValue:val;
	}
	public int getInt(String key,int defaultValue){
		return NumberUtils.toInt(String.valueOf(get(key)), defaultValue);
	}
	public long getLong(String key,long defaultValue){
		return NumberUtils.toLong(String.valueOf(get(key)), defaultValue);
	}
	@Override
	public String toString(){
		final StringBuilder sb=new StringBuilder("Page{");
		sb.append("totalCount=").append(totalCount);
		sb.append(", pageNo=").append(pageNo);
		sb.append(", pageSize=").append(pageSize);
		sb.append('}');
		return sb.toString();
	}
}
