package com.github.xy8864.webGenerator.base;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.18 0018 12:02
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseServiceImpl<T>implements BaseService<T>{

	public abstract MybatisBaseMapper<T> getMapper();

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = Exception.class)
	public long create(Map<String, Object> parameter){
		if(parameter==null||parameter.isEmpty())return 0L;
		try{
			getMapper().create(parameter);
		}catch(Exception e){
			throw new ServiceException(e);
		}
		//<insert id="insert" useGeneratedKeys="true" keyProperty="id">    要这么写
		//return parameter.get("id");
		return 0L;
	}

	public T get(Long id){
		if(id==null||id<1)return null;
		return getMapper().get(id);
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = Exception.class)
	public int update(Map<String, Object> parameter){
		try{
			return getMapper().update(parameter);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = Exception.class)
	public void update(int updateCount,Map<String, Object> params){
		if(params==null||params.isEmpty())return;
		int count=0;
		try{
			count=getMapper().update(params);
		}catch(Exception e){
			throw new ServiceException(e);
		}
		if(count!=updateCount){
			throw new ServiceException(String.format("更新[%s]的数量[%s]!=%s",getMapper().getClass().getSimpleName(),count,updateCount));
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = Exception.class)
	public void toggle(Long id){
		if(id==null||id<1)return;
		getMapper().toggle(id);
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(Long id){
		if(id==null||id<1)return;
		try{
			getMapper().delete(id);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}

	@Override
	public List<T> list(Map<String, Object> filter){
		return getMapper().list(filter);
	}

	@Override
	public Long count(Map<String, Object> filter){
		return getMapper().count(filter);
	}

	public void page(Page<T> page){
		if(page==null){
			page=new Page<T>();
			page.add("pageable",0);//<when test="pageable==0"></when>
		}else{
			Map<String, Object> params=page.buildFilter();

			if( !page.isCalcTotalCount() || page.getTotalCount()==null||page.getTotalCount()<1){
				Long total=getMapper().count(params);
				if(total==null||total<1){
					page.setTotalCount(0L);
					page.setList(Collections.<T>emptyList());
					return;
				}
				page.setTotalCount(total);
			}


			page.setList( getMapper().list(params));
			if(page.getList()==null){
				page.setList(Collections.<T>emptyList());
			}
		}
	}
}
