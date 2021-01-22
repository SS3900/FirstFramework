package com.shopping.ShoppingAppFrameWork;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.ReadWriteExcelFile;
import utils.SendAutomationReportViaAutoEmail;

public class BaseTest {
    
	public static WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports reports;
	public static ExtentTest eTests;
	public Integer a,b;
	static Integer [] fPrice;
	public static int totalprod;
	public static ArrayList<ArrayList<Integer>> LPQ;
	static String concatenate = ".";
	public static String testResult;
	
	@BeforeTest
	@Parameters(value = "testerSign")
	public void beforeTestMethod(String testerSign) throws IOException
	{
		File OutputFolder = new File("./reports");
		 if (!OutputFolder.exists()) {
			 if(OutputFolder.mkdir()) {
		         System.out.println("Directory is created!");
			 }
			 else  {   System.out.println("Failed to create Directory");
		      } 
		  }else {System.out.println("Directory already exists");
		  }
		//**********************************Report Generation******************************************//
		htmlReporter = new ExtentHtmlReporter("./reports/AutomatioTestReports.html");
		htmlReporter.config().setEncoding("uft-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.DARK);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("Tester", testerSign );
		//**********************************End of Report ********************************************************//
		//************************************Import Data from excel***********************************//
		totalprod = ReadWriteExcelFile.getTotalexecution();
		fPrice = new Integer[totalprod];
		System.out.println(totalprod);
		LPQ = ReadWriteExcelFile.getPQ();
		
	}
	
	@BeforeMethod
	@Parameters(value ="browserName")
	public void beforeMethodMethod(String browserName, Method testMethod)
	{
		eTests = reports.createTest(testMethod.getName());
		getDriver("chrome");
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void afterMethodMethod(ITestResult result)
	{
		testResult = String.valueOf(result.getStatus());
		if (result.getStatus()==ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logText = "TestCase:"+ methodName + "Passed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			eTests.log(Status.PASS, m);
			}else if (result.getStatus()==ITestResult.FAILURE) {
				String methodName = result.getMethod().getMethodName();
				String logText = "TestCase:"+ methodName + "Failed";
				Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
				eTests.log(Status.FAIL, m);
			}else if (result.getStatus()==ITestResult.SKIP) {
				String methodName = result.getMethod().getMethodName();
				String logText = "TestCase:"+ methodName + "Skipped";
				Markup m = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
				eTests.log(Status.SKIP, m);
			}
		driver.quit();
	}
	
	@AfterTest
	public void afterTestMethod()
	{
		reports.flush();
		
	}
	
	@AfterSuite
	public void sendReport() {
		
		SendAutomationReportViaAutoEmail.triggerExecutionReport(testResult,"sraman19940@gmail.com","Siva@2367","sivarammrn@gmail.com");
	}
	
	public static void getDriver(String testDriver)
	{
		if (testDriver.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		}else if(testDriver.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "./drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		}else {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
	
}
