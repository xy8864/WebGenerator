<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${functionName}管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${r"${ctx}"}/${urlPrefix}/">${functionName}列表</a></li>
		<shiro:hasPermission name="${permissionPrefix}:edit"><li><a href="${r"${ctx}"}/${urlPrefix}/form">${functionName}添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="${className}" action="${r"${ctx}"}/${urlPrefix}/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${r"${page.pageNo}"}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${r"${page.pageSize}"}"/>
		<!--添加查询条件-->
		<#list fieldList as field>
        <#assign upperFieldName = StringUtil.firstToUpper(field.name)>
        <#if field.queryPage == 'Y'>
        <label>${field.comment} ：</label><form:input path="${field.name}" htmlEscape="false" maxlength="${field.length}" class="input-medium"/>
		</#if>
		</#list>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${r"${message}"}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<#list fieldList as field>
        <#assign upperFieldName = StringUtil.firstToUpper(field.name)>
        <#if upperFieldName!='Id'&& upperFieldName!='ID'>
        <#if field.listPage=='Y'>
        <th>${field.comment}</th>
		</#if>
        </#if>
		</#list>
		<shiro:hasPermission name="${permissionPrefix}:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${r"${page.list}"}" var="${className}">
			<tr>
		<#list fieldList as field>
        <#assign upperFieldName = StringUtil.firstToUpper(field.name)>
          <#if upperFieldName!='Id'&& upperFieldName!='ID'>
             <#if field.listPage=='Y'>
                 <#if field.asLink == 'Y'>
                 <td><a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className+".id"}}">${"${"+className+".${field.name}"}}</a></td>
                 <#elseif !StringUtil.isBlank(field.dictType)>
                 <td>${r"${"+"fns:getDictLabel(${className}.${field.name}, '${field.dictType}', '未分类')"+r"}"}</td>
                 <#else>
                 <td>${"${"+className+".${field.name}"}}</td>
                 </#if>
               </#if>
           </#if>
		</#list>
				<shiro:hasPermission name="${permissionPrefix}:edit"><td>
    				<a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className+".id}"}">修改</a>
					<a href="${r"${ctx}"}/${urlPrefix}/delete?id=${"${"+className+".id}"}" onclick="return confirmx('确认要删除该${functionName}吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${r"${page}"}</div>
</body>
</html>
