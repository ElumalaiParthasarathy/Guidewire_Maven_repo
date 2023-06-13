package excelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PolicyNumToExcel {
			public static void policyNum(String filePath,String sheetName,String policyNumber) {
				   try {
			            FileInputStream fis = new FileInputStream(new File(filePath));
			            XSSFWorkbook workbook = new XSSFWorkbook(fis);

			            // Get the sheet you want to append data to
			            XSSFSheet sheet = workbook.getSheet(sheetName);

			            // Determine the last row index
			            int lastRowIndex = sheet.getLastRowNum();

			            // Determine the last entered cell index in the same column
			            int lastCellIndex = 0; // Assuming the first column (column index 0)
			            Row lastRow = sheet.getRow(lastRowIndex);
			            if (lastRow != null) {
			                lastCellIndex = lastRow.getLastCellNum();
			            }

			            // Create a new row
			            Row newRow = sheet.createRow(lastRowIndex + 1);

			            // Create a new cell after the last entered cell
			            Cell newCell = newRow.createCell(lastCellIndex);

			            // Set the value of the new cell
			            newCell.setCellValue(policyNumber);

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
			    }
			}

