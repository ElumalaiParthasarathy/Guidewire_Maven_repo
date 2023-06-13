package pageObject;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GWPolicyFlatCancelTestCaseObjs {
		public static void   policySearchBar(WebDriver driver,String policyNumber) throws Exception {
			driver.findElement(By.xpath("(//div[@class='gw-action--expand-button'])[3]")).click();	
			WebElement searchBar =driver.findElement(By.xpath("//input[@name='TabBar-PolicyTab-PolicyTab_PolicyRetrievalItem']"));
			searchBar.sendKeys(policyNumber);
			searchBar.sendKeys(Keys.ENTER);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		public static void cancelButton(WebDriver driver) {
			driver.findElement(By.xpath("//div[text()='New Transaction']")).click();
			driver.findElement(By.xpath("(//div[@class='gw-subMenu gw-open'])[3]"));
			driver.findElement(By.xpath("//div[text()='Cancel Policy']")).click();
		}
		
		public static void sourceSelect(WebDriver driver,String option) {
			Select s = new Select(driver.findElement(By.xpath("//select[@name='StartCancellation-StartCancellationScreen-CancelPolicyDV-Source']")));
			s.selectByVisibleText(option);
		}
		public static void selectReason(WebDriver driver,String option) throws Exception {
		try {
			Select s = new Select(driver.findElement(By.xpath("//select[@name='StartCancellation-StartCancellationScreen-CancelPolicyDV-Reason']")));
			s.selectByVisibleText(option);
		}catch (Exception e) {
			System.out.println(e.getMessage());		}
		}
		
		public static void reasonDescription(WebDriver driver,String description) throws InterruptedException {
			
			WebElement descriptionSend = driver.findElement(By.xpath("//textarea[@name='StartCancellation-StartCancellationScreen-CancelPolicyDV-ReasonDescription']"));
			descriptionSend.sendKeys(description);
		}
		
		public static void startCancellationButton(WebDriver driver) {
			WebElement cancel = driver.findElement(By.xpath("//div[text()='Start Cancellation']"));
	        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        //boolean isClickable = wait.until(ExpectedConditions.elementToBeClickable(cancel));
	        //System.out.println(driver.findElement(By.xpath("//div[@class='gw-VerbatimWidget--inner']")).getText()); 
	        cancel.click();
			
		}
		public static void binOptions(WebDriver driver) throws InterruptedException {
			driver.findElement(By.xpath("(//div[@class='gw-action--inner'])[13]")).click();
			driver.findElement(By.xpath("//div[@aria-label='Cancel Now']")).click();
			Alert alert = driver.switchTo().alert();
			Thread.sleep(1000);
			alert.accept();
		}
		
}
