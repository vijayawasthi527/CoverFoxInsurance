package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import coverFoxBase.Base;
//import coverFoxPOM.CoverFoxAddressDetailsPage;
//import coverFoxUsingTestNg.CoverFoxHealthPlanPage;
//import coverFoxUsingTestNg.CoverFoxHealthPlanResultsPage;
//import coverFoxUsingTestNg.CoverFoxHomePage;
//import coverFoxUsingTestNg.CoverFoxMemberDetailsPage;
//import coverFoxUtility.Utility;
//import coverFox_With_Datadriven.CoverFox_HomePage;
import coverFoxPom.*;
import coverFoxUtility.*;


@Listeners(listners.ListenerCoverFox.class)

public class CF_TC555_Validate_search_results_for_healthcare_policies extends Base
{
	public static Logger logger;
	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxAddressDetailsPage addressDetails;
	CoverFoxMemberDetailsPage memberDetails;
	CoverFoxHealthPlanResultsPage result;
	
	
  @BeforeClass
  public void launchBrowser() throws InterruptedException 
  {
	  logger=logger.getLogger("CoverFoxInsurance");
	  PropertyConfigurator.configure("log4j.properties");
	  
	  
	  launchCoverFox();
	  logger.info("Launching CoverFox");
	  home=new CoverFoxHomePage(driver);
	  healthPlan=new CoverFoxHealthPlanPage(driver);
	  addressDetails=new CoverFoxAddressDetailsPage(driver);
	  memberDetails=new CoverFoxMemberDetailsPage(driver);
	  result=new CoverFoxHealthPlanResultsPage(driver);
	  
	  
  }
  
  @BeforeMethod
  public void enterMemberDetails() throws InterruptedException, EncryptedDocumentException, IOException
  {
	  Reporter.log("Click On Gender Button",true);
	  home.clickOnMaleButton();
	  logger.info("Clicking on male button");
	  Thread.sleep(1000);
	  
	  Reporter.log("click on next button",true);
	  healthPlan.clickOnNextButton();
	  logger.info("click on next button");
	  Thread.sleep(1000);
	  
	  Reporter.log("Handling age dropdown",true);
	  logger.info("Handling age dropdown");
	  memberDetails.handleAgedropdown(Utility.readDataFromExcel(0,0));
	  Reporter.log("Clicking on next button",true);
	  logger.info("Clicking on next button");
	  memberDetails.clickOnNextButton();
	  Thread.sleep(1000);
	  
	  Reporter.log("Entering PinCode",true);
	  logger.info("Entering PinCode");
	  addressDetails.enterPincode(Utility.readDataFromExcel(0,1));
	  Reporter.log("Entering Mobile Number",true);
	  addressDetails.enterMobileNumber(Utility.readDataFromExcel(0,2));
	  Reporter.log("Clicking on continue Button",true);
	  addressDetails.clickOnNextButton();
	  Thread.sleep(1000);
	  
  }
  @Test
  public void validateTestPlansFromTextAndBanners() throws InterruptedException, IOException
  {
	  Thread.sleep(5000);
	  Reporter.log("Fetching number of results from text ", true);
	  int textResult = result.avilablePlanNumberFromText();
	  Thread.sleep(7000);
	  Reporter.log("Fetching number of results from Banners ", true);
	  int bannerResult = result.availablePlanNumberFromBanners();
	  Thread.sleep(1000);
	  Assert.assertEquals(textResult, bannerResult,"Text results are matching with Banner results, TC is failed");
	  Reporter.log("TC is passed ", true);
	  //Utility.takeScreenShot(driver,"CF_TC555");
  }
  
  @AfterMethod
	public void closeBrowser() throws InterruptedException
	{
	Reporter.log("Closing browser ", true);
	Thread.sleep(7000);
	driver.close();
	}
  
  
}
