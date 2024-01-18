package coverFoxPom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoverFoxAddressDetailsPage 
{
	@FindBy(className = "mp-input-text")private WebElement pincodeField;
	@FindBy(xpath = "(//input[@type='number'])[2]")private WebElement mobileNumberField;
	@FindBy(xpath = "//div[text()='Continue']")private WebElement continueButton;
	
	public CoverFoxAddressDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public void enterPincode(String pincode)
	{
		pincodeField.sendKeys(pincode);
	}
	
	public void enterMobileNumber(String mobNumber)
	{
		mobileNumberField.sendKeys(mobNumber);
	}
	public void clickOnNextButton()
	{
		continueButton.click();
	}
	
	
	
	
}
