package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PolicyCreationObjects {
	public static void newSubsmissionButton(WebDriver driver) {
		
		driver.findElement(By.xpath("(//div[@class='gw-action--expand-button'])[3]")).click();	
		driver.findElement(By.xpath("//div[text()='New Submission']")).click();
	}
	public static void accountNumberField(WebDriver driver,String Accnum) {
		driver.findElement(By.xpath("//input[@name='NewSubmission-NewSubmissionScreen-SelectAccountAndProducerDV-Account']")).sendKeys(Accnum);
		driver.findElement(By.xpath("(//span[@class='gw-icon'])[7]")).click();
	}
	public static void organization(WebDriver driver,String organization) {
		driver.findElement(By.xpath("//input[@name='NewSubmission-NewSubmissionScreen-SelectAccountAndProducerDV-ProducerSelectionInputSet-Producer']")).sendKeys(organization);
		driver.findElement(By.xpath("(//span[@class='gw-icon'])[8]")).click();
	}
	public static void procducerCode(WebDriver driver, String producerCode) {
		driver.findElement(By.xpath("//select[@name='NewSubmission-NewSubmissionScreen-SelectAccountAndProducerDV-ProducerSelectionInputSet-ProducerCode']")).click();
		Select  s = new Select(driver.findElement(By.xpath("//select[@class='gw-focus']")));
		s.selectByVisibleText(producerCode);
	}
	public static void nextButton(WebDriver driver) {
		driver.findElement(By.xpath("//div[text()='Next']")).click();
	}
	public static void addTravelers (WebDriver driver,String travellerName,String age, String relationship) throws InterruptedException {
	//add travellers button
		driver.findElement(By.xpath("//div[@aria-label='Add Travellers Details']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name=\"SubmissionWizard-LOBWizardStepGroup-LineWizardStepSet-APDRiskLine0WizardStepGroup-APDRiskScreen-APDRiskCoverablePanelSet-APDRiskPanelSet-Exposures-0-APDRiskExposureLV-riskExposureLV-0-exposureField-0-value-APDDataFieldValue-value\"]")).sendKeys(travellerName);
		driver.findElement(By.xpath("//select[@name='SubmissionWizard-LOBWizardStepGroup-LineWizardStepSet-APDRiskLine0WizardStepGroup-APDRiskScreen-APDRiskCoverablePanelSet-APDRiskPanelSet-Exposures-0-APDRiskExposureLV-riskExposureLV-0-exposureField-1-value-APDDataFieldValue-DropDownCode']")).click();
		Select s =  new Select(driver.findElement(By.xpath("//select[@class='gw-focus']")));
		s.selectByVisibleText(age);
		driver.findElement(By.xpath("//select[@name='SubmissionWizard-LOBWizardStepGroup-LineWizardStepSet-APDRiskLine0WizardStepGroup-APDRiskScreen-APDRiskCoverablePanelSet-APDRiskPanelSet-Exposures-0-APDRiskExposureLV-riskExposureLV-0-exposureField-2-value-APDDataFieldValue-DropDownCode']")).click();
		Select s1 = new Select(driver.findElement(By.xpath("//select[@class='gw-focus']")));
		s1.selectByVisibleText(relationship);
	}
	public static void permiumAmountField(WebDriver driver,String Amount) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='SubmissionWizard-LOBWizardStepGroup-LineWizardStepSet-APDRiskLine0WizardStepGroup-APDPricingScreen-APDRiskPricingPanelSet-PricingIterator-0-Rate']")).sendKeys(Amount);
	}
	
	
	public static void clickOn(WebDriver driver1, WebElement element,   
			Duration timeout)  
			{  
			new WebDriverWait(driver1,   
			timeout).until(ExpectedConditions.visibilityOf(element));  
			element.click();  
			}  
}
