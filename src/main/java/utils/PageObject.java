package utils;

import com.shopping.ShoppingAppFrameWork.DriverTest;

public interface PageObject {
	
	String product = "//body/div[@id='root']/div/div[1]/div[1]/div["+DriverTest.a+"]";
	String quantity = "//body/div[@id='root']/div/div[1]/div[1]/div["+DriverTest.a+"]/div[2]/input[@class='quantity']";
	String addToCare = "//body/div[@id='root']/div/div[1]/div[1]/div["+DriverTest.a+"]/div[3]";

}
