package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static final String FILE_PATH =
            "C:\\Users\\2497837\\OneDrive - Cognizant\\Desktop\\Automation files\\LoginData.xlsx";

    public static String[] getLoginData(int rowNum) throws IOException {

        String[] data = new String[2];

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found.");
            }

            Row row = sheet.getRow(rowNum);

            if (row == null) {
                throw new RuntimeException("Row " + rowNum + " not found.");
            }

            DataFormatter formatter = new DataFormatter();

            data[0] = formatter.formatCellValue(row.getCell(0));
            data[1] = formatter.formatCellValue(row.getCell(1));

            System.out.println("Cell 0 (Username/Phone): " + data[0]);
            System.out.println("Cell 1 (Password): " + data[1]);
        }

        return data;
    }
}