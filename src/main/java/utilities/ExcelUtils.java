package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {

    public static void writeCitiesToExcel(List<String> topCities,
                                          List<String> allCities) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Cities");
        // Header Row
        Row header = sheet.createRow(0);
        Cell allCityHeader = header.createCell(0);
        allCityHeader.setCellValue("ALL CITIES");
        Cell topCityHeader = header.createCell(1);
        topCityHeader.setCellValue("TOP CITIES");

        int maxRows = Math.max(allCities.size(), topCities.size());
        for (int i = 0; i < maxRows; i++) {
            Row row = sheet.createRow(i + 1);
            if (i < allCities.size()) {
                row.createCell(0).setCellValue(allCities.get(i));
            }
            if (i < topCities.size()) {
                row.createCell(1).setCellValue(topCities.get(i));
            }
        }

        // Auto fit columns
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        FileOutputStream fos = new FileOutputStream("Cities.xlsx");
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
}

