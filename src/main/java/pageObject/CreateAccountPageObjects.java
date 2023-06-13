package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPageObjects {
	public static WebElement firstName(WebDriver driver) {
		WebElement fName = driver.findElement(By.xpath("//input[@name='CreateAccount-CreateAccountScreen-CreateAccountDV-CreateAccountContactInputSet-GlobalPersonNameInputSet-LastName']"));
		return fName;
	}
	public static WebElement lastName(WebDriver driver) {
		WebElement lName = driver.findElement(By.xpath("//input[@name='CreateAccount-CreateAccountScreen-CreateAccountDV-CreateAccountContactInputSet-GlobalPersonNameInputSet-LastName']"));
		return lName;
	}
	public static WebElement addressLine1(WebDriver driver) {
		WebElement address1 = driver.findElement(By.xpath("//*[@id=\"CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-AddressLine1\"]/div/input"));
		return address1;
	}
	public static WebElement selectCity(WebDriver driver) {
		WebElement city = driver.findElement(By.xpath("//*[@id=\"CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-City\"]/div[1]/input"));
		return city;
	}
	
	public static WebElement zipCode(WebDriver  driver) {
		WebElement zipcode = driver.findElement(By.xpath("//*[@id=\"CreateAccount-CreateAccountScreen-CreateAccountDV-AddressInputSet-globalAddressContainer-GlobalAddressInputSet-PostalCode\"]/div[1]/input"));
		return zipcode;
		
	}
	
	


}
