package com.basic.core.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作excel的工具类
 * @author: wangzw
 * @date: 2018/5/31 14:11
 * @version: 1.0
 */
public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    private static int counter = 0;
    private static final int sheetNum=6;

    public static void main(String[] args) {
        String excelPath = "E:/标日/book1-unit1/lesson4/lesson4.xlsx";
        List<Map> mapList = readExcel(excelPath);
        String toExcelPath = "E:/标日/book1-unit1/lesson4/lesson4_transfer.xlsx";
        writeExcel(mapList,toExcelPath);
    }

    public static int countStrNum(String str1, String str2) {
        if (str1.indexOf(str2) == -1) {
            return 0;
        } else if (str1.indexOf(str2) != -1) {
            counter ++;
            countStrNum(str1.substring(str1.indexOf(str2) + str2.length()), str2);
            return counter;
        }
        return 0;
    }

    public static List<Map> readExcel(String excelPath){
        try {
            File excel = new File(excelPath);
            if (excel.isFile() == false || excel.exists() == false) {
                System.out.println("excelPath不是个文件或excelPath不存在！");
            }
            Workbook workbook = getWorkbook(excel);
            if(workbook == null){
                System.out.println("文件类型错误!");
                return null;
            }
            List<Map> dataList = new ArrayList<>();
            for(int a=0;a<sheetNum;a++){
                Map dataListMap = new HashMap<>();
                Sheet sheet = workbook.getSheetAt(a);
                List<Map> mapList = getListFromExcel(sheet);
                dataListMap.put("sheetName",sheet.getSheetName());
                dataListMap.put("dataList",mapList);
                dataList.add(dataListMap);
            }
            System.out.println("读取成功....");
            return dataList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Map> getListFromExcel(Sheet sheet){
        List<Map> dataList = new ArrayList<>();
        for(int rowIndex = sheet.getFirstRowNum(); rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                Map dataMap = new HashMap<>();
                for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    if (cell != null) {
                        String cellText = cell.toString();
                        int indexOf = cellText.indexOf("tag");
                        if(indexOf > 0){
                            int countStrNum = countStrNum(cellText, "<tag");
                            for(int i=0;i<countStrNum;i++){
                                cellText = cellText.substring(cellText.indexOf(">")+1,cellText.length());
                                cellText = cellText.replace("</tag>","");
                            }
                        }
                        int img = cellText.indexOf("img");
                        if(img > 0){
                            cellText = cellText.replace("<img>","").replace("</img>","");
                        }
                        dataMap.put("cell_"+cellIndex,cellText);
                    }
                }
                dataMap.put("cellNum",row.getLastCellNum());
                dataList.add(dataMap);
            }
        }
        return dataList;
    }

    public static Workbook getWorkbook(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    public static void writeExcel(List<Map> dataListMap, String finalXlsxPath){
        OutputStream out = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            for(int b=0;b<dataListMap.size();b++){
                Map dataThreeMap = dataListMap.get(b);
                String sheetName = dataThreeMap.get("sheetName").toString();
                List<Map> dataList = (List<Map>) dataThreeMap.get("dataList");
                Sheet sheet = workbook.createSheet(sheetName);
                for (int j = 0; j < dataList.size(); j++) {
                    Row row = sheet.createRow(j);
                    Map dataMap = dataList.get(j);
                    int cellNum = Integer.parseInt(dataMap.get("cellNum").toString());
                    for (int k = 0; k < cellNum; k++) {
                        Object value_v = dataMap.get("cell_"+k);
                        if(value_v == null){
                            continue;
                        }
                        String value = value_v.toString();
                        Cell cell = row.createCell(k);
                        cell.setCellValue(value);
                    }
                }
            }
            out =  new FileOutputStream(finalXlsxPath);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功----------");
    }
}
