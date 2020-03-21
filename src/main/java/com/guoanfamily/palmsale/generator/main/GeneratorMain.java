package com.guoanfamily.palmsale.generator.main;

import com.guoanfamily.palmsale.generator.entity.ColumnEntity;
import com.guoanfamily.palmsale.generator.entity.TableEntity;
import com.guoanfamily.palmsale.generator.repository.GeneratorDao;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 代码生成
 * </p>
 *
 * @auth: 张文旭
 * @time: 2017/6/9 9:22
 * @version: 1.0
 */
@Component
public class GeneratorMain {
    private static Logger logger = LoggerFactory.getLogger(GeneratorMain.class);

    @Autowired
    private GeneratorDao generatorDao;

    /**
     * @param tableNmae                                                   --- 要生成的表名
     * @param packagePath---包名eg（com.guoanfamily.palmsale.demo）
     * @param urlMapping---controller中namespace，项目中使用的是全局常量，这里指定一下(默认：表名)
     * @param author---作者(默认：张文旭)
     * @param version--版本号(默认：1.0)
     * @param tablePrefix--表前缀(有些人的建表习惯，喜欢以sys_开头，或者t_,a_等等，这里把它干掉,可为空)
     * @throws IOException
     */
    public void generatorCode(String tableNmae, String packagePath, String urlMapping, String author, String version, String tablePrefix) throws IOException {
        if (StringUtils.isEmpty(tableNmae)) {
            logger.error("tableNmae不能为空", new RuntimeException("tableNmae不能为空"));
            return;
        }
        if (StringUtils.isEmpty(packagePath)) {
            logger.error("packagePath", new RuntimeException("packagePath不能为空"));
            return;
        }
        Map<String, String> config = new HashMap<>();
        config.put("tablePrefix", "");
        config.put("tinyint", "Integer");
        config.put("smallint", "Integer");
        config.put("mediumint", "Integer");
        config.put("int", "Integer");
        config.put("integer", "Integer");
        config.put("bigint", "Long");
        config.put("float", "Float");
        config.put("double", "Double");
        config.put("decimal", "BigDecimal");
        config.put("char", "String");
        config.put("varchar", "String");
        config.put("tinytext", "String");
        config.put("text", "String");
        config.put("mediumtext", "String");
        config.put("longtext", "String");
        config.put("date", "Date");
        config.put("datetime", "Date");
        config.put("timestamp", "Date");
        //设置生成代码包
        config.put("package", packagePath);
        //设置作者名称
        config.put("author", StringUtils.isEmpty(author) ? "张文旭" : author);
        //设置版本号
        config.put("version", StringUtils.isEmpty(version) ? "1.0" : author);


        if (generatorDao == null) generatorDao = new GeneratorDao();
        Map<String, String> table = generatorDao.findTableInfoByTableName(tableNmae);
        List<Map<String, String>> columns = generatorDao.findTableColumnInfoByTableName(tableNmae);

        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));

        String className = tableToJava(tableEntity.getTableName(), config.get("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));

            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            String attrType = config.getOrDefault(columnEntity.getDataType(), "unknowType");
            columnEntity.setAttrType(attrType);

            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);


        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments().equals("") ? tableEntity.getTableName() + "表操作" : tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", StringUtils.isEmpty(urlMapping) ? tableEntity.getClassname().toLowerCase() : urlMapping);
        map.put("columns", tableEntity.getColumns());
        map.put("package", config.get("package"));
        map.put("author", config.get("author"));
        map.put("version", config.get("version"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        VelocityContext context = new VelocityContext(map);


        List<String> templates = new ArrayList<String>();
        templates.add("templates/Controller.java.vm");
        templates.add("templates/Entity.java.vm");
        templates.add("templates/Repository.java.vm");

        for (String template : templates) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                String projectPath = System.getProperty("user.dir");
                logger.info("====项目根路径:" + projectPath);
                File javaFile = new File(projectPath + File.separator + getFileName(template, tableEntity.getClassName(), config.get("package")));
                logger.info("====java文件生成路径:" + javaFile.getPath());
                if (!javaFile.exists()) {
                    if (!javaFile.getParentFile().exists()) javaFile.getParentFile().mkdirs();
                    javaFile.createNewFile();
                }
                OutputStream osfile = new FileOutputStream(javaFile);
                IOUtils.write(sw.toString(), osfile, "UTF-8");
                IOUtils.closeQuietly(sw);
            } catch (IOException e) {
                throw new RuntimeException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }

    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    public static String getFileName(String template, String className, String packageName) {
        String packagePath = "src" + File.separator + "main" + File.separator + "java" + File.separator;
        if (org.apache.commons.lang.StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Repository.java.vm")) {
            return packagePath + "repository" + File.separator + className + "Repository.java";
        }

        return null;
    }
}

/**
 * 日期处理
 * 内部类
 * 不被其他人使用
 */
class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
}