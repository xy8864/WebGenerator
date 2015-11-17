package com.github.xy8864.webGenerator.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.github.xy8864.webGenerator.core.Column;
import com.github.xy8864.webGenerator.util.CharsetUtil;
import com.github.xy8864.webGenerator.util.ClassUtil;
import com.github.xy8864.webGenerator.util.FileUtil;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * @author WYY
 * @description
 */
public class FreeMarkerEngine implements Engine{
	private static final Logger logger=LoggerFactory.getLogger(FreeMarkerEngine.class);
	private static final Configuration config=new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

	/** 模板文件路径 */
	public FreeMarkerEngine(String templateDir){
		try{
			if(StringUtils.isEmpty(templateDir)){
				config.setDirectoryForTemplateLoading(new File(ClassUtil.getClassPath()));
			}else{
				config.setDirectoryForTemplateLoading(ResourceUtils.getFile(templateDir));
			}
			config.setDefaultEncoding(CharsetUtil.UTF_8.displayName());
			config.setLocale(Locale.CHINA);
			config.setDefaultEncoding("utf-8");
			config.setEncoding(Locale.CHINA, "utf-8");
			//config.setServletContextForTemplateLoading(servletContext, templateDir);
			config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
		}catch(IOException e){
			logger.error("file:{}", templateDir, e);
			System.exit(0);
		}
	}

	// 合并模板到文件中
	@Override
	public void writeToFile(String ftlPath, Map<String, Object> model, String filePath){
		Writer writer=null;
		try{
			if(FileUtil.createFile(filePath)){
				Template template=config.getTemplate(ftlPath);
				//FileWriter writer=new FileWriter(filePath);
				writer = new OutputStreamWriter(new FileOutputStream(filePath),CharsetUtil.UTF_8);
				template.process(model, writer);
			}else{
				logger.info("文件已存在,生成失败");
			}
		}catch(Exception e){
			logger.error("合并模板出错！", e);
			throw new IllegalStateException("合并模板出错");
		}finally{
			IOUtils.closeQuietly(writer);
		}
	}

	// 合并模板并返回字符串
	@Override
	public String toString(String ftlPath, Map<String, Object> model){
		String result;
		StringWriter writer=null;
		try{
			Template template=config.getTemplate(ftlPath);
			writer=new StringWriter();
			template.process(model, writer);
			result=writer.toString();
			//writer.close();
		}catch(Exception e){
			logger.error("合并模板出错！", e);
			throw new IllegalStateException(e);
		}finally{
			IOUtils.closeQuietly(writer);
		}

		return result;
	}

	@Override
	public String getTemplateSuffix(){
		return ".ftl";
	}

	public static TemplateHashModel useStaticPackage(String packageName){
		try{
			//BeansWrapper wrapper=BeansWrapper.getDefaultInstance();
			BeansWrapperBuilder builder = new BeansWrapperBuilder(Configuration.VERSION_2_3_22);
			builder.setExposeFields(true);
			BeansWrapper wrapper=builder.build();;
			TemplateHashModel staticModels=wrapper.getStaticModels();
			return (TemplateHashModel) staticModels.get(packageName);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	public static void main(String[] args){
		FreeMarkerEngine engine=new FreeMarkerEngine("D:/dev/src/git/spring/WebGenerator/src/main/resources/freemarker/");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("package","com.cnlot.booking.domain");
		map.put("className","Trainer");

		List<Column> columns=new ArrayList<Column>();
		columns.add(new Column("id","Long",null));
		columns.add(new Column("name","String","名称"));
		columns.add(new Column("createTime","Date","创建时间"));

		map.put("fieldList", columns);
		map.put("StringUtil", useStaticPackage("com.github.xy8864.webGenerator.util.StringUtil"));
		map.put("StringUtils", useStaticPackage("org.apache.commons.lang3.StringUtils"));

		System.out.println(engine.toString("domain.ftl",map));
	}
}
