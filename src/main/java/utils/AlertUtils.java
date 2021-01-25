package utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shopping.ShoppingAppFrameWork.AlertWindow;

public class AlertUtils {
	public static Set<String> getAllWindow;
	public static Iterator<String> I1;
	public static WebDriver driver = AlertWindow.fDriver;
	
	public static void windowHandleUtils(String elementValue) {
		
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.id(elementValue)).click();
		getAllWindow = driver.getWindowHandles();
		I1 = getAllWindow.iterator();
		while(I1.hasNext())
		{
			String ChildWindow = I1.next();
			System.out.println(ChildWindow);
			
			if(!parentWindow.equals(ChildWindow))
			{
				driver.switchTo().window(ChildWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

}
