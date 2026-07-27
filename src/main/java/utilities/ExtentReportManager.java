package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public  class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public  ExtentTest test;

	String repName;

	public void onTestStart(ITestResult result) {
		
		//Before each test case
		test = extent.createTest("Test");
		ExtentFactory.getInstance().setExtent(test);
	
	}

	public void onTestSuccess(ITestResult result) {
	    
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case:"+result.getMethod().getMethodName()+"is Passed");
		ExtentFactory.getInstance().removeExtentObject();
	}


	public void onTestFailure(ITestResult result) {
		
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case:"+result.getMethod().getMethodName()+"is Failed");
		ExtentFactory.getInstance().getExtent().log(Status.FAIL,result.getThrowable());
		
	}


	public void onTestSkipped(ITestResult result) {

		ExtentFactory.getInstance().getExtent().log(Status.SKIP,"Test Case:"+result.getThrowable().getMessage()+"is Skipped.");
		ExtentFactory.getInstance().removeExtentObject();

	}

	public void onStart(ITestContext context) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		repName = "Test-Report-"+timeStamp+".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);   //specify location of the report

		sparkReporter.config().setDocumentTitle("RestAssured Automation");   //Title of report
		sparkReporter.config().setReportName("Go-Rest API User Test");                     // Name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);		
		extent.setSystemInfo("Application", "RestAssured Automation");
		extent.setSystemInfo("Operating System",System.getProperty("os.name"));
		extent.setSystemInfo("User name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","suresh");
		
		test = extent.createTest("GoRest API User Test").assignAuthor("suresh").assignDevice("chrome");

	}




	public void onFinish(ITestContext context) {


		extent.flush();

	}






}
