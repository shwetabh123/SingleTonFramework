package Tests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import reusableComponents.PropertiesOperations;
import testBase.BaseTest;
import testBase.BrowserFactory;
import testBase.DriverFactory;
import testBase.ExtentFactory;

public class ParallelTestingThreadLocal2 extends BaseTest {

	BrowserFactory bf = new BrowserFactory();

	public static String appURL1 = "https://www.google.com";
	public static String appURL2 = "https://www.facebook.com/";
	public static String appURL3 = "https://www.flipkart.com/";
	public static String appURL4 = "https://www.myntra.com/";

	@Test
	public void GoogleTest(Method m) throws InterruptedException, MalformedURLException {

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
		

		childTestnew.get().log(Status.INFO,"Title is  " +DriverFactory.getInstance().getDriver().getTitle());
		 
		childTestnew.get().log(Status.INFO,"Logged into  " + appURL1);
		/*
		 * 
		 * childTestnew.log(Status.INFO, "Type in Google search ");
		 * 
		 * childTestnew.log(Status.INFO, "Running" + m.getName());
		 * childTestnew.assignCategory("smoke testing "); childTestnew.log(Status.INFO,
		 * "Logged into  " + appURL1);
		 */

	}

	@Test
	public void FacebookTest(Method m) throws InterruptedException, MalformedURLException {

		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");

		System.out.println(m.getName() + " of class ParallelTestingThreadLocal Executed by Thread "
				+ Thread.currentThread().getId() + " on" + " driver reference "
				+ DriverFactory.getInstance().getDriver());

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));

		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(appURL2);
		

		childTestnew.get().log(Status.INFO,"Title is  " +DriverFactory.getInstance().getDriver().getTitle());
		 
		childTestnew.get().log(Status.INFO,"Logged into  " + appURL2);

		System.out.println("Title printed by Thread " + Thread.currentThread().getId() + " - "
				+ DriverFactory.getInstance().getDriver().getTitle() + " on driver" + " reference "
				+ DriverFactory.getInstance().getDriver());

	}

	@Test
	public void FlipkartTest(Method m) throws InterruptedException, MalformedURLException {

		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");

		System.out.println(m.getName() + " of class ParallelTestingThreadLocal Executed by Thread "
				+ Thread.currentThread().getId() + " on" + " driver reference "
				+ DriverFactory.getInstance().getDriver());

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));

		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(appURL3);

		

		childTestnew.get().log(Status.INFO,"Title is  " +DriverFactory.getInstance().getDriver().getTitle());
		 
		childTestnew.get().log(Status.INFO,"Logged into  " + appURL3);
		
		
		System.out.println("Title printed by Thread " + Thread.currentThread().getId() + " - "
				+ DriverFactory.getInstance().getDriver().getTitle() + " on driver" + " reference "
				+ DriverFactory.getInstance().getDriver());

	}

	@Test
	public void MyntraTest(Method m) throws InterruptedException, MalformedURLException {

		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");

		System.out.println(m.getName() + " of class ParallelTestingThreadLocal Executed by Thread "
				+ Thread.currentThread().getId() + " on" + " driver reference "
				+ DriverFactory.getInstance().getDriver());

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));

		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().navigate().to(appURL4);

		

		childTestnew.get().log(Status.INFO,"Title is  " +DriverFactory.getInstance().getDriver().getTitle());
		 
		childTestnew.get().log(Status.INFO,"Logged into  " + appURL4);
		
		System.out.println("Title printed by Thread " + Thread.currentThread().getId() + " - "
				+ DriverFactory.getInstance().getDriver().getTitle() + " on driver" + " reference "
				+ DriverFactory.getInstance().getDriver());

	}

}
