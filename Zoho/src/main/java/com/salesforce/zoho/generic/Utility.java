package com.salesforce.zoho.generic;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	public static void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String getScreenshot(WebDriver driver, String testCaseName) {
		String timeStamp=LocalDateTime.now().toString().replace(':', '-');
		TakesScreenshot ts = (TakesScreenshot)driver;
		File tempFile = ts.getScreenshotAs(OutputType.FILE);
		String filePath = IAutoConstants.IMG_PATH+timeStamp+testCaseName+".png";
		File destFile = new File(filePath);
		
		try {
			FileUtils.copyFile(tempFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return destFile.getAbsolutePath();
	}
}
