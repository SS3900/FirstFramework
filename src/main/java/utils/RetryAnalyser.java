package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
	int count =0, retrycount =1;
	
	public boolean retry (ITestResult result)
	{
		while(count<retrycount)
		{
			count++;
			return true;
		}
		
		return false;
	}
	

}
