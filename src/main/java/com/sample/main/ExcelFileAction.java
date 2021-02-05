package com.sample.main;

import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileAction{
	XSSFWorkbook work_book;
	XSSFSheet sheet;
	public ExcelFileAction(String excelfilePath) {
		try {
			FileInputStream s = new FileInputStream(excelfilePath);
			work_book = new XSSFWorkbook(s);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public String[] getData(String sheetname, int row, int column){
		sheet = work_book.getSheet(sheetname);
		String data[] = new String[sheet.getRow(row).getLastCellNum()];
		for(int i=0;i<sheet.getRow(row).getLastCellNum();i++){
			data[i]=sheet.getRow(row).getCell(i).getStringCellValue().toString();
//			System.out.print("\n"+data[i]);
//			return data;
		}
		return data;
	} 
	public int getRowCount(int sheetIndex){
		int row = work_book.getSheetAt(sheetIndex).getLastRowNum();
		row = row + 1;
		return row;
	}
}