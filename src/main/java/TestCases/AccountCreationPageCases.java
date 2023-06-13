package TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.AccountCreationPageObjects;
import PageObject.LoginPageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AccountCreationPageCases {
		
			@Test(dataProvider = "search Data", dataProviderClass = ExcelDataProvider.class)
			public void searchFunctionCase(String firstName,String lastName) throws Exception {
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
			driver.get(url);
			Thread.sleep(3000);
			driver.manage().window().maximize();
			LoginPageObjects.userName(driver).sendKeys("su");		
			LoginPageObjects.password(driver).sendKeys("gw");
			LoginPageObjects.loginButton(driver).click();
			Thread.sleep(3000);
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
				System.out.println("Match Found");
			}else {
				System.out.println("Match Not Found");
			}
			driver.quit();
			
			
			}
}
