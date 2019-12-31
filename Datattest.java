package com.vzw.ccpa.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vzw.ccpa.page.VpdLandingpagePreview;
import com.vzw.pos.automation.utilities.TestDataProvider;

public class Datattest extends VpdLandingpagePreview {

	public Datattest(WebDriver driver, Hashtable<String, String> table) throws IOException {
		super(driver, table);
		// TODO Auto-generated constructor stub
	}

	// TODO Auto-generated constructor stub






	/*
	 * public static void writeExcelData(String filePath, List<String> lstResult,
	 * List<String> lstFailure, List<String> lstExecutionTime) { try {
	 * 
	 * // Object of Execl file FileInputStream fsIP = new FileInputStream(new
	 * File(â€ª"C://Users//qazisu//Desktop//XSSLT.xlsx"));
	 * 
	 * // Object of workbook XSSFWorkbook wb = new XSSFWorkbook(fsIP);
	 * 
	 * // Object of workbsheet XSSFSheet worksheet = wb.getSheetAt(0);
	 * 
	 * // code for formatting of the Excel cell CellStyle style =
	 * wb.createCellStyle(); style.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
	 * style.setWrapText(true);
	 * 
	 * //code to write the excel // Condition to clear the 'the Data column of Excel
	 * Report' if (lstResult == null) { for (int i = 1; i <
	 * worksheet.getPhysicalNumberOfRows(); i++) { XSSFRow row1 =
	 * worksheet.getRow(i);
	 * 
	 * for (int j = 1; j <= 3; j++) { row1.createCell(j + 1); XSSFCell cell1 =
	 * worksheet.getRow(i).getCell(j + 1); cell1.setCellValue("");
	 * cell1.setCellStyle(style); } } } else { for (int i = 0; i < lstResult.size();
	 * i++) { XSSFRow row1 = worksheet.getRow(i + 1); row1.createCell(2);
	 * row1.createCell(3); row1.createCell(4); XSSFCell cell1 = worksheet.getRow(i +
	 * 1).getCell(2); XSSFCell cell2 = worksheet.getRow(i + 1).getCell(3); XSSFCell
	 * cell3 = worksheet.getRow(i + 1).getCell(4);
	 * cell1.setCellValue(lstResult.get(i)); cell2.setCellValue(lstFailure.get(i));
	 * cell3.setCellValue(lstExecutionTime.get(i)); cell1.setCellStyle(style);
	 * cell2.setCellStyle(style); cell3.setCellStyle(style); } }
	 * 
	 * // Close the input file fsIP.close();
	 * 
	 * // Making new file FileOutputStream output_file = new FileOutputStream(new
	 * File(filePath)); wb.write(output_file);
	 * 
	 * // Close the output file output_file.close(); } catch (Exception ex) { //
	 * Exception Handling System.out.println("Exception occurred :-> " +
	 * ex.getMessage()); ex.printStackTrace(); } }
	 */
	static String LocSummaryRes;

	public static void CreateResultsSummary(String s) throws IOException {

		// Blank workbook

		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet

		XSSFSheet sheet = workbook.createSheet("Results");

		//sheet.createRow(0);
		//System.out.println("ROW DATA: "+s);
		//String[] col = s.split("\\,");//{ "Name","Email address", "Address","Can_be_reached","Government identifiers","Social security number" };
		//System.out.println("COL _LEN: "+col.length);

		//Font headerFont = workbook.createFont();

		//headerFont.setBold(true);

		//headerFont.setFontHeightInPoints((short) 12);

		//headerFont.setColor(IndexedColors.DARK_GREEN.getIndex());

		// Create a CellStyle with the font

		//CellStyle headerCellStyle = workbook.createCellStyle();

		//headerCellStyle.setFont(headerFont);

		// Create a Row

		//Row headerRow = sheet.createRow(0);

		// Create cells
/*
		for (int i = 0; i < col.length; i++) {

			Cell cell = headerRow.createCell(i);

			cell.setCellValue(col[i]);
			System.out.println("|"+col[i]+"|");

			cell.setCellStyle(headerCellStyle);

		}
*/
		DateFormat dateFormat = new SimpleDateFormat("MMdd_HHmmss");

		Date date = new Date();

		String timeStamp = dateFormat.format(date);


		LocSummaryRes ="C:\\Users\\sankaso\\Desktop\\"+ timeStamp +"_Testoutput.XLSX"; // give your location
		// here.



		try {

			// this Writes the workbook Output
			File tempFile = new File("C:\\Users\\sankaso\\Desktop\\Testoutput.XLSX");
			boolean exists = tempFile.exists();
			FileOutputStream out = new FileOutputStream(new File(LocSummaryRes));


			workbook.write(out);

			out.close();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			workbook.close();

		}

	}

	public static void updateResultsSummary(String Name,String Email,String Address,String Can_be_reached,String Government_identifiers,String Social_security_number ) throws IOException {

		//String[] values = { testCaseName, URL, StateName, ActualImage, Status, Comments };
		String[] values = { Name,Email,Address,Can_be_reached,Government_identifiers,Social_security_number };

		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(LocSummaryRes));

		try {

			//           XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(LocSummaryRes));

			XSSFSheet sheet = wb.getSheet("Results");

			int rCount = sheet.getLastRowNum() + 1;

			sheet.createRow(rCount);

			XSSFRow row = sheet.getRow(rCount);

			for (int i = 0; i < values.length; i++) {

				Cell cell = row.createCell(i);

				cell.setCellValue(values[i]);

			}

			try {

				// this Writes the workbook Output

				FileOutputStream out = new FileOutputStream(new File(LocSummaryRes));

				wb.write(out);

				out.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			wb.close();

		}

	}




	public static void updateResultsSummary2(String r ) throws IOException {

		//String[] values = { testCaseName, URL, StateName, ActualImage, Status, Comments };
		System.out.println(r);
		String[] values = r.split("\\,");//{ Name,Email,Address,Can_be_reached,Government_identifiers,Social_security_number };
		System.out.println(values.length);
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(LocSummaryRes));

		try {

			//           XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(LocSummaryRes));

			XSSFSheet sheet = wb.getSheet("Results");

			int rCount = sheet.getLastRowNum() + 1;

			sheet.createRow(rCount);

			XSSFRow row = sheet.getRow(rCount);

			for (int i = 0; i < values.length; i++) {

				Cell cell = row.createCell(i);

				cell.setCellValue(values[i]);
				System.out.println("|"+values[i]+"|");
			}

			try {

				// this Writes the workbook Output

				FileOutputStream out = new FileOutputStream(new File(LocSummaryRes));

				wb.write(out);

				out.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			wb.close();

		}

	}


	public static void createHeader(String r ) throws IOException {

		//String[] values = { testCaseName, URL, StateName, ActualImage, Status, Comments };
		System.out.println(r);
		String[] values = r.split("\\,");//{ Name,Email,Address,Can_be_reached,Government_identifiers,Social_security_number };
		System.out.println(values.length);
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(LocSummaryRes));

		try {

			//           XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(LocSummaryRes));

			XSSFSheet sheet = wb.getSheet("Results");

			int rCount = sheet.getLastRowNum() + 1;

			sheet.createRow(rCount);

			XSSFRow row = sheet.getRow(rCount);
			
			//setting header style
			Font headerFont = wb.createFont();

			headerFont.setBold(true);

			headerFont.setFontHeightInPoints((short) 12);

			headerFont.setColor(IndexedColors.DARK_GREEN.getIndex());

			// Create a CellStyle with the font

			CellStyle headerCellStyle = wb.createCellStyle();

			headerCellStyle.setFont(headerFont);

			for (int i = 0; i < values.length; i++) {

				Cell cell = row.createCell(i);

				cell.setCellValue(values[i]);
				cell.setCellStyle(headerCellStyle);
				System.out.println("|"+values[i]+"|");
			}

			try {

				// this Writes the workbook Output

				FileOutputStream out = new FileOutputStream(new File(LocSummaryRes));

				wb.write(out);

				out.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			wb.close();

		}

	}



}






































