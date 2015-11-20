package ${package};

<#assign ClassName=StringUtil.firstToUpper(domainName)>
import ${domainPackage}.${ClassName};
import com.github.xy8864.webGenerator.base.MybatisBaseMapper;

import org.springframework.stereotype.Repository;

<#include "copyright.ftl"/>
@Repository
public interface ${ClassName}Mapper extends MybatisBaseMapper<${ClassName}> {

}
