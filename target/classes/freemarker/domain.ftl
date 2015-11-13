package ${package};

<#assign ClassName=StringUtil.firstToUpper(className)>
//import java.util.Date;


public class ${ClassName} implements Serializable {
	private static final long serialVersionUID = 1L;

	<#list fieldList as field>
	<#if (field.comment)??>/** ${field.comment} */</#if>
	private ${field.type} ${field.name};
	</#list>

	<#list fieldList as field>
	<#assign upperFieldName = StringUtil.firstToUpper(field.name)>
	<#if (field.comment)??>/** ${field.comment} */</#if>
	public ${field.type} get${upperFieldName}() {
		return ${field.name};
	}
	<#if (field.comment)??>/** ${field.comment} */</#if>
	public void set${upperFieldName}(${field.type} ${field.name}) {
		this.${field.name} = ${field.name};
	}

	</#list>
}
