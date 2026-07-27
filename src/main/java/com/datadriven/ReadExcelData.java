package com.datadriven;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcelData {
	
	
	@Test
	public void dataRead() throws IOException {
		
		XSSFWorkbook book = new XSSFWorkbook("./Parameters/Data.xlsx");      //workbook
		XSSFSheet sheet = book.getSheet("Sheet1");                        //worksheet
		
		int rowcount = sheet.getLastRowNum();                             //row  count
		short collcount = sheet.getRow(0).getLastCellNum();               //colum count
		
		String[][] data = new String [rowcount][collcount];
		
		for(int i=1; i<=rowcount; i++) 
		{
			XSSFRow row = sheet.getRow(i);
			
			for(int j=0; j<collcount; j++)
			{
				XSSFCell cell = row.getCell(j);
				String values = cell.getStringCellValue();
				
			}
		}
		
		
		
	}

}
