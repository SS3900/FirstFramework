package com.shopping.ShoppingAppFrameWork;

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
		WebDriver fDriver = new FirefoxDriver();
		fDriver.get(System.getProperty("url"));
		fDriver.manage().window().maximize();
		
		Wait wait = new WebDriverWait(fDriver,50);
		
		fDriver.findElement(By.xpath("//div[@class='category-cards']/div[3]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[2]")));
		WebElement e= fDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[2]"));
		scroll(e,fDriver);
		fDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/ul[1]/li[2]")).click();
		fDriver.findElement(By.id("alertButton")).click();
		
		Alert a = fDriver.switchTo().alert();
		a.accept();
		
	}
}
