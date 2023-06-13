package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import excelUtils.Excel;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.GWPolicyFlatCancelTestCaseObjs;
import pageObject.LoginPageObjects;

public class GWPolicyFlatCancelTestCase {
	private static final Logger log = Logger.getLogger(GWPolicyFlatCancelTestCase.class);
	public static WebDriver driver = null ;
//dataprovider
	@DataProvider
	public static Object[][] testData( ) throws Exception {
		FileInputStream file = new FileInputStream("config.properties");
		Properties properties =  new Properties();
		properties.load(file);
		String filePath = (String) properties.get("testdatapath");
		Object[][] arrObj = getExcelDatas(filePath, "GWFlatPolicyCancelTestData");
		return arrObj;

	}

	public static String[][] getExcelDatas(String fileName, String sheetName) throws IOException {
		String[][] data = null;
		try {

			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(0);
			int noOfRows = sheet.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			Cell cell;
			data = new String[noOfRows - 1][noOfCols];
			

			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {
					row = sheet.getRow(i);
					cell = row.getCell(j);  
					cell.setCellType(CellType.STRING); 
					data[i - 1][j] = cell.getStringCellValue();
				}
			}
		} catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		return data;
	}
@BeforeTest
public void initiateBrowser() throws Exception {
	PropertyConfigurator.configure("log4j2.properties");
	FileInputStream file = new FileInputStream("config.properties");
	Properties properties =  new Properties();
	properties.load(file);
	String browser = (String) properties.get("browser");
	String url = (String) properties.get("url");
	if(browser.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		log.info(" -------Chrome browser initiated------- ");
	}
	else if (browser.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		log.info(" -------FireFox browser initiated------- ");

	}
	driver.get(url);
	Thread.sleep(3000);
}

@Test(testName = "GW_Policy_Flat Cancel_Test Case",dataProvider = "testData")
public static void validLogin(String userName,String passWord,String policyNum,String source,String reason,String description) throws Exception {
	
	try{
		driver.manage().window().maximize();
		log.info(" -------Target Website ------- ");
		LoginPageObjects.userName(driver).sendKeys(userName);	
		log.info(" -------Entering Username------- ");
		LoginPageObjects.password(driver).sendKeys(passWord);
		log.info(" -------Entering Password------- ");
		LoginPageObjects.loginButton(driver).click();
		Thread.sleep(3000);
		GWPolicyFlatCancelTestCaseObjs.policySearchBar(driver, policyNum);
		log.info("Entered policy number in search bar");
		GWPolicyFlatCancelTestCaseObjs.cancelButton(driver);
		GWPolicyFlatCancelTestCaseObjs.sourceSelect(driver, source);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		GWPolicyFlatCancelTestCaseObjs.selectReason(driver, reason);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
        GWPolicyFlatCancelTestCaseObjs.reasonDescription(driver, description);
        Thread.sleep(2000);
        GWPolicyFlatCancelTestCaseObjs.startCancellationButton(driver);
        Thread.sleep(4000);
        GWPolicyFlatCancelTestCaseObjs.binOptions(driver);
        String confirmation = driver.findElement(By.xpath("//div[@class='gw-VerbatimWidget--inner']")).getText();
        boolean contains = confirmation.contains("Your Cancellation");
        Excel excel = new  Excel("exceldata\\TestData.xlsx");
        excel.setCellData("GWFlatPolicyCancelTestData", 6, 1, confirmation);
        excel.setCellData("GWFlatPolicyCancelTestData", 7, 1, contains==true ? "PASS":"FAIL" );
        Assert.assertEquals(contains, true);
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}finally {
		driver.quit();
	}
}


















}