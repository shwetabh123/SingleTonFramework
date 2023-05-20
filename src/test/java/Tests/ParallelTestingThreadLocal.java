package Tests;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import reusableComponents.ActionEngine;
import reusableComponents.PropertiesOperations;
import testBase.BaseTest;
import testBase.BrowserFactory;
import testBase.DriverFactory;
import testBase.ExtentFactory;
public class ParallelTestingThreadLocal extends BaseTest {
	BrowserFactory bf = new BrowserFactory();
	ActionEngine ae=new ActionEngine();
	public static String appURL1 = "https://global.hitachi-solutions.com/";

	@Test
	public void navigatetosite(Method m) throws InterruptedException, MalformedURLException {
		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");
		System.out.println(m.getName() + " of class ParallelTestingThreadLocal Executed by Thread "
				+ Thread.currentThread().getId() + " on" + " driver reference "
				+ DriverFactory.getInstance().getDriver());
		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(appURL1);
		System.out.println("Title printed by Thread " + Thread.currentThread().getId() + " - "
				+ DriverFactory.getInstance().getDriver().getTitle() + " on driver" + " reference "
				+ DriverFactory.getInstance().getDriver());
		// childTestnew.get().log(Status.INFO, "Type in Google search ");
		//  childTestnew.log(Status.INFO, "Type in Google search ");
		 // childTestnew.log(Status.INFO, "Running" + m.getName());
		//  childTestnew.assignCategory("smoke testing "); 
		childTestnew.get().log(Status.INFO,"Title is  " +DriverFactory.getInstance().getDriver().getTitle());
		childTestnew.get().log(Status.INFO,"Logged into  " + url);
	}
	@Test
	public void searchforkeywords(Method m) throws InterruptedException, MalformedURLException {
		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");
		System.out.println(m.getName() + " of class ParallelTestingThreadLocal Executed by Thread "
				+ Thread.currentThread().getId() + " on" + " driver reference "
				+ DriverFactory.getInstance().getDriver());
		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(appURL1);
		Thread.sleep(5000);
		WebElement e1=DriverFactory.getInstance().getDriver().findElement(By.xpath(PropertiesOperations.getPropertyValueByKey("search")));
		e1.click();
		childTestnew.get().log(Status.INFO,"Clicked search ");

		Thread.sleep(5000);
		WebElement e2=DriverFactory.getInstance().getDriver().findElement(By.xpath(PropertiesOperations.getPropertyValueByKey("typeinsearch")));
		e2.sendKeys("Finance");
		childTestnew.get().log(Status.INFO,"Type in search box ");
		WebElement e3=DriverFactory.getInstance().getDriver().findElement(By.xpath(PropertiesOperations.getPropertyValueByKey("searchbtn")));
		e3.click();
		
		childTestnew.get().log(Status.INFO,"Click search btn ");
		
		System.out.println("Title printed by Thread " + Thread.currentThread().getId() + " - "
				+ DriverFactory.getInstance().getDriver().getTitle() + " on driver" + " reference "
				+ DriverFactory.getInstance().getDriver());

		childTestnew.get().log(Status.INFO,"Logged into  " + url);
	}
	
	@Test(dataProvider = "loginData")
	public void opensearchresults(Method m,String searchtext) throws InterruptedException, MalformedURLException {
		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");
		System.out.println(m.getName() + " of class ParallelTestingThreadLocal Executed by Thread "
				+ Thread.currentThread().getId() + " on" + " driver reference "
				+ DriverFactory.getInstance().getDriver());
		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(appURL1);
		Thread.sleep(5000);
		WebElement e1=DriverFactory.getInstance().getDriver().findElement(By.xpath(PropertiesOperations.getPropertyValueByKey("search")));
		e1.click();
		childTestnew.get().log(Status.INFO,"Clicked search ");
		Thread.sleep(5000);
		WebElement e2=DriverFactory.getInstance().getDriver().findElement(By.xpath(PropertiesOperations.getPropertyValueByKey("typeinsearch")));
		e2.sendKeys(searchtext);
		childTestnew.get().log(Status.INFO,"Type in search box ");
		WebElement e3=DriverFactory.getInstance().getDriver().findElement(By.xpath(PropertiesOperations.getPropertyValueByKey("searchbtn")));
		e3.click();
		childTestnew.get().log(Status.INFO,"Click search btn ");
		WebElement e4=DriverFactory.getInstance().getDriver().findElement(By.xpath(PropertiesOperations.getPropertyValueByKey("Link")));
		e4.click();
		childTestnew.get().log(Status.INFO,"Click Link");
		
		System.out.println("Title printed by Thread " + Thread.currentThread().getId() + " - "
				+ DriverFactory.getInstance().getDriver().getTitle() + " on driver" + " reference "
				+ DriverFactory.getInstance().getDriver());

	}
	
	@DataProvider(name="loginData")
	public String[][] getData() throws Exception {
		File excelFile = new File("./src/test/resources/Test.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int noOfRows = sheet.getPhysicalNumberOfRows();
		int noOfColumns = sheet.getRow(0).getLastCellNum();
	
		String[][] data = new String[noOfRows-1][noOfColumns];
		for (int i = 0; i < noOfRows-1; i++) {
			for (int j = 0; j < noOfColumns; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] =  df.formatCellValue(sheet.getRow(i+1).getCell(j));
			}
		}
		workbook.close();
		fis.close();
		
 		for (String[] dataArr : data) {
 			System.out.println(Arrays.toString(dataArr));
 		}
		return data;
	}
	
}