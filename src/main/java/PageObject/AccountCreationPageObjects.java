package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountCreationPageObjects {
	public static void newAccountButton(WebDriver driver) {
		driver.findElement(By.xpath("(//div[@class='gw-icon gw-icon--expand'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='TabBar-AccountTab-AccountTab_NewAccount']")).click();
	}
	public static WebElement firstName(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@name='NewAccount-NewAccountScreen-NewAccountSearchDV-GlobalPersonNameInputSet-FirstName']"));
	}
	public static WebElement lastName(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@name='NewAccount-NewAccountScreen-NewAccountSearchDV-GlobalPersonNameInputSet-LastName']"));
	}
	public static void searchbutton(WebDriver driver) {
		driver.findElement(By.xpath("//div[@id='NewAccount-NewAccountScreen-NewAccountSearchDV-SearchAndResetInputSet-SearchLinksInputSet-Search']")).click();
	}
}
