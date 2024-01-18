package coverFoxPom;



import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoverFoxHealthPlanResultsPage 
{	
	
	@FindBy(xpath = "//div[contains(text(),'matching Health Insurance')]") private WebElement resultInString;
	@FindBy(id = "plans-list")private List<WebElement>planlist;
	@FindBy(className = "open-contact-form")private WebElement loginButton;
	
	public CoverFoxHealthPlanResultsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public int avilablePlanNumberFromText()
	{
		String test=resultInString.getText();
		//49 matching Health Insurance Plans
		
		String arr[]=test.split(" ");
		//ar[]={"49" "matching" "Health" "Insurance" "Plans"}
		
		String numberOfResultsInString=arr[0];
		
		int numberOfResultsInInt=Integer.parseInt(numberOfResultsInString);
		
		return numberOfResultsInInt;
	}
	
	public int availablePlanNumberFromBanners()
	{
		int totalNumberofPlans=planlist.size();
		return totalNumberofPlans;
	}
	
	public void checkLoginButton()
	{
		boolean loginButtonStatus = loginButton.isDisplayed();
		System.out.println(loginButtonStatus);
	}
	
	
	
	

}
