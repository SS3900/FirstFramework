package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.shopping.ShoppingAppFrameWork.BaseTest;

public class TestEvidence {
	
	public static String screenshot(String sName) throws IOException  
	{
		File sFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
		File dstfile = new File("./screenshot/"+sName+".png");
		FileUtils.copyFile(sFile, dstfile);
		
		return "./screenshot/"+sName+".png";
	}

}
