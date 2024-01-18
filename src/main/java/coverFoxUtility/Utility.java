package coverFoxUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
//import org.openxmlformats.schemas.drawingml.x2006.diagram.CTOtherwise;
import org.testng.Reporter;
import org.testng.*;

public class Utility 
	{

		public static String readDataFromExcel(int row,int cell) throws EncryptedDocumentException, IOException
		{
			Reporter.log("Reading data from excelsheet",true);
			FileInputStream myFile=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\NewExcelFileForTesting.xlsx");
			Sheet mySheet = WorkbookFactory.create(myFile).getSheet("Sheet6");
			String myData=mySheet.getRow(row).getCell(cell).getStringCellValue();
			return myData;
		
		}
		
		public static void takeScreenShot(WebDriver driver,String TCID) throws IOException
		{
			Reporter.log("Taking ScreenShot",true);
			String timeStamp=new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File dest=new File("C:\\Users\\vijay\\Desktop\\Class Notes\\Practice Work\\coverFox"+TCID+"_"+timeStamp+".png");
			Reporter.log("Saved ScreenShot at"+dest,true);
			FileHandler.copy(src, dest);
		}
		
		public static String propertyFileData(String key) throws InterruptedException, IOException
		{
			Properties prop=new Properties();
			Thread.sleep(1000);
			
			FileInputStream myFile=new FileInputStream(System.getProperty("user.dir")+"\\CoverFoxData.Properties");
			prop.load(myFile);
			Thread.sleep(1000);
			
			String value=prop.getProperty(key);
			return value;
		}
	
}
