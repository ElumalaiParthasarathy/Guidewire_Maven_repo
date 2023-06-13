package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObjects {
	public static WebElement userName(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@name='Login-LoginScreen-LoginDV-username']"));
	}
	public static WebElement password(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@name='Login-LoginScreen-LoginDV-password']"));
	}
	public static WebElement loginButton(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class='gw-action--inner gw-hasDivider']"));
	}
}
