package com.shopping.ShoppingAppFrameWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ReadWriteExcelFile;
import utils.TestEvidence;

public class DriverTest extends BaseTest {

	static WebElement product;
	static String pName;
	public static Integer a,b;
	static int finalprice, Totalprice = 0;
	
	@Test
	public void testMethod() throws Exception {
		
		for (int i=0; i<BaseTest.totalprod; i++)
		{
		Integer[] prod_id = ReadWriteExcelFile.getsList(i, BaseTest.LPQ);
		a = (prod_id[0]);
		b = (prod_id[1]);
		product = BaseTest.driver.findElement(By.xpath("//body/div[@id='root']/div/div[1]/div[1]/div["+a+"]"));
		pName = product.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div["+a+"]/h4[1]")).getText();
		product.findElement(By.xpath("//body/div[@id='root']/div/div[1]/div[1]/div["+a+"]/div[2]/input[@class='quantity']")).clear();
		product.findElement(By.xpath("//body/div[@id='root']/div/div[1]/div[1]/div["+a+"]/div[2]/input[@class='quantity']")).sendKeys(b.toString());
		product.findElement(By.xpath("//body/div[@id='root']/div/div[1]/div[1]/div["+a+"]/div[3]")).click();//3
		int listprice = ReadWriteExcelFile.getPrice(pName);
		int pValue = b;
		finalprice = (pValue)*(listprice);
		System.out.println(finalprice);
		fPrice[i]= finalprice;
		
		Totalprice = Totalprice + (finalprice);
		Thread.sleep(1000);
		}

		String cartPrice = BaseTest.driver.findElement(By.xpath("//tr[2]//td[3]//strong[1]")).getText();
		int cPrice = ReadWriteExcelFile.StringtoIntegerConverter(cartPrice);
		
		System.out.println(cPrice);
		
		Assert.assertEquals(cPrice, Totalprice);
		BaseTest.driver.findElement(By.className("cart-icon")).click();
		BaseTest.driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		TestEvidence.screenshot("FinalCart");
		BaseTest.driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
		WebElement stateselector = BaseTest.driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/select[1]"));
		Select slct=new Select(stateselector);
		slct.selectByVisibleText("United States");
		BaseTest.driver.findElement(By.className("chkAgree")).click();
		BaseTest.driver.findElement(By.xpath("//button[contains(text(),'Proceed')]")).click();
		TestEvidence.screenshot("orderplaceMsg");
			
	
	}
}
