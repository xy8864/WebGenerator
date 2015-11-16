package com.github.xy8864.webGenerator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * Created by IntelliJ IDEA.
 * User: pangj
 * Date: 2012-3-6
 * Time: 10:05:26
 * 文件操作工具类
 */
public class FreemarkerUtil{
	private static final Logger log = LoggerFactory.getLogger(FreemarkerUtil.class);


	public static final String TEMPLATE_BO="";
	public static final String TEMPLATE_BOIMPL="";

	public static String PROJECT_ROOT = "projectRoot";

	public static String PACKAGE_NAME = "packageName";

	public static String CLASS_SIMPLE_NAME = "classSimpleName";

	public static String MODULES_PACKAGE = "modulesPackage";

	public static String METHOD_NAME = "methodName";

	//模板根目录
	private static final String templateRootWithClasspath = "classpath*:com/hundsun/pms/generator/resource/template";

	private static final String templateRoot = "com/hundsun/pms/generator/resource/template";
	//工程根目录
	private static String projectRoot;
	//包名
	private static String packageName;
	//服务所在的包路径
	private static String modulesPackage;

	private static String classSimpleName;

	private static String templateName;

	private static String methodName;

	public static final String FILE_BO_NAME_SUFFIX = "Bo.java";

	public static final String FILE_BOIMPL_NAME_SUFFIX = "BoImpl.java";

	public static final String FILE_DAO_NAME_SUFFIX = "Dao.java";

	public static final String FILE_DAOIMPL_NAME_SUFFIX = "DaoImpl.java";

	public static final String FILE_DTO_NAME_SUFFIX = ".java";

	public static final String FILE_SQL_MAP_SUFFIX = ".xml";

	public static final String FILE_SERVICE_SUFFIX = ".service";

	public static final String FILE_COMPONENT_SUFFIX = ".component";

	public static final String FILE_MODULE_NAME = "module.xml";

	public static final String FILE_PAGE_SUFFIX = ".jsp";


	/**
	 * 数据绑定至模板
	 *
	 * @param tpName 模板名
	 * @param param  参数
	 * @return
	 */
	public static void process(String tpName, Map<String, Object> param) {
		init(tpName, param);
		Writer out = null;
		try {
			Template temp = getTemplate(templateName);
			String fileDir = getFileDir();
			File dir = new File(fileDir);
			if (!dir.exists()) {
				if (!dir.mkdirs()) {
					log.info("创建目录："+fileDir+"失败");
					return;
				}
			}
			File f = new File(fileDir + getFileName());
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "utf-8"));
			try {
				temp.setEncoding("utf-8");
				temp.process(param, out);
				log.info("文件："+getFileDir()+getFileName()+"生成完成！");
			} catch (TemplateException e) {
				log.info("文件："+getFileDir()+getFileName()+"生成失败，请检查原因！");
				log.info(e.getMessage());
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
	}

	/**
	 * 生成页面
	 *
	 * @param tpName 模板名
	 * @param jsp    jsp页面全路径
	 * @param page   页面对象
	 */
	public static void process(String tpName, String jsp, Map<String,Object> page) {
		Writer writer = null;
		try {
			Template tmp = FreemarkerUtil.getTemplate(tpName);
			String dir = jsp.substring(0, jsp.lastIndexOf("\\"));
			File fdir = new File(dir);
			if (!fdir.exists()) {
				if (!fdir.mkdirs()) {
					log.info("创建目录："+fdir+"失败");
					return;
				}
			}
			File file = new File(jsp);
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			tmp.setEncoding("utf-8");
			tmp.process(page, writer);
			writer.flush();
			writer.close();
			log.info("页面："+jsp+" 生成成功!");
		} catch (TemplateException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
	}

	public static Template getTemplate(String templateName) {
		Configuration cfg = new Configuration();
		Template temp = null;
		URL path = ClassLoader.getSystemResource("");
		File tmpRootFile = null;
		if (path == null) {
			tmpRootFile = getResourceURL("resource/template");
		} else {
			String tmpRoot = path + templateRoot;
			tmpRoot = tmpRoot.replaceAll("file:/", "");
			tmpRootFile = new File(tmpRoot);
		}
		if (tmpRootFile == null) {
			throw new RuntimeException("无法取得模板根路径！");
		}
		try {
			cfg.setDefaultEncoding("utf-8");
			cfg.setOutputEncoding("utf-8");
			cfg.setDirectoryForTemplateLoading(tmpRootFile);
            /*cfg.setDirectoryForTemplateLoading(getResourceURL());*/
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			temp = cfg.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return temp;
	}

	public static File getResourceURL(String templatePath) {
		try {
			URL url = ResourceUtils.getURL(templateRootWithClasspath);
			String path = url.getPath();
			path = path.replace(templateRootWithClasspath, templatePath);
			log.info("File path is:"+path);
			path = StringUtils.replace(path, "%20", " ");
			return new File(path);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("不能获取路径：" + templateRootWithClasspath);
		}
	}

	private static void init(String tpName, Map<String, Object> param) {
		projectRoot = (String) param.get(PROJECT_ROOT);
		packageName = (String) param.get(PACKAGE_NAME);
		classSimpleName = (String) param.get(CLASS_SIMPLE_NAME);
		modulesPackage = (String) param.get(MODULES_PACKAGE);
		methodName = (String) param.get(METHOD_NAME);
		templateName = tpName;
	}

	private static String getFileName() {
		if (TEMPLATE_BO.equals(templateName)) {
			return classSimpleName + FILE_BO_NAME_SUFFIX;
		} else if (Constants.TEMPLATE_BOIMPL.equals(templateName)) {
			return classSimpleName + FILE_BOIMPL_NAME_SUFFIX;
		} else if (Constants.TEMPLATE_DAO.equals(templateName)) {
			return classSimpleName + FILE_DAO_NAME_SUFFIX;
		} else if (Constants.TEMPLATE_DAOIMPL.equals(templateName)) {
			return classSimpleName + FILE_DAOIMPL_NAME_SUFFIX;
		} else if (Constants.TEMPLATE_DTO.equals(templateName)) {
			return classSimpleName + FILE_DTO_NAME_SUFFIX;
		} else if (Constants.TEMPLATE_SQLMAP.equals(templateName)) {
			return classSimpleName + FILE_SQL_MAP_SUFFIX;
		} else if (Constants.TEMPLATE_SERVICE.equals(templateName)) {
			return methodName + FILE_SERVICE_SUFFIX;
		} else if (Constants.TEMPLATE_COMPONENT.equals(templateName)) {
			////return NamingRuleConvert.firstLetterToLowerCase(classSimpleName) + FILE_COMPONENT_SUFFIX;
		} else if (Constants.TEMPLATE_CS.equals(templateName) || Constants.TEMPLATE_DS.equals(templateName) ||
				Constants.TEMPLATE_MODULE.equals(templateName)) {
			return FILE_MODULE_NAME;
		}
		return null;
	}

	private static String getFileDir() {
		String path = null;
		if (packageName != null)
			path = packageName.replaceAll("\\.", "/");
		String servicePath = null;
		if (!StringUtils.isEmpty(modulesPackage)) {
			servicePath = modulesPackage.replaceAll("\\.", "/");
		}
		if (Constants.TEMPLATE_BO.equals(templateName)) {
			return projectRoot + Constants.SRC_DIR + path + "/bo/";
		} else if (Constants.TEMPLATE_BOIMPL.equals(templateName)) {
			return projectRoot + Constants.SRC_DIR + path + "/bo/impl/";
		} else if (Constants.TEMPLATE_DAO.equals(templateName)) {
			return projectRoot + Constants.SRC_DIR + path + "/dao/";
		} else if (Constants.TEMPLATE_DAOIMPL.equals(templateName)) {
			return projectRoot + Constants.SRC_DIR + path + "/dao/impl/";
		} else if (Constants.TEMPLATE_DTO.equals(templateName)) {
			return projectRoot + Constants.SRC_DIR + path + "/dto/";
		} else if (Constants.TEMPLATE_SQLMAP.equals(templateName)) {
			return projectRoot + Constants.SRC_DIR + path + "/dao/maps/";
		} else if (Constants.TEMPLATE_SERVICE.equals(templateName) || (Constants.TEMPLATE_CS.equals(templateName))) {
			return projectRoot + "/modules/" + servicePath + "/cs/";
		} else if (Constants.TEMPLATE_COMPONENT.equals(templateName) || Constants.TEMPLATE_DS.equals(templateName)) {
			return projectRoot + "/modules/" + servicePath + "/ds/";
		} else if (Constants.TEMPLATE_MODULE.equals(templateName)) {
			return projectRoot + "/modules/" + servicePath + "/";
		}
		return null;
	}
}