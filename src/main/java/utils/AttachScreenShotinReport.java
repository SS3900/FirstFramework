package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.shopping.ShoppingAppFrameWork.BaseTest;

public class AttachScreenShotinReport {
	static String concatenate = ".";
	public static Object screenCapture(String logdetails, String ssName) throws Exception {
		String getScreenShotPath = concatenate + TestEvidence.screenshot(ssName);
		BaseTest.eTests.log(Status.INFO, logdetails, MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath).build());
		return BaseTest.eTests;
	}
}
