package rahul.raj.helpers;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWrapper {

	public static WebElement findElement(WebDriver driver, By by) {
		WebElement element=driver.findElement(by);
		return element;
	}
	
	public static WebElement findElementUsingElement(WebElement element1, By by) {
		WebElement element2=element1.findElement(by);
		return element2;
	}
	
	public static List<WebElement> findElements(WebDriver driver, By by) {
		List<WebElement> elements=driver.findElements(by);
		return elements;
	}
	
	public static void implicitWait(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	
	
	public static boolean webDriverWait(WebDriver driver, ExpectedCondition<?> condition, int seconds) {
		try {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(condition);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public static boolean fluentWait(WebDriver driver, ExpectedCondition<?> condition, int timeout, int pollingInterval) {
		try {
			Wait<WebDriver> fwait = new FluentWait<>(driver).
					withTimeout(Duration.ofSeconds(timeout)).
					pollingEvery(Duration.ofSeconds(pollingInterval)).
					ignoring(Exception.class);
			fwait.until(condition);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public static void scrollToElement(WebDriver driver, WebElement element) {
		webDriverWait(driver, ExpectedConditions.visibilityOf(element), 15);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void click(WebDriver driver, WebElement element) {
		webDriverWait(driver, ExpectedConditions.visibilityOf(element), 15);
        element.click();
	}
	
	public static void sendKeys(WebDriver driver, WebElement element, String text) {
		webDriverWait(driver, ExpectedConditions.visibilityOf(element), 15);
        element.sendKeys(text);
	}
	
	public static String getText(WebDriver driver, WebElement element) {
		webDriverWait(driver, ExpectedConditions.visibilityOf(element), 15);
		return element.getText().trim();
	}
	
	public static String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
	}
	
	public static void clearText(WebElement element) {
		element.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	}
	
	public static String takeScreenshot(WebDriver driver, String testCaseName) throws IOException {
		File screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir")+"//screenshots//"+testCaseName+"_"+System.currentTimeMillis()+".png";
		File destFile=new File(destination);
		FileUtils.copyFile(screenshot, destFile);
		return destination;
	}
}
