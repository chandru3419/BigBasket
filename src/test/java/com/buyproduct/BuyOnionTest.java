package com.buyproduct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import genericUtility.BaseClass;


public class BuyOnionTest extends BaseClass {

	ArrayList<Double> list = new ArrayList<Double>();

	//this method is used to find the onion price in Big Basket
	@Test(priority=1)
	public void testBigBasket() throws InterruptedException
	{
		driver.get("https://www.bigbasket.com/");
		driver.findElement(By.xpath("//input[@qa='searchBar']")).sendKeys("onion");
		driver.findElement(By.xpath("//button[@class='btn btn-default bb-search']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[.='Onion - Organically Grown']")).click();
		Thread.sleep(5000);
		String price = driver.findElement(By.xpath("//h1[.='Fresho Onion - Organically Grown, 1 kg  ']/ancestor::div[@id='title']/descendant::td[@data-qa='productPrice']")).getText();
		System.out.println("Price of 1 KG onion in Bigbasket:  "+price.substring(3));
		list.add(Double.parseDouble(price.substring(3)));
	}

	//this method is used to find the onion price in Amazon
	@Test(priority=2)
	public void testAmazon(){
		driver.get("https://www.amazon.in/");

		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("onion 1 kg",Keys.ENTER);
		driver.findElement(By.xpath("//span[.='Fresh Onion, 1kg']")).click();
		Set<String> WinId = driver.getWindowHandles();
		for (String wn : WinId) 
		{
			driver.switchTo().window(wn);
		}
		String amt = driver.findElement(By.xpath("//span[contains(.,'Fresh Onion, 1kg')]"
				+ "/ancestor::div[@class='centerColAlign centerColAlign-bbcxoverride']/descendant::span[@class='a-size-medium a-color-price']")).getText();
		System.out.println("Price of 1 KG onion in Amazon:  "+amt.substring(1));
		list.add(Double.parseDouble(amt.substring(1)));	
	}
	
	//this method is used to find the onion price in Flipkart
	@Test(priority=3)
	public void testFlipkart() throws Throwable{
		driver.get("https://www.flipkart.com/");

		driver.findElement(By.xpath("//button[.='✕']")).click();
		driver.findElement(By.xpath("//input[@class='_3704LK']")).sendKeys("onion",Keys.ENTER);

		driver.findElement(By.xpath("//a[.='Onion 1 kg']")).click();
		Set<String> WinId = driver.getWindowHandles();
		for (String wn : WinId) 
		{
			driver.switchTo().window(wn);
		}

		String price2=driver.findElement(By.xpath("//span[.='Onion 1 kg']/ancestor::div[@class='aMaAEs']/descendant::div[@class='_30jeq3 _16Jk6d']")).getText();
		Thread.sleep(5000);
		System.out.println("Price of 1 KG onion in Flipkart:  "+price2.substring(1));
		list.add(Double.parseDouble(price2.substring(1)));	

		//Sorting the minimum price for onion
		Collections.sort(list,Collections.reverseOrder());
	    //Printing minimum onion price
		System.out.println("Minimum Onion Price for 1 KG "+list.get(list.size()-1));
		
		driver.quit();
	}
}


