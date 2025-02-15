package rahul.raj.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import rahul.raj.helpers.WebDriverManagerSingleton;
import rahul.raj.pages.HomePage;
import rahul.raj.pages.LoginPage;
import rahul.raj.pages.UserHomePage;
import rahul.raj.testUtilities.DataProviderClass;

public class LoginTest {
	private WebDriver driver;
	private WebDriverManagerSingleton instance;
	private HomePage hp;
	private LoginPage lp;
	private UserHomePage uhp;
	
	@BeforeClass(alwaysRun = true)
	public void driverSetup() throws FileNotFoundException, IOException {
		instance=WebDriverManagerSingleton.getInstanceOfBrowser();
		driver=instance.getDriver();
		hp=new HomePage(driver);
		lp=new LoginPage(driver);
		uhp=new UserHomePage(driver);
	}
	
	@Test(priority = 1, dataProvider = "testData", dataProviderClass = DataProviderClass.class, enabled = true, groups = "Login")
	public void ValidLoginTest(String userName, String password) {
		if(!lp.isUserOnLoginPage()) {
			hp.navigateToLoginPage();
		}
		lp.performLogin(userName, password);
		Assert.assertTrue(uhp.checkUserLoggedInSuccessfully());
		uhp.performLogout();
	}
	
	@Test(priority = 2, dataProvider = "testData", dataProviderClass = DataProviderClass.class, enabled = true, groups="Login")
	public void InvalidLoginTest(String userName, String password) {
		if(!lp.isUserOnLoginPage()) {
			hp.navigateToLoginPage();
		}
		lp.performLogin(userName, password);
		Assert.assertTrue(lp.checkPresenceOfErrorPopup());
		lp.clearFieldsOnLoginPage();
		lp.checkInvisibilityOfErrorPopup();
	}
	
	@AfterClass
	public void closeBrowser() {
		instance.quitBrowser();
	}
}
