package rahul.raj.testUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahul.raj.helpers.ReportManagerSingleton;
import rahul.raj.helpers.SeleniumWrapper;
import rahul.raj.helpers.WebDriverManagerSingleton;

public class TestListener implements ITestListener {
	private ReportManagerSingleton reporterInstance=ReportManagerSingleton.getReporterInstance();
	private ExtentReports extent=reporterInstance.getExtentReports();
	private ThreadLocal<ExtentTest> testThread=new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		ExtentTest test=extent.createTest(result.getMethod().getMethodName());
		testThread.set(test);
	}
	
	public void onTestSuccess(ITestResult result) {
		testThread.get().log(Status.PASS, result.getMethod().getMethodName()+ " Passed!");
	}
	
	public void onTestFailure(ITestResult result) {
		testThread.get().log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
		
		// Capture Screenshot
		String destination=null;
		try {
			WebDriverManagerSingleton driverInstance=WebDriverManagerSingleton.getInstanceOfBrowser();
			WebDriver driver=driverInstance.getDriver();
			destination=SeleniumWrapper.takeScreenshot(driver, result.getMethod().getMethodName());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		testThread.get().addScreenCaptureFromPath(destination);
	}
	
	public void onFinish(ITestContext context) {
	    extent.flush();
	}
}
