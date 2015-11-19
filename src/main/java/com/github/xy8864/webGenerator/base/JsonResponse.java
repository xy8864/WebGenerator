package com.github.xy8864.webGenerator.base;

import java.io.Serializable;
import java.util.List;


/**
 * {"data": {"message": "success","entity": {}},"code": 200}
 */
public class JsonResponse implements Serializable{
	private static final long serialVersionUID=-7516840350611563530L;

	public final static int ok=200;
	public final static int fail=201;
	/** 约定的未登录状态码 */
	public final static int notLogin=401;
	/** 约定的没权限 */
	public final static int forbidden=403;

	public final static String STRING_OK=code(ok,"操作成功!");
	public final static String STRING_FAIL=code(fail,"操作失败!");
	public final static String STRING_FORBIDDEN=code(forbidden,"禁止访问!");
	public final static String STRING_NOT_LOGIN=code(notLogin,"没有登录!");
	public final static JsonResponse Response_OK=new JsonResponse().ok();
	public final static JsonResponse Response_FAIL=new JsonResponse().fail();
	public final static JsonResponse RESPONSE_FORBIDDEN=new JsonResponse().forbidden();

	private Integer code;
	private Parameter data;

	public Integer getCode(){
		return code;
	}

	public void setCode(Integer code){
		this.code=code;
	}

	public Parameter getData(){
		return data;
	}

	public void setData(Parameter data){
		this.data=data;
	}
	public void setData(Page page){
		if(page==null)return;
		put("totalCount",page.getTotalCount());
		put("pageNo",page.getPageNo());
		put("pageSize",page.getPageSize());
		put("list", page.getList());
	}

	public JsonResponse put(String key, Object value){
		if(data==null) data=new Parameter();
		data.put(key, value);
		return this;
	}

	public JsonResponse message(String message){
		if(data==null) data=new Parameter();
		data.put("message", message);
		return this;
	}
	public JsonResponse entity(Object entity){
		if(data==null) data=new Parameter();
		data.put("entity", entity);
		return this;
	}
	public <T> JsonResponse list(List<T> list){
		if(data==null) data=new Parameter();
		data.put("list", list);
		return this;
	}

	public Object get(String key,Object dv){
		if(data==null|| data.isEmpty())return dv;
		return data.get(key);
	}
	public Object get(String key){
		return get(key, null);
	}

	public JsonResponse code(int code){
		this.code= code;
		return this;
	}

	public JsonResponse notLogin(){
		return code(notLogin);
	}
	public JsonResponse forbidden(){
		return code(forbidden);
	}
	public JsonResponse ok(){
		return code(ok);
	}
	public JsonResponse fail(){
		return code(fail);
	}

	public static String code(int code,String message){
		return String.format("{\"code\":%s,\"data\":{\"message\":\"%s\"}}", code, message);
	}

	public static String notLogin(String message){
		return code(notLogin,message);
	}
	public static String forbidden(String message){
		return code(forbidden, message);
	}
	public static String ok(String message){
		return code(ok,message);
	}
	public static String fail(String message){
		return code(fail,message);
	}

}
