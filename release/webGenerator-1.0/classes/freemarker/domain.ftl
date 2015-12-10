package ${package};

<#assign ClassName=StringUtil.firstToUpper(domainName)>
import java.io.Serializable;

<#include "copyright.ftl"/>
public class ${ClassName} implements Serializable {
	<#list fieldList as field>
	<#if StringUtils.isNotBlank(field.comment)>/** ${field.comment} */</#if>
	private ${field.javaType} ${field.name};
	</#list>

	<#list fieldList as field>
	<#assign upperFieldName = StringUtil.firstToUpper(field.name)>
	<#if StringUtils.isNotBlank(field.comment)>/** ${field.comment} */</#if>
	public ${field.javaType} get${upperFieldName}() {
		return ${field.name};
	}
	<#if StringUtils.isNotBlank(field.comment)>/** ${field.comment} */</#if>
	public void set${upperFieldName}(${field.javaType} ${field.name}) {
		this.${field.name} = ${field.name};
	}

	</#list>
}
