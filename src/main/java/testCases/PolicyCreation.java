package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import excelUtils.Excel;
import excelUtils.PolicyNumToExcel;

import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.LoginPageObjects;
import pageObject.PolicyCreationObjects;

public class PolicyCreation {
    private static final Logger log = Logger.getLogger(PolicyCreation.class);

	@DataProvider
	public static Object[][] policyCreationDataProvider( ) throws Exception {
		FileInputStream file = new FileInputStream("config.properties");
		Properties properties =  new Properties();
		properties.load(file);
		String filePath = (String) properties.get("testdatapath");
		Object[][] arrObj = getExcelDatas(filePath, "BasicPolicyCreationTestData");
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
	@Test(dataProvider = "policyCreationDataProvider")
	public void policyCreationCase(String userName,String pwd,String accountNum,String accountNamr,String Organistation,String producerCode,String travellerName,String ageRange,String relationship,String premium) throws IOException {
		PropertyConfigurator.configure("log4j2.properties");
		WebDriver driver = null ;
		FileInputStream file = new FileInputStream("config.properties");
		Properties properties =  new Properties();
		properties.load(file);
		String browser = (String) properties.get("browser");
		String url = (String) properties.get("url");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Chrome Browser initiated");
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Firefox Browser initiated");
		}
		try{
			driver.get(url);
			Thread.sleep(3000);
			driver.manage().window().maximize();
//login code
			LoginPageObjects.userName(driver).sendKeys(userName);	
			log.info("Entered the username");
			LoginPageObjects.password(driver).sendKeys(pwd);
			log.info("Entered the password");
			LoginPageObjects.loginButton(driver).click();
			log.info("Login Successfull");
			
			Thread.sleep(3000);
//Creating Policy
			PolicyCreationObjects.newSubsmissionButton(driver);
			log.info("policy submission page");
			Thread.sleep(3000);
//Asscessing policy fields
			PolicyCreationObjects.accountNumberField(driver, accountNum);
			Thread.sleep(3000);
//Verifying wheather correct account or not
			String text = driver.findElement(By.xpath("//div[@class='gw-ActionValueWidget']")).getText();
			if(text.equalsIgnoreCase(accountNamr)) {
				log.info(text +" Account Found ");
			}else {
				log.fatal("Account not Found");
			}
			Thread.sleep(3000);
//organistation field
			PolicyCreationObjects.organization(driver, Organistation);
			Thread.sleep(3000);
//producer code field
			PolicyCreationObjects.procducerCode(driver, producerCode);
//selecting the insurance product	
			Thread.sleep(3000);
			WebElement selectButton = driver.findElement(By.xpath("//div[text()='Select']"));
			selectButton.click();
//next Button
			Thread.sleep(3000);
			PolicyCreationObjects.nextButton(driver);
//adding travellers details		
			Thread.sleep(3000);
			log.info("Adding Travelers");
			PolicyCreationObjects.addTravelers(driver, travellerName, ageRange, relationship);
			PolicyCreationObjects.nextButton(driver);
			log.info("Setting Premium Amount");
			PolicyCreationObjects.permiumAmountField(driver, premium);
			PolicyCreationObjects.nextButton(driver);
			Thread.sleep(3000);
			PolicyCreationObjects.nextButton(driver);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@role='button'])[11]")).click();
			Thread.sleep(3000);
			PolicyCreationObjects.nextButton(driver);
			Thread.sleep(3000);
			PolicyCreationObjects.nextButton(driver);
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@role='button'])[15]")).click();
			driver.findElement(By.xpath("//div[text()='Issue Policy']")).click();
			Alert alert = driver.switchTo().alert();
			Thread.sleep(1000);
			alert.accept();
			Thread.sleep(1000);
			String policyNum = driver.findElement(By.xpath("(//div[@class='gw-label gw-infoValue'])[3]")).getText();
			log.info("Policy "+ policyNum +" created Sucessfully ");
			String check = driver.findElement(By.xpath("//div[@class='gw-VerbatimWidget--inner']")).getText();
			String expectedCheck = "Your Submission";
			boolean testResult= check.contains(expectedCheck);
			Assert.assertEquals(testResult, true);
			PolicyNumToExcel.policyNum("exceldata\\\\TestData.xlsx", "CreatedPolicyNumber", policyNum);
			Excel excel = new Excel("exceldata\\\\TestData.xlsx");
			excel.setCellData("GWFlatPolicyCancelTestData", 2, 1, policyNum);
			}catch (Exception e) {
			log.error("Error!! Look below");
			System.out.println(e.getMessage());
			}
		driver.quit();
	}
	
	
}
