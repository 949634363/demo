package com.hx.demo.util;

import com.hx.demo.annotation.ExcelCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ExcelUtil
 * excel工具类
 * @author LiaoCaiYun
 * @date 2020/7/17
 */
public class ExcelUtil {

    private static volatile ExcelUtil excelUtil;

    private ExcelUtil(){}

    public static ExcelUtil getInstance() {
        if (excelUtil == null) {
            synchronized (ExcelUtil.class) {
                if (excelUtil == null) {
                    excelUtil = new ExcelUtil();
                }
            }
        }
        return excelUtil;
    }

    public <T> HSSFWorkbook toExcel(List<T> data, Class<T> clazz, String excelName) {
        if (data == null || clazz == null) {
            throw new NullPointerException("生成Excel失败，请添加对应的数据");
        }
        // 创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet(excelName);
        // 创建表头
        HSSFRow head = sheet.createRow(0);
        Field[] declaredFields = clazz.getDeclaredFields();
        List<String> heads = new ArrayList<>(declaredFields.length);
        for (Field declaredField : declaredFields) {
            ExcelCell annotation = declaredField.getAnnotation(ExcelCell.class);
            if (annotation == null) {
                continue;
            }
            heads.add(declaredField.getName());
            head.createCell(heads.size() - 1).setCellValue(annotation.name());
        }
        // 添加数据
        for (int rowNum = 0; rowNum < data.size(); rowNum++) {
            HSSFRow row = sheet.createRow(rowNum + 1);
            for (int lineNum = 0; lineNum < heads.size();  lineNum++) {
                try {
                    Method method = clazz.getMethod("get"
                            + heads.get(lineNum).substring(0, 1).toUpperCase()
                            + heads.get(lineNum).substring(1));
                    Object result = method.invoke(data.get(rowNum));
                    row.createCell(lineNum).setCellValue(String.valueOf(result));
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }
}
