package com.neethu.tests;

import java.time.Duration;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.neethu.base.BaseTest;

public class Miniproject2_Handle_multiple_features extends BaseTest {

	/*
	 * Test Case: “Handle multiple features” 1.Open website
	 * [https://demo.automationtesting.in/Windows.html 2.Click “Open New Window”
	 * 3.Switch to new window 4.Validate title 5.Go back to main window 6.Navigate
	 * to Frames 7.Switch to frame 8.Enter text 9.Switch back to main content
	 */
	@Test
	public void testMultipleFeatures() {

		// Open website
		driver.get("https://demo.automationtesting.in/Windows.html");
		driver.manage().window().maximize();

		// Explicitwait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		String mainwindow = driver.getWindowHandle();
		String maintitle = driver.getTitle();
		System.out.println("Maintitle:" + maintitle);

		// Click “Open New Window”
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='btn btn-info'])[1]")));
		driver.findElement(By.xpath("(//button[@class='btn btn-info'])[1]")).click();

		Set<String> allwindows = driver.getWindowHandles();
		for (String window : allwindows) {
			if (!window.equals(mainwindow)) {
				driver.switchTo().window(window);
			}
		}
//Validate title
		String Actual_title = driver.getTitle();
		String Expected_title = "Selenium";
		System.out.println("New window title:" + Actual_title);

		Assert.assertTrue(Actual_title.contains(Expected_title), "Title does not contain expected text!");
		driver.close();

//Go back to main window
		driver.switchTo().window(mainwindow);

// Navigate to Frames page
		driver.get("https://demo.automationtesting.in/Frames.html");

//wait and Switch to frames
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("singleframe"));

//Enter Text
		String input_text = "QA";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[1]")));

		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(input_text);

//Validate entered text
		String entered_text = driver.findElement(By.xpath("(//input[@type='text'])[1]")).getAttribute("value");
		Assert.assertEquals(entered_text, input_text, "Entered text does not match!");

//switch back to main content
		driver.switchTo().defaultContent();
		
		driver.quit();

	}

}
