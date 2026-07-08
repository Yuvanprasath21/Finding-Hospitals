package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtils {
    public static String[] getWellnessFormData(int colNum) {
        String filePath = ConfigReader.getProperty("excel_path");
        String sheetName = "LoginData";
        String[] data = new String[6];
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
             XSSFSheet sheet = workbook.getSheet(sheetName);

            DataFormatter formatter = new DataFormatter();
            for (int i = 0; i < 6; i++) {
                Cell cell = sheet.getRow(i)
                        .getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                data[i] = formatter.formatCellValue(cell).trim();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String[] getPatientFormData() {
        String filePath = ConfigReader.getProperty("excel_path");
        String sheetName = "PatientData";
        String[] data = new String[4];
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);

            DataFormatter formatter = new DataFormatter();
            for (int i = 0; i < 4; i++) {
                Cell cell = sheet.getRow(i)
                        .getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                data[i] = formatter.formatCellValue(cell).trim();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
