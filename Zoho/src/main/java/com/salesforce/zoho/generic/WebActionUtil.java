package com.salesforce.zoho.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


//Wrapper class to perform Actions on the WebElements
public class WebActionUtil {
	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor jse;
	Actions actions;
	public WebActionUtil(WebDriver driver, long ETO) {
		this.driver=driver;
		wait = new WebDriverWait(driver,ETO);
		jse = (JavascriptExecutor)driver;
		actions = new Actions(driver);
	}
	
	public void enterData(WebElement target, String text) {
		target.clear();
		target.sendKeys(text);
	}
	
	public void click(WebElement target) {
		wait.until(ExpectedConditions.elementToBeClickable(target));
		try {
			target.click();
		} catch(Exception e) {
			try {
				jsClick(target);
			} catch(Exception e2) {
				Utility.sleepInSeconds(3);
				target.click();
			}			
		}		
	}
	
	public void jsClick(WebElement target) {
		jse.executeScript("arguments[0].click()", target);
	}
	
	public void scrollToEndOrTop(boolean end) {
		if(end) {
			jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		} else {
			jse.executeScript("window.scrollTo(0,-document.body.scrollHeight);");
		}
	}
	
	public void switchToFrame(String indexNameOrId) {
		try {
			int index=Integer.parseInt(indexNameOrId);
			driver.switchTo().frame(index);
		} catch(NumberFormatException e) {
			driver.switchTo().frame(indexNameOrId);
		}
	}
	
	public void mouseHover(WebElement target) {
		actions.moveToElement(target).perform();
	}
	
	public void dragAndDrop(WebElement source, WebElement dest) {
		actions.dragAndDrop(source, dest).perform();
	}
	
	public void selectByVisbleText(WebElement targetListBox, String text) {
		Select s = new Select(targetListBox);
		s.selectByVisibleText(text);
	}
	
	public void closeChildBrowser() {
		String parentWid=driver.getWindowHandle();
		
		Set<String> allWindowIds=driver.getWindowHandles();
		allWindowIds.remove(parentWid);
		for(String wid:allWindowIds) {
			driver.switchTo().window(wid);
			driver.close();
		}
		
		driver.switchTo().window(parentWid);
	}
	
	public void switchToWindow(String titleContains) {
		Set<String> allWindowIds=driver.getWindowHandles();
		
		for(String wid:allWindowIds) {
			driver.switchTo().window(wid);
			if(driver.getTitle().contains(titleContains)) {
				break;
			}
		}
	}
	
	public void switchToParentWindow() {
		List<String> widsList = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(widsList.get(0));
	}
	
	public boolean verifyAlertText(String alertTextContains) {
		wait.until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert().getText().contains(alertTextContains);
	}

	public void verifyAlertTextAndAccept(String campainName) {
		verifyAlertText(campainName);
		driver.switchTo().alert().accept();		
	}
	
	public void waitForInvisibility(WebElement target) {
		wait.until(ExpectedConditions.invisibilityOf(target));
	}
	
}
