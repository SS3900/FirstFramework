package com.shopping.ShoppingAppFrameWork;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AlertWindow {

	
	public static void main(String[]args)
	{
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		WebDriver fDriver = new FirefoxDriver();
		fDriver.get("https://demoqa.com/");
		fDriver.manage().window().maximize();
		
		fDriver.findElement(By.xpath("//div[@class='category-cards']/div[3]")).click();
		fDriver.findElement(By.xpath("//div[@class='element-list collapse show']//li[@id='item-1']")).click();
		fDriver.findElement(By.id("alertButton")).click();
		
		Alert a = fDriver.switchTo().alert();
		a.accept();
		
	}
}
