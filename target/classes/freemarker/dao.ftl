/*****************************************************************
 *仿佛兮若轻云之蔽月，飘飘兮若流风之回雪
 *@author ${classAuthor}
 *@copyright all rights reserved (c) wyyft@163.com
 *****************************************************************/
package ${packageName}.${moduleName}.dao${subModuleName};
<#assign ClassName=StringUtil.firstToUpper(className)>
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseDaoImpl;
import ${packageName}.${moduleName}.entity${subModuleName}.${ClassName};

/**
 * ${functionName}DAO接口
 * @author ${classAuthor}
 * @version ${classVersion}
 */
public interface ${ClassName}Dao extends ${ClassName}DaoCustom, CrudRepository<${ClassName}, Long> {

	@Modifying
	@Query("update ${ClassName} set delFlag='" + ${ClassName}.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);
	
}

/**
 * DAO自定义接口
 * @author ${classAuthor}
 */
interface ${ClassName}DaoCustom extends BaseDao<${ClassName}> {

}

/**
 * DAO自定义接口实现
 * @author ${classAuthor}
 */
@Component
class ${ClassName}DaoImpl extends BaseDaoImpl<${ClassName}> implements ${ClassName}DaoCustom {

}
