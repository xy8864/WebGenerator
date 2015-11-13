/*****************************************************************
 *仿佛兮若轻云之蔽月，飘飘兮若流风之回雪
 *@author ${classAuthor}
 *@copyright all rights reserved (c) wyyft@163.com
 *****************************************************************/
package ${packageName}.${moduleName}.service${subModuleName};
<#assign ClassName=StringUtil.firstToUpper(className)>
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import ${packageName}.${moduleName}.entity${subModuleName}.${ClassName};
import ${packageName}.${moduleName}.dao${subModuleName}.${ClassName}Dao;



/**
 * ${functionName}Service
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Component
@Transactional(readOnly = true)
public class ${ClassName}Service extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(${ClassName}Service.class);
	
	@Autowired
	private ${ClassName}Dao ${className}Dao;
	
	public ${ClassName} get(Long id) {
		return ${className}Dao.findOne(id);
	}
	
	public Page<${ClassName}> find(Page<${ClassName}> page, ${ClassName} ${className}) {
		DetachedCriteria dc = ${className}Dao.createDetachedCriteria();
		//添加查询条件
		<#list fieldList as field>
        <#assign upperFieldName = StringUtil.firstToUpper(field.name)>
        <#if field.queryPage == 'Y'>
        <#if field.type == 'String'>
        if (StringUtils.isNotEmpty(${className}.get${upperFieldName}())){
			dc.add(Restrictions.like("${field.name}", "%"+${className}.get${upperFieldName}()+"%"));
		}
		<#else>
		if (null != ${className}.get${upperFieldName}()
				&& StringUtils.isNotEmpty(${className}.get${upperFieldName}().toString())) {
			dc.add(Restrictions.like("${field.name}", "%"+${className}.get${upperFieldName}()+"%"));
		}
		</#if>
		</#if>
		</#list>
		dc.add(Restrictions.eq(${ClassName}.DEL_FLAG, ${ClassName}.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return ${className}Dao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(${ClassName} ${className}) {
		${className}Dao.save(${className});
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		${className}Dao.deleteById(id);
	}
	
}
