package excelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	 public FileInputStream fis = null;
	    public FileOutputStream fos = null;
	    public XSSFWorkbook workbook = null;
	    public XSSFSheet sheet = null;
	    public XSSFRow row = null;
	    public XSSFRow lastRow  = null;
	    public XSSFRow newRow  = null;


	    public XSSFCell cell = null;
	    public XSSFCell newCell = null;
	     
	    String xlFilePath;
	 
	    public Excel(String xlFilePath) throws Exception
	    {
	        this.xlFilePath = xlFilePath;
	        fis = new FileInputStream(xlFilePath);
	        workbook = new XSSFWorkbook(fis);
	        fis.close();
	    }
	 
	    public  boolean setCellData(String sheetName, int colNumber, int rowNum, String value)
	    {
	        try
	        {
	        	
	        	
	        	
	            sheet = workbook.getSheet(sheetName);
	            row = sheet.getRow(rowNum);
	            if(row==null)
	                row = sheet.createRow(rowNum);
	 
	            cell = row.getCell(colNumber);
	            if(cell == null)
	                cell = row.createCell(colNumber);
	 
	            cell.setCellValue(value);
	 
	            fos = new FileOutputStream(xlFilePath);
	            workbook.write(fos);
	            fos.close();
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	            return  false;
	        }
	        return true;
	    }
	    
	    
	    public  boolean setCellDataAppend(String filePath,String sheetName, String value)
	    {
	    	
	         
	         try {
	             FileInputStream fis = new FileInputStream(new File(filePath));
	             XSSFWorkbook workbooks = new XSSFWorkbook(fis);
	             
	             // Get the sheet you want to append data to
	             XSSFSheet sheet = workbooks.getSheet(sheetName);
	             
	             // Determine the last row index with physically existing data
	             int lastRowIndex = sheet.getLastRowNum();
	             int nextRowIndex = lastRowIndex + 1;
	             
	             // Get the last physically existing row
	             Row lastRow = sheet.getRow(lastRowIndex);
	             
	             // Determine the last cell index with physically existing data in the last row
	             int lastCellIndex = lastRow.getLastCellNum();
	             int nextCellIndex = lastCellIndex + 1;
	             
	             // Create a new row if there are no physically existing rows, otherwise use the last row
	             Row newRow = sheet.createRow(lastRowIndex + 1);
	             
	             // Create a new cell at the next cell index
	             Cell newCell = newRow.createCell(nextCellIndex);
	             
	             // Set the value of the new cell
	             newCell.setCellValue(value);
	             
	             // Save the changes to the existing Excel file
	             FileOutputStream fos = new FileOutputStream(filePath);
	             workbook.write(fos);
	             
	             // Close streams
	             fos.close();
	             fis.close();
	             
	             System.out.println("Data appended successfully!");
	         } catch (FileNotFoundException e) {
	             e.printStackTrace();
	         } catch (IOException e) {
	             e.printStackTrace();
	    
	    }
	         return true;	    
	    
	    
	    
	    
	    
	}
	    }

