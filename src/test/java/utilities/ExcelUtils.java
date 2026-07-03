package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static String[] getLoginData(int rowNum) throws IOException{
        String filePath = ConfigReader.getProperty("excel_path");
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("LoginData");
        Row row = sheet.getRow(rowNum);
        String[] data = new String[2];
        data[0] = row.getCell(0).toString().trim(); // Mobile/Email
        data[1] = row.getCell(1).toString().trim(); // Password
        workbook.close();
        fis.close();
        return data;
    }
}
