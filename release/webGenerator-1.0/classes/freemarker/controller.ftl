package ${package};

<#assign ClassName=StringUtil.firstToUpper(domainName)>
<#assign className=StringUtil.firstToLower(domainName)>
import ${domainPackage}.${ClassName};
import ${servicePackage}.${ClassName}Service;
import com.github.xy8864.webGenerator.base.JsonResponse;
import com.github.xy8864.webGenerator.base.Page;
import com.github.xy8864.webGenerator.base.Parameter;
import com.github.xy8864.webGenerator.base.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

<#include "copyright.ftl"/>
@Controller
@RequestMapping("/${className}")
public class ${ClassName}Controller{
	private static final Logger log = LoggerFactory.getLogger(${ClassName}Controller.class);

	@Autowired ${ClassName}Service service;

	@ResponseBody
	@RequestMapping(produces="application/json;charset=UTF-8")
	public String create(HttpServletRequest request){
		try{
			service.create(RequestUtil.toParameter(request));
			return JsonResponse.STRING_OK;
		}catch(Exception e){
			RequestUtil.debugParam(request);
			log.error("create ${className} error",e);
			return JsonResponse.STRING_FAIL;
		}
	}

	@ResponseBody@RequestMapping(value = "/get")
	public JsonResponse get(HttpServletRequest request){
		long id=RequestUtil.get(request, "id", 0L);
		if(id<1)return JsonResponse.Response_FAIL;
		JsonResponse response=new JsonResponse();

		Object obj=service.get(id);
		if(obj==null){
			return response.fail().message("获取失败");
		}else{
			return response.ok().entity(obj);
		}
	}


	@ResponseBody
	@RequestMapping(value = "/update",produces="application/json;charset=UTF-8")
	public String update(HttpServletRequest request){
		long id=ServletRequestUtils.getLongParameter(request, "id", 0L);
		if(id<1){
			return JsonResponse.fail("无效的id:"+id);
		}
		Parameter parameter=RequestUtil.toParameter(request);
		parameter.add("id",id);
		try{
			service.update(1,parameter);
			return JsonResponse.STRING_OK;
		}catch(Exception e){
			RequestUtil.debugParam(request);
			log.error("update ${className} error",e);
			return JsonResponse.STRING_FAIL;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/delete",produces="application/json;charset=UTF-8")
	public String delete(HttpServletRequest request){
		Parameter parameter=RequestUtil.toParameter(request);
		try{
			service.update(1,parameter);
			return JsonResponse.STRING_OK;
		}catch(Exception e){
			RequestUtil.debugParam(request);
			log.error("delete ${className} error",e);
			return JsonResponse.STRING_FAIL;
		}
	}

	@ResponseBody @RequestMapping(value = "/list")
	public JsonResponse list(HttpServletRequest request){
		JsonResponse response=new JsonResponse();

		Page<${ClassName}> page=new Page<${ClassName}>();
		page.setTotalCount(RequestUtil.get(request, "total", 0L));
		page.setPageNo(RequestUtil.get(request, "pageNo", 1L));
		page.setPageSize(RequestUtil.get(request, "pageSize", 20L));// default 20

		page.setFilter(RequestUtil.toParameter(request));

		//page.add("locked", RequestUtil.get(request, "status", 0));
		//page.add("username", RequestUtil.get(request, "name", null));
		try{
			service.page( page);
		}catch(Exception e){
			RequestUtil.debugParam(request);
			log.error("list ${className} error",e);
		}

		page.getFilter().clear();
		response.ok().setData(page);

		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/toggle",produces="application/json;charset=UTF-8")
	public String toggle(HttpServletRequest request){
		long id=RequestUtil.get(request,"id",0L);
		if(id<1)return JsonResponse.fail("id<1");

		try{
			service.toggle( id);
			return JsonResponse.STRING_OK;
		}catch(Exception e){
			log.error("toggle ${className} error",e);
			return JsonResponse.STRING_FAIL;
		}
	}
}