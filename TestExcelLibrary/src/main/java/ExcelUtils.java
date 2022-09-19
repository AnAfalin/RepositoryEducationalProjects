import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {
    public static void writeExcelFile(List<User> userList, File file){
        HSSFWorkbook book = new HSSFWorkbook();                         //создание объекта файла
        HSSFSheet sheet = book.createSheet("Users Sheet");     //создание нового листа в объекте
        fillHeaders(sheet);
        int lastEmptyRow = 1;
        for (int i = 0; i < userList.size(); i++) {
            writeUser(sheet, userList.get(i), lastEmptyRow);
            lastEmptyRow++;
        }

        try {
            book.write(new FileOutputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            book.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillHeaders(HSSFSheet sheet){
        Row row0 = sheet.createRow(0);   //Создание первой строки, нумерация начинается с 0
        Cell cell0 = row0.createCell(0);      //Создание в строке первой ячейки, нумерация с 0
        cell0.setCellValue("Firstname");       //Установление имени ячейки

        Cell cell1 = row0.createCell(1);
        cell1.setCellValue("Lastname");

        Cell cell2 = row0.createCell(2);
        cell2.setCellValue("Age");

        Cell cell3 = row0.createCell(3);
        cell3.setCellValue("Profession");
    }

    private static void writeUser(HSSFSheet sheet, User user, int numberRow){
        Row row = sheet.createRow(numberRow);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue(user.getFirstname());

        Cell cell1 = row.createCell(1);
        cell1.setCellValue(user.getLastname());

        Cell cell3 = row.createCell(2);
        cell3.setCellValue(user.getAge());

        Cell cell4 = row.createCell(3);
        cell4.setCellValue(user.getProfession());
    }


}
