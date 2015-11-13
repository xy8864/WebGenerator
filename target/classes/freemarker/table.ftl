DROP TABLE IF EXISTS `${table.name}`;
CREATE TABLE `${table.name}` (
<#list columnList as column>
    `${column.name}` ${column.type}<#if (column.length != "0") && (column.precision != "0")>(${column.length},${column.precision})<#elseif column.length != "0">(${column.length})</#if> <#if column.notnull == "Y">NOT NULL</#if> <#if column.pk == "Y">AUTO_INCREMENT</#if> <#if column.comment != "">COMMENT '${column.comment}',</#if>
</#list>
    `create_by` BIGINT(20) NULL DEFAULT NULL COMMENT '创建者',
	`create_date` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	`update_by` BIGINT(20) NULL DEFAULT NULL COMMENT '更新者',
	`update_date` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
	`del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
	`remarks` nvarchar(255),
	PRIMARY KEY (`${table.pk}`),
	INDEX `${table.name}_create_by` (`create_by`),
	INDEX `${table.name}_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

