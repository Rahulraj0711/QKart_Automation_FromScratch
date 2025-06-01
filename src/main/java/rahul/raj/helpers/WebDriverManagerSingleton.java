package rahul.raj.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManagerSingleton {
	
	private static WebDriverManagerSingleton driverInstance=null;
	private final ThreadLocal<WebDriver> driverThread= new ThreadLocal<>();
	
	private WebDriverManagerSingleton() throws IOException {
		Properties prop=new Properties();
		prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/test/java/rahul/raj/resources/GlobalData.properties"));
		String browser=prop.getProperty("browser");
		if(browser.contains("headless")) {
			ChromeOptions options=new ChromeOptions();
			options.addArguments("headless");
			driverThread.set(new ChromeDriver(options));
		}
		else if(browser.contains("chrome")) {
			driverThread.set(new ChromeDriver());
		}
		else if(browser.contains("edge")) {
			driverThread.set(new ChromeDriver());
		}
		else {
			driverThread.set(new ChromeDriver());
		}
		driverThread.get().manage().window().maximize();
		driverThread.get().get(prop.getProperty("url"));
	}
	
	public static WebDriverManagerSingleton getInstanceOfBrowser() throws IOException {
		if(driverInstance==null) {
			driverInstance=new WebDriverManagerSingleton();
		}
		return driverInstance;
	}
	
	public WebDriver getDriver() {
		return this.driverThread.get();
	}
	
	public void quitBrowser() {
		this.driverThread.get().quit();
	}
}
