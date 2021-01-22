package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;


public class SuiteListener implements ITestListener, IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyser.class);
	}

	public void onTestStart(ITestResult result) {
		
	}

	public void onTestSuccess(ITestResult result) {
		
	}

	public void onTestFailure(ITestResult result) {
		
		try {
			AttachScreenShotinReport.screenCapture("Test Failed", "failed-step-SS");
			//int currentLine = new Throwable().getStackTrace()[0].getLineNumber();
			//System.out.println("The Current Line Number is: " + currentLine);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getStackTrace();
		}
		//BaseTest.eTests.log(Status.INFO, MarkupHelper.createLabel("Test failed at this point", ExtentColor.AMBER));
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		
	}

	
}
