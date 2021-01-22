package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import com.shopping.ShoppingAppFrameWork.BaseTest;

public class ReadWriteExcelFile {

	static ArrayList <String> listofProduct;
	static int j=0, intVal, intvalue;
	static String product, productPrice, a,b;
	static FileInputStream wb;
	static ArrayList<ArrayList<Integer>> LOP = new ArrayList<ArrayList<Integer>>();
	
	public static void pPrice() throws IOException
	{
		for (int i =1; i<31;i++)
		{
		product = BaseTest.driver.findElement(By.xpath("//body/div[@id='root']/div/div[1]/div[1]/div["+i+"]/h4")).getText();
		productPrice = BaseTest.driver.findElement(By.xpath("//body/div[@id='root']/div/div[1]/div[1]/div["+i+"]/p[@class='product-price']")).getText();
		//productData(product, productPrice);
		wb = new FileInputStream("C:\\Users\\sivaraman\\Desktop\\shopping.xlsx");
		Workbook wbs = WorkbookFactory.create(wb);
		Sheet s = wbs.getSheet("Sheet1");
		Object[][] pData = {{product,productPrice}};
		
		
		for (Object[] aBook: pData)
		{
			Row row = s.createRow(i);
			
			int countColm = 0;
			for (Object field: aBook)
			{
				Cell cell = row.createCell(++countColm);
				cell.setCellValue((String) field);
			
			}
			//countRow++;	
		}
		FileOutputStream output = new FileOutputStream("C:\\Users\\sivaraman\\Desktop\\shopping.xlsx");
		wbs.write(output);
		output.close();
		
		}
		
	}
	
	public static int getPrice(String pName) throws IOException
	{
		FileInputStream file = new FileInputStream("C:\\Users\\sivaraman\\Desktop\\shopping.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		Iterator<Row> itRow = sheet.iterator();
		while (itRow.hasNext())
		{
			Row pRow = itRow.next();
			Iterator<Cell> itCell = pRow.cellIterator();
			while(itCell.hasNext())
			{
				
				Cell pCell = itCell.next();
				if (pCell.getStringCellValue() .contains(pName))
				{
					String value= String.valueOf(pRow.getCell(2).getStringCellValue());
			        intVal = Integer.parseInt(value);
			        //price = pRow.getCell(2);						
				}
			}		
		}
		return intVal;
	}
	
	public static int StringtoIntegerConverter(String sValue)
	{
		if (sValue.contains("."))
		{
			int length1 = sValue.length();
			if (length1 > 3)
			{
				String value = sValue.substring(0, 2);
				intvalue = Integer.parseInt(value);
			}
			else
			{
				String value = sValue.substring(0, 1);
				intvalue = Integer.parseInt(value);
			}

		}
		else
		{
			intvalue = Integer.parseInt(sValue);
			
		}
		return intvalue;
	}
	//getTotalexecution
	public static int getTotalexecution() throws IOException
	{
		Iterator<Row> itRow = getdatasheet();
		Row pRow = itRow.next();
		Iterator <Cell> itCell = pRow.cellIterator();
		int k=0, column=0;
		while(itCell.hasNext())
		{
			Cell cValue = itCell.next();
			if (cValue.getStringCellValue().equalsIgnoreCase("P_Indicatior"))
			{
				column = k;
				break;
				//System.out.println(column);
			}
			k++;
		}
		int totalExeCount = 0;
		while(itRow.hasNext())
		{
			Row r = itRow.next();
			if(r.getCell(column).getStringCellValue().equalsIgnoreCase("Y"))
			{
				
				//System.out.println(totalExeCount + 1);
				totalExeCount++;
				
			}
		}
		return (totalExeCount);
	}
	public static Iterator<Row> getdatasheet() throws IOException
	{
		FileInputStream file = new FileInputStream("C:\\Users\\sivaraman\\Desktop\\shopping.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet("Purchase-list");
		Iterator<Row> itRow = sheet.iterator();
		return itRow;
	}
	
	public static ArrayList<ArrayList<Integer>> getPQ() throws IOException
	{
		Iterator<Row> itRow = getdatasheet();
		Row pRow = itRow.next();
		Iterator <Cell> itCell = pRow.cellIterator();
		int k=0, column=0;
		while(itCell.hasNext())
		{
			Cell cValue = itCell.next();
			if (cValue.getStringCellValue().equalsIgnoreCase("P_Indicatior"))
			{
				column = k;
				break;
				//System.out.println(column);
			}
			k++;
		}
		
		while(itRow.hasNext())
		{
			
			Row r = itRow.next();
			if(r.getCell(column).getStringCellValue().equalsIgnoreCase("Y"))
			{
				LOP.add(new ArrayList<Integer>());
				 Iterator<Cell> cr = r.cellIterator();  
				 int l=0,m=0;
				 while(cr.hasNext())
				{
					 
					 Cell cType = cr.next();
					 if (cType.getCellTypeEnum()==CellType.NUMERIC)
					 {
						 
						String get = r.getCell(l).toString();
						l++;
						int get1 = StringtoIntegerConverter(get);
						//System.out.println(get1);
						LOP.get(j).add(m, get1);
						m++;
					 }
					 else
					 {

						 l++;
					}
						 
				}
				 //System.out.println(LOP);
			}
			
			else if (r.getCell(column).getStringCellValue().equalsIgnoreCase("N"))
			{
				j--;
			}	
			j++;
			
		}
		return LOP;	
		
	}
	//arrayListindexExtractor()
	public static Integer[] getsList(int i, ArrayList<ArrayList<Integer>> LPQ) throws IOException
	{
		
		ArrayList<Integer> lla = LPQ.get(i);
		Integer a = lla.get(0);
		Integer b = lla.get(1);
		Integer [] PQ = new Integer [2];
		PQ[0] = a;
		PQ[1]= b;
		return PQ;
		

	}
}
