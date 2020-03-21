package com.guoanfamily.palmsale.common.util;

import com.guoanfamily.palmsale.annotation.Excel;
import com.guoanfamily.palmsale.common.abstractobj.IdEntity;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * excel导入导出工具
 * </p>
 *
 * @auth: 张文旭
 * @time: 2017/6/19 11:06
 * @version: 1.0
 */
public class ExcelUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    private static final String excelPath = "D:/";


    /**
     * excel导出
     *
     * @param data
     * @return
     */
    public static String buildExcel(List<?> data) {
        return buildExcel(excelPath, data);
    }

    /**
     * 创建excel文件---excel导出
     *
     * @param excelPath---生成excel的路径
     * @param data--导出数据
     * @return
     */
    public static String buildExcel(String excelPath, List<?> data) {
        IdEntity object = null;
        if (data == null || data.size() < 1) {
            logger.warn("空数据集合");
            throw new RuntimeException("数据为null");
        } else {
            if (data.get(0) instanceof IdEntity) {
                object = (IdEntity) data.get(0);
            } else {
                logger.warn("list泛型必须是IdEntity或其子类");
                throw new RuntimeException("不支持的List泛型,list泛型必须是IdEntity或其子类");
            }
        }
        OutputStream out = null;
        String path = excelPath + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        List<Excel> excels = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("index");
            sheet.setDefaultColumnWidth((short) 20);
            Row title = sheet.createRow((short) 0);
            CellStyle titleStyle = wb.createCellStyle();
            titleStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
            titleStyle.setTopBorderColor((short) 0);
            Field[] fields = object.getClass().getDeclaredFields();
            Excel excel;
            for (Field f : fields) {
                Annotation[] fildAnnotation = f.getAnnotations();
                for (Annotation annotation : fildAnnotation) {
                    if (annotation instanceof Excel) {
                        excel = (Excel) annotation;
                        excels.add(excel);
                    }
                }
            }
            for (int i = 0; i < excels.size(); i++) {
                Cell cell = title.createCell(i);
                cell.setCellStyle(titleStyle);
                HSSFRichTextString text = new HSSFRichTextString(excels.get(i).name());
                cell.setCellValue(text);
            }
            if (data != null) {
                for (int rowNum = 1; rowNum < data.size() + 1; rowNum++) {
                    Row row = sheet.createRow((short) rowNum);
                    Object obj = data.get(rowNum - 1);
                    IdEntity entity = (IdEntity) obj;
                    for (int i = 0; i < excels.size(); i++) {
                        if (entity.getKey(excels.get(i).ref()).toString().contains("http://")) {
                            Cell cell = row.createCell(i);
                            cell.setCellFormula("HYPERLINK(\"" +entity.getKey(excels.get(i).ref()).toString()+ "\")");
                            CellStyle linkStyle = wb.createCellStyle();
                            Font cellFont= wb.createFont();
                            cellFont.setUnderline((byte) 1);
                            cellFont.setColor(HSSFColor.BLUE.index);
                            linkStyle.setFont(cellFont);
                            cell.setCellStyle(linkStyle);
                        } else {
                            Cell cell = row.createCell(i);
                            cell.setCellStyle(titleStyle);
                            HSSFRichTextString text = new HSSFRichTextString(entity.getKey(excels.get(i).ref()).toString());
                            cell.setCellValue(text);
                        }
                    }
                }
            }
            //输出文件？？
            out = new FileOutputStream(file);
            wb.write(out);
        } catch (Exception e) {
            logger.error("excel写入异常", e);
        } finally {
            IOUtils.closeQuietly(out);
        }
        return path;
    }
}
