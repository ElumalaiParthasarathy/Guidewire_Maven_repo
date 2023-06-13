package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.AccountCreationPageObjects;
import pageObject.CreateAccountPageObjects;
import pageObject.LoginPageObjects;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class AccountCreationPageCases {
	 public static org.apache.logging.log4j.Logger log =  LogManager.getLogger();
	@DataProvider
	public static Object[][] d1( ) throws Exception {
		FileInputStream file = new FileInputStream("config.properties");
		Properties properties =  new Properties();
		properties.load(file);
		String filePath = (String) properties.get("testdatapath");
		Object[][] arrObj = getExcelData(filePath, "AccountSearchTestData");
		return arrObj;

	}

	public static String[][] getExcelData(String fileName, String sheetName) throws IOException {
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
					data[i - 1][j] = cell.getStringCellValue();
				}
			}
		} catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		return data;
	}
	@DataProvider
	public static Object[][] name( ) throws Exception {
		FileInputStream file = new FileInputStream("config.properties");
		Properties properties =  new Properties();
		properties.load(file);
		String filePath = (String) properties.get("testdatapath");
		Object[][] arrObj = getExcelDatas(filePath, "AccountCreationTestData");
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
			Cell cell,cell1;
			data = new String[noOfRows - 1][noOfCols];

			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {
					row = sheet.getRow(i);
					cell = row.getCell(j);
					data[i - 1][j] = cell.getStringCellValue();
				}
			}
		} catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		return data;
	}



		@Test(dataProvider = "d1")
		public void searchFunctionCase(String firstName,String lastName) throws Exception {
			
			WebDriver driver = null ;
			
			PropertyConfigurator.configure("C:\\Users\\Tringapps\\Downloads\\Guidewire_Maven_repo\\test-output\\logs.properties");
			//reading the properties file
			FileInputStream file = new FileInputStream("config.properties");
			Properties properties =  new Properties();
			properties.load(file);
			String browser = (String) properties.get("browser");
			String url = (String) properties.get("url");

			if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.info("--------Opening Chorme Browser-------");
			}
			else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Opening Firefox");
			}



			driver.get(url);
			log.info("Visited Target website");
			Thread.sleep(3000);
			driver.manage().window().maximize();
			LoginPageObjects.userName(driver).sendKeys("su");
			log.info("Enter username");
			LoginPageObjects.password(driver).sendKeys("gw");
			log.info("Enter password");
			LoginPageObjects.loginButton(driver).click();
			Thread.sleep(3000);
			log.info("Login successful");
			AccountCreationPageObjects.newAccountButton(driver);
			Thread.sleep(3000);
			WebElement fName = AccountCreationPageObjects.firstName(driver);
			WebElement lName = AccountCreationPageObjects.lastName(driver);
			fName.sendKeys(firstName);
			lName.sendKeys(lastName);
			AccountCreationPageObjects.searchbutton(driver);
			Thread.sleep(3000);
			WebElement searchResult = driver.findElement(By.xpath("//div[@id='NewAccount-NewAccountScreen-NewAccountSearchResultsLV-0-Name']"));
			Thread.sleep(3000);
			String r = searchResult.getText();
			Assert.assertEquals(firstName+" "+lastName,r);
			if (r.equals("Dora H")) {
				log.info("Account name Match Found");
			}
			else {
				
			}
			driver.quit();
			log.info("------------------- Browser closed!! & Tear down -----------------");
			


		}



//	@Test(dataProvider = "name")
//	public void createAccountWithAllMandatory(String firstName1,String lastName1,String firstName,String lastName,String address1,String city, String state,String zipcode,String addressType,String organization,String producerCode) throws Exception {
//
//		Logger log = Logger.getLogger(AccountCreationPageCases.class);
//		WebDriver driver2 = null ;
//		PropertyConfigurator.configure("C:\\Users\\Tringapps\\Downloads\\Guidewire_Maven_repo\\test-output\\logs.properties");
//		//reading the properties file
//		FileInputStream file = new FileInputStream("config.properties");
//		Properties properties =  new Properties();
//		properties.load(file);
//		String browser = (String) properties.get("browser");
//		String url = (String) properties.get("url");
//
//		if(browser.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver2 = new ChromeDriver();
//		}
//		else if (browser.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			driver2 = new FirefoxDriver();
//		}
//
////visiting the website
//		driver2.get(url);
//		driver2.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
//		driver2.manage().window().maximize();
////entering crendentials
//		LoginPageObjects.userName(driver2).sendKeys("su");
//		LoginPageObjects.password(driver2).sendKeys("gw");
//		LoginPageObjects.loginButton(driver2).click();
//		driver2.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
////clicking new account option
//		AccountCreationPageObjects.newAccountButton(driver2);
//		driver2.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
////searching account
//		WebElement fiName = AccountCreationPageObjects.firstName(driver2);
//		WebElement laName = AccountCreationPageObjects.lastName(driver2);
//		fiName.sendKeys(firstName1);
//		laName.sendKeys(lastName1);
//		driver2.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
////clicking the search button
//		AccountCreationPageObjects.searchbutton(driver2);
//		Thread.sleep(5000);
//		driver2.findElement(By.xpath("//div[@class='gw-ToolbarButtonWidget gw-styleTag--ToolbarWidget gw-putSubMenusBelow gw-isTopLevelMenu gw-hasChildren gw-hasMinimizedView gw-action--outer']")).click();
//		driver2.findElement(By.xpath("//div[text()='Person']")).click();
////Accessing the address filed
//		WebElement addressLine1 = CreateAccountPageObjects.addressLine1(driver2);
//		addressLine1.sendKeys(address1);
////Accessing the state filed
//		driver2.findElement(By.xpath("//select[@name='CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-State']")).click();
//		driver2.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
//		Select select = new Select(driver2.findElement(By.xpath("//select[@class='gw-focus']")));
//		select.selectByVisibleText(state);
////Accessing the zipcode filed
//		WebElement zipCodePut = CreateAccountPageObjects.zipCode(driver2);
//		zipCodePut.sendKeys(zipcode);
////Accessing the Address type filed
//		Thread.sleep(3000);
//		driver2.findElement(By.xpath("(//select[@aria-required='true'])[2]")).click();
//		Select  s = new Select(driver2.findElement(By.xpath("//select[@class='gw-focus']")));
//		s.selectByVisibleText(addressType);
////accessing the organistation field
//		WebElement selectOragnistationList = driver2.findElement(By.xpath("//input[@class='gw-picker-input']"));
//		selectOragnistationList.sendKeys(organization);
////accessing the producer code
//		Thread.sleep(8000);
//		driver2.findElement(By.xpath("//select[@name='CreateAccount-CreateAccountScreen-CreateAccountDV-ProducerSelectionInputSet-ProducerCode']")).click();
//		Select  s1 = new Select(driver2.findElement(By.xpath("//select[@class='gw-focus']")));
//		s1.selectByVisibleText(producerCode);
//
//		
//		driver2.quit();
//
//	}





}
