package com.example.DataDrivenTEsting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.IntStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//ExcelFile-> workBook->sheets-> cells

public class ReadDataFromExcelFile {
    public static Object[][] GetExcelData(String filePath,String SheetName) throws IOException {

        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(SheetName);
        int totalRows = sheet.getLastRowNum();
        // System.out.println("sheetnumber:-" + sheetnum);

        // get the data from sheet
        int total_cells = sheet.getRow(1).getLastCellNum();

        System.out.println("number of rows:-" + totalRows);
        System.out.println("Total number of cells:- " + total_cells);

        Object[][] data = new Object[totalRows][total_cells];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < total_cells; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }
        workbook.close();
        return data;

    }

}
