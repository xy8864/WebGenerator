package ${package};

<#assign ClassName=StringUtil.firstToUpper(className)>
import java.io.Serializable;

public class ${ClassName} implements Serializable {
	<#list fieldList as field>
	<#if (field.comment)??>/** ${field.comment} */</#if>
	private ${field.javaType} ${field.name};
	</#list>

	<#list fieldList as field>
	<#assign upperFieldName = StringUtil.firstToUpper(field.name)>
	<#if (field.comment)??>/** ${field.comment} */</#if>
	public ${field.javaType} get${upperFieldName}() {
		return ${field.name};
	}
	<#if (field.comment)??>/** ${field.comment} */</#if>
	public void set${upperFieldName}(${field.javaType} ${field.name}) {
		this.${field.name} = ${field.name};
	}

	</#list>
}
