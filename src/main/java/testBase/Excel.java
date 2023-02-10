package testBase;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {

	public static String getCellValue(String path, String sheet, int r, int c) {
		String value = "";
		try {

			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			value = wb.getSheet(sheet).getRow(r).getCell(c).toString();

		} catch (Exception e) {
		}
		return value;
	}

	public static int getRowCount(String path, String sheet) {
		int row = 0;
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			row = wb.getSheet(sheet).getLastRowNum();
		} catch (Exception e) {
		}
		return row;
	}
}
