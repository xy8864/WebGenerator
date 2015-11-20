package ${package};

<#assign ClassName=StringUtil.firstToUpper(domainName)>
<#assign className=StringUtil.firstToLower(domainName)>
import ${domainPackage}.${ClassName};
import com.github.xy8864.webGenerator.base.BaseService;

<#include "copyright.ftl"/>
public interface ${ClassName}Service extends BaseService<${ClassName}> {

}