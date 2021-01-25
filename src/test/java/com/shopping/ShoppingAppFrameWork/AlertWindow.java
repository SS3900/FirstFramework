package com.shopping.ShoppingAppFrameWork;

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

import utils.AlertUtils;

public class AlertWindow {

	public static WebDriver fDriver;
	public static WebElement e;

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
		//********************************New Tab Handle*******************************//
		AlertUtils.windowHandleUtils("tabButton");
		
		//*******************************New Window Handle***************************//
		AlertUtils.windowHandleUtils("windowButton");
		
		//**********************************New Window Handle************************//
		AlertUtils.windowHandleUtils("messageWindowButton");
		
		//*******************************Window Alert************************************//
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[2]")));
		e= fDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[2]"));
		scroll(e,fDriver);
		fDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[2]")).click();
		fDriver.findElement(By.id("alertButton")).click();
		
		Alert a = fDriver.switchTo().alert();
		a.accept();
		
		fDriver.findElement(By.id("timerAlertButton")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		fDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		a.accept();
		
		fDriver.findElement(By.id("confirmButton")).click();
		a.dismiss();
		
		fDriver.findElement(By.id("promtButton")).click();
		a.sendKeys("Alert Confirmed");
		a.accept();
		
		fDriver.close();
		
	}
	
	
}
