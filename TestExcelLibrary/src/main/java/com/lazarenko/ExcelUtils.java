package com.lazarenko;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelUtils {
    public static void writeExcelFile(List<User> userList, File file) {

        HSSFWorkbook book = new HSSFWorkbook();                         //создание объекта файла
        HSSFSheet sheet = book.createSheet("Users Sheet");     //создание нового листа в объекте
        fillHeaders(sheet);
        int lastEmptyRow = 1;
        for (int i = 0; i < userList.size(); i++) {
            writeUser(sheet, userList.get(i), lastEmptyRow);
            lastEmptyRow++;
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            book.write(fileOutputStream);
            book.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillHeaders(HSSFSheet sheet) {

        Class<User> userClass = User.class;
        Field[] declaredFields = userClass.getDeclaredFields();

        Row row = sheet.createRow(0);

        for (int i = 0; i < declaredFields.length; i++) {
            Cell cell = row.createCell(i);
            String fieldName = declaredFields[i].getName();
            cell.setCellValue(fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
        }

    }

    private static void writeUser(HSSFSheet sheet, User user, int numberRow) {
        Row row = sheet.createRow(numberRow);

        Field[] declaredFields = user.getClass().getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            Cell cell = row.createCell(i);
            declaredFields[i].setAccessible(true);
            Object valueField = "";
            try {
                valueField = declaredFields[i].get(user);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            cell.setCellValue(valueField.toString());
        }
    }


}
