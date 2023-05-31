package test_selenium;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class first {
	@Test
	  public void EdgeTest() throws InterruptedException { 
		
	     WebDriver driver = new SafariDriver();  
	     driver.get("https://subscribe.stctv.com");
	     driver.manage().window().maximize();

	    String type = "//*[@class=\"plan-names\"][1]";
	    String priceAndCurrency  = "//*[@class=\"plan-row\"][1]/div[2]";
	    
	    String  Country_name [] = new String[3];
	    String[][]  Package_type = new String[3][3];
	    String[][]Package_price = new String[3][3];
	    String[][] Package_currency  = new String[3][3];
	    
	    for (int i = 1; i <=3; i++) {
	    	String index1 = Integer.toString(i);
	    	Thread.sleep(1000);
		    driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div[1]")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//*[@id=\"country-selct\"]/a["+index1+"]")).click();
		    Country_name[i -1] = driver.findElement(By.xpath("//*[@id=\"country-selct\"]/a["+index1+"]/span[2]")).getText();		    
		    Thread.sleep(3000);
	    	  for (int j = 1; j <=3; j++) {
	    		    String index = Integer.toString(j);
	    		    String[] currency = driver.findElements(By.xpath(priceAndCurrency + "/div["+index+"]/div/i")).toString().split(" / ");
	    		     Package_type[i-1][j-1] = driver.findElement(By.xpath(type + "/div["+index+"]/strong")).getText();
	    		     Package_price[i-1][j-1] = driver.findElement(By.xpath(priceAndCurrency + "/div["+index+"]/div/b")).getText();
	    		     Package_currency[i-1][j-1] = driver.findElement(By.xpath(priceAndCurrency + "/div["+index+"]/div/i/text()")).getText().trim();
	    	  }
 		     Thread.sleep(3000);	  

	    }
	    String  Expect_Country_name [] = {"البحرين","السعودية","الكويت"};
	    String  Expect_Package_type [] = {"لايت","الأساسية","بريميوم"};
	    String  Expect_Package_price [][] = {{"2","3","6"},{"15","25","60"},{"1.2","2.5","4.8"}};
	    String  Expect_Package_currency [] = {"دينار بحريني","ريال سعودي","دينار كويتي"};
	   
	    
	    for (int i = 0; i < 3; i++) {
    	    System.out.println(Country_name[i]);
    	    
    	    Assert.assertEquals(Country_name[i].trim(),Expect_Country_name [i]);

	    	for (int j = 0; j < 3; j++) {
	    	    System.out.print(Package_type[i][j]);
	    	    System.out.print(Package_price[i][j]);
	    	    System.out.println(Package_currency[i][j]);
	    	    Assert.assertEquals(Package_type[i][j],Expect_Package_type [j]);
	    	    Assert.assertEquals(Package_price[i][j],Expect_Package_price [i][j]);
	    	    Assert.assertEquals(Package_currency[i][j].contains(Expect_Package_currency[i]), true);

	    	}
    	    System.out.println("---------------------");

	    	}}}
