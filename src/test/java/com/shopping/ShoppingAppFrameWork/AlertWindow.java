package com.shopping.ShoppingAppFrameWork;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AlertWindow {

	static WebDriver fDriver;
	static WebElement e;
	
	
	public static WebElement scroll(WebElement element, WebDriver driver)
	  {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("arguments[0].scrollIntoView();", element);
		  return element;
	  }
	
	@Test(priority=2)
	public void newPage()
	{
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		fDriver = new FirefoxDriver();
		fDriver.get("https://demoqa.com");
		fDriver.manage().window().maximize();
		
		Wait wait = new WebDriverWait(fDriver,50);
		
		//********************************Window Handle*********************************//
		fDriver.findElement(By.xpath("//div[@class='category-cards']/div[3]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[1]")));
		//e= fDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[1]"));
		//scroll(e,fDriver);
		fDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[1]")).click();
		String parentWindow = fDriver.getWindowHandle();
		System.out.println(parentWindow);
		fDriver.findElement(By.id("tabButton")).click();
		String childWindow = fDriver.getWindowHandle();
		
		if(!parentWindow.equals(childWindow)) {
			fDriver.switchTo().window(childWindow).close();
		}
		
		
		//*******************************Window Alert************************************//
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[2]")));
		e= fDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[2]"));
		scroll(e,fDriver);
		fDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[2]")).click();
		fDriver.findElement(By.id("alertButton")).click();
		
		Alert a = fDriver.switchTo().alert();
		a.accept();
		
		fDriver.findElement(By.id("timerAlertButton")).click();
		fDriver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		a.accept();
		
		fDriver.findElement(By.id("confirmButton")).click();
		a.dismiss();
		
		fDriver.findElement(By.id("promtButton")).click();
		a.sendKeys("Alert Confirmed");
		a.accept();
		
		fDriver.close();
		
	}
	
	
}
