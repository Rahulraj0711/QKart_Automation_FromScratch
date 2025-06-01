package rahul.raj.testUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
    DataFormatter formatter=new DataFormatter();

    @DataProvider(name="testData")
    public Object[][] dpMethod(Method m) throws IOException {
        FileInputStream excelFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/rahul/raj/resources/DataFile.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(excelFile);
        XSSFSheet sheet=workbook.getSheet(m.getName());

        int rowCount=sheet.getPhysicalNumberOfRows();
        int colCount=sheet.getRow(0).getLastCellNum();
        Object[][] finalData=new Object[rowCount-1][colCount-1];

        for(int i=0;i<rowCount-1;i++) {
            XSSFRow row=sheet.getRow(i+1);
            for(int j=0;j<colCount-1;j++) {
                XSSFCell cellValue=row.getCell(j+1);
                finalData[i][j]=formatter.formatCellValue(cellValue);
            }
        }

        return finalData;
//        int rowIndex = 0;
//        int cellIndex = 0;
//        List<List<?>> outputList = new ArrayList<>();
//
//        // Random Comment
//        FileInputStream excelFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/rahul/raj/resources/DataFile.xlsx");
//        Workbook workbook = new XSSFWorkbook(excelFile);
//        Sheet selectedSheet = workbook.getSheet(m.getName());
//        for (Row row : selectedSheet) {
//            Iterator<Cell> cellIterator = row.cellIterator();
//            List<String> innerList = new ArrayList<>();
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                if (rowIndex > 0 && cellIndex > 0) {
//                    if (cell.getCellType() == CellType.STRING) {
//                        innerList.add(cell.getStringCellValue());
//                    } else if (cell.getCellType() == CellType.NUMERIC) {
//                        innerList.add(String.valueOf(cell.getNumericCellValue()));
//                    }
//                }
//                cellIndex = cellIndex + 1;
//            }
//            rowIndex = rowIndex + 1;
//            cellIndex = 0;
//            if (!innerList.isEmpty())
//                outputList.add(innerList);
//        }
//        excelFile.close();
//        workbook.close();
//
//        return outputList.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::ne
    }
}
