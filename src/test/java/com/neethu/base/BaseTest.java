package com.neethu.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver = new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Windows.html");
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}

}
