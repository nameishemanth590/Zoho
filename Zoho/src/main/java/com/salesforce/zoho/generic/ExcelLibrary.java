package com.salesforce.zoho.generic;

import java.io.FileInputStream;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {
	static FileInputStream file;
	static Workbook workbook;
	static {
		try {
			file = new FileInputStream(IAutoConstants.XL_PATH);
			workbook=WorkbookFactory.create(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getStringData(String sheetName, int row, int cell) {
		try {
			
			return workbook.getSheet(sheetName).getRow(row).getCell(cell).toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Double getNumericData(String sheetName, int row, int cell) {
		try {
			return workbook.getSheet(sheetName).
						getRow(row).getCell(cell).getNumericCellValue();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean getBooleanData(String sheetName, int row, int cell) {
		try {
			
			return workbook.getSheet(sheetName).
						getRow(row).getCell(cell).getBooleanCellValue();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static LocalDateTime getDate(String sheetName, int row, int cell) {
		try {
			
			return workbook.getSheet(sheetName).
						getRow(row).getCell(cell).getLocalDateTimeCellValue();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String[][] getMultipleData(String sheetName) {
		try {
			Sheet sheet = workbook.getSheet(sheetName);
			int rowCount=sheet.getPhysicalNumberOfRows();		
			String[][] sarr = new String[rowCount-1][];
			
			for(int i=1,k=0;i<=rowCount-1;i++,k++) {
				int cellCount=sheet.getRow(i).getPhysicalNumberOfCells();
				sarr[k]=new String[cellCount];
				for(int j=0;j<=cellCount-1;j++) {
					sarr[k][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				}
			}
			
			return sarr;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
