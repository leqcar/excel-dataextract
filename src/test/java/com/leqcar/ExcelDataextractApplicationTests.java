package com.leqcar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExcelDataextractApplication.class)
@WebAppConfiguration
public class ExcelDataextractApplicationTests {

	String SAMPLE_PERSON_DATA_XLSX_FILE_PATH = "src/test/resources/Sample-Person-Data.xlsx";
	String SAMPLE_PERSON_DATA_XLS_FILE_PATH = "src/test/resources/Sample-Person-Data.xls";
	
	@Test
	public void contextLoads() {
	}

	
	@Test
	public void testShouldSimplyIterateThruAllRows() throws InvalidFormatException, IOException {
		File xlsFile = new File(SAMPLE_PERSON_DATA_XLSX_FILE_PATH);
		InputStream inp = new FileInputStream(xlsFile);
		Workbook wb = WorkbookFactory.create(inp);
		
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			Row row = (Row) rows.next();
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				Cell cell = (Cell) cells.next();
				
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					doLog(cell.getRichStringCellValue().getString());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						doLog(cell.getDateCellValue()+"");
					} else {
						System.out.println(cell.getNumericCellValue());
					}
					break;		
				case Cell.CELL_TYPE_BOOLEAN:
					doLog(cell.getBooleanCellValue()? "Yes" : "No");
					break;

				default:
					break;
				}
			}
		}
		inp.close();
	}
	
	private static void doLog(String message) {
		System.out.println(message);
	}
}
