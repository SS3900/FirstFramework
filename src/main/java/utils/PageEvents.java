package utils;

import utils.FetchElements;
import utils.PageObject;

public class PageEvents {
	
	public void getElement()
	{
	FetchElements eFetch = new FetchElements();
	eFetch.getWebElement("XPATH", PageObject.product);
	}
	
	}
