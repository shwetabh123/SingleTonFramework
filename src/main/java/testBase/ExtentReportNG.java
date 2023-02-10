package testBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import reusableComponents.PropertiesOperations;

/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
public class ExtentReportNG {

	static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter = null;

	public static ExtentReports setupExtentReport() throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);

		String reportPath = System.getProperty("user.dir") + "/Reports/ExecutionReport_" + actualDate + ".html";

		htmlReporter = new ExtentHtmlReporter(reportPath);

		// ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);

		extent = new ExtentReports();
		// extent.attachReporter(sparkReport);
		extent.attachReporter(htmlReporter);

		/*
		 * sparkReport.config().setDocumentTitle("DocumentTitle");
		 * sparkReport.config().setTheme(Theme.DARK);
		 * sparkReport.config().setReportName("ReportName");
		 */

		htmlReporter.config().setDocumentTitle("DocumentTitle");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setReportName("ReportName");

		extent.setSystemInfo("Executed on Environment: ", PropertiesOperations.getPropertyValueByKey("url"));
		extent.setSystemInfo("Executed on Browser: ", PropertiesOperations.getPropertyValueByKey("browser"));
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));

		return extent;
	}

}
