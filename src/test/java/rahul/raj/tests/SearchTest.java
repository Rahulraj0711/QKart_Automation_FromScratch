package rahul.raj.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import rahul.raj.helpers.WebDriverManagerSingleton;
import rahul.raj.pages.HomePage;
import rahul.raj.testUtilities.DataProviderClass;

public class SearchTest {
	private WebDriver driver;
	private WebDriverManagerSingleton instance;
	private HomePage hp;
	
	@BeforeClass(alwaysRun = true)
	public void driverSetup() throws FileNotFoundException, IOException {
		instance=WebDriverManagerSingleton.getInstanceOfBrowser();
		driver=instance.getDriver();
		hp=new HomePage(driver);
	}
	
	@Test(priority = 1, dataProvider = "testData", dataProviderClass = DataProviderClass.class, enabled=true, groups = "Search")
	public void ValidProductSearch(String productName) throws InterruptedException {
		hp.searchProduct(productName);
		List<WebElement> products=hp.getSearchResults();
		Assert.assertTrue(hp.checkSearchedProducts(products, productName));
	}
	
	@Test(priority = 2, dataProvider = "testData", dataProviderClass = DataProviderClass.class, enabled=true, groups = "Search")
	public void InvalidProductSearch(String productName) throws InterruptedException {
		hp.searchProduct(productName);
		Assert.assertTrue(hp.noProductFound());
	}
	
	@AfterClass
	public void closeBrowser() {
		instance.quitBrowser();
	}
}
