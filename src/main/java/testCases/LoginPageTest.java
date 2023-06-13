package testCases;

import static org.testng.Assert.assertEquals;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.LoginPageObjects;



public class LoginPageTest {
	
	
    private static final Logger log = Logger.getLogger(PolicyCreation.class);

	@Test(testName = "valid Login Case")
	public static void validLogin() throws Exception {
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
			log.info(" -------Chrome browser initiated------- ");
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info(" -------FireFox browser initiated------- ");

		}
		try{
			driver.get(url);
			Thread.sleep(3000);
			driver.manage().window().maximize();
			log.info(" -------Target Website ------- ");
			LoginPageObjects.userName(driver).sendKeys("su");	
			log.info(" -------Entering Username------- ");
			LoginPageObjects.password(driver).sendKeys("gw");
			log.info(" -------Entering Password------- ");
			LoginPageObjects.loginButton(driver).click();
			Thread.sleep(3000);
			String expectedTitle ="[DEV mode - 10.2.1.1711] Guidewire PolicyCenter (Super User) My Summary";
			Assert.assertEquals(expectedTitle,driver.getTitle());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			driver.quit();
		}
	}
	@Test(testName = "Invalid login case")
	public static void invalidLogin() throws Exception {
		WebDriver  driver = null;

		FileInputStream file = new FileInputStream("config.properties");
		Properties properties =  new Properties();
		properties.load(file);
		String browser = (String) properties.get("browser");
		String url = (String) properties.get("url");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		try {
			driver.get(url);
			Thread.sleep(3000);
			driver.manage().window().maximize();
			LoginPageObjects.userName(driver).sendKeys("de");		
			LoginPageObjects.password(driver).sendKeys("gw");
			LoginPageObjects.loginButton(driver).click();
			Thread.sleep(3000);
			String expectedTitle ="[DEV mode - 10.2.1.1711] Guidewire PolicyCenter (Super User) My Summary";
			Assert.assertEquals(expectedTitle,driver.getTitle());
		}
		catch (AssertionError e) {
			System.out.println(e.getMessage());
		}finally {
			driver.quit();
		}
	}


}
