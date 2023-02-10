package testBase;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import reusableComponents.PropertiesOperations;
import testBase.DriverFactory;
public class BaseTest {
	/*
	 * private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	 */
	/* BrowserFactory bf = new BrowserFactory(); */
	final static String workingDir = System.getProperty("user.dir");
	final static String filePath = "\\test-output\\MyReport.html";
	public static String path = workingDir + filePath;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	private static ExtentHtmlReporter htmlReporter = null;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> childTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> childTestnew = new ThreadLocal<ExtentTest>();
	@BeforeSuite
	public void beforeSuite() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		String reportPath = System.getProperty("user.dir") + "/Reports/ExecutionReport_" + actualDate + ".html";
		htmlReporter = new ExtentHtmlReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setReportName("Regression Testing");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setTheme(Theme.DARK);
	    htmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");
	    htmlReporter.config().setDocumentTitle("DocumentTitle");
		htmlReporter.config().setTheme(Theme.DARK);
		extent.setSystemInfo("Platform", "Windows");
		extent.setSystemInfo("Executed on Environment: ", PropertiesOperations.getPropertyValueByKey("url"));
		extent.setSystemInfo("Executed on Browser: ", PropertiesOperations.getPropertyValueByKey("browser"));
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
	}
	@BeforeTest
	public synchronized void beforeTest(XmlTest method) {
		ExtentTest tests = extent.createTest(method.getName());
		parentTest.set(tests);
	}
	@BeforeClass
	public synchronized void beforeClass(ITestContext result) {
		ExtentTest testclass = parentTest.get().createNode(getClass().getSimpleName());
		childTest.set(testclass);
	}
	@BeforeMethod
	/* @Parameters({ "browser" }) */
	public void setUp(Method method) throws MalformedURLException {
		ExtentTest testmethod = childTest.get().createNode(method.getName());
		childTestnew.set(testmethod);
		/*
		 * WebDriverManager.chromedriver().setup();
		 * 
		 * driver.set(new ChromeDriver());
		 */
	}
	/*
	 * public WebDriver getDriver() { return driver.get();
	 * 
	 * }
	 */
	public static String getScreenshot(RemoteWebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE)
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
			childTestnew.get().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
			childTestnew.get().fail(result.getThrowable());
			//add screenshot for failed test.
			File src = ((TakesScreenshot)DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
			Date date = new Date();
			String actualDate = format.format(date);
			String screenshotPath = System.getProperty("user.dir")+
					"/Reports/Screenshots/"+actualDate+".jpeg";
			File dest = new File(screenshotPath);
			try {
				FileUtils.copyFile(src, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
				ExtentFactory.getInstance().removeExtentObject();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (result.getStatus() == ITestResult.SKIP)
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Skipped.");
			childTestnew.get().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Skipped.");
			childTestnew.get().skip(result.getThrowable());
		}
		else if (result.getStatus() == ITestResult.SUCCESS)
		{
			childTestnew.get().pass("Test Passed");
		}
		extent.flush();
		DriverFactory.getInstance().closeBrowser();
	}
	@AfterSuite
	public void testDown() {
	}
	@AfterClass
	public void afterClass() {
		extent.flush();
		// driver.close();
	}
}