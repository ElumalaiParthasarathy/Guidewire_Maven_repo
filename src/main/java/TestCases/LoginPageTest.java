package TestCases;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



import PageObject.LoginPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;



public class LoginPageTest {
	@Test(testName = "valid Login Case")
	public static void validLogin() throws Exception {
		WebDriver driver = null ;
		FileInputStream file = new FileInputStream("config.properties");
		Properties properties =  new Properties();
		properties.load(file);
		String browser = (String) properties.get("browser");
		String url = (String) properties.get("url");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		try{
			driver.get(url);
			Thread.sleep(3000);
			driver.manage().window().maximize();
			LoginPageObjects.userName(driver).sendKeys("su");		
			LoginPageObjects.password(driver).sendKeys("gw");
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
		}else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
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
		catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			driver.quit();
		}
	}


}
