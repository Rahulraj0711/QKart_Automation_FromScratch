package rahul.raj.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import rahul.raj.helpers.SeleniumWrapper;
import rahul.raj.helpers.WebDriverManagerSingleton;
import rahul.raj.pages.HomePage;
import rahul.raj.pages.LoginPage;
import rahul.raj.pages.RegisterPage;
import rahul.raj.testUtilities.DataProviderClass;

public class RegistrationTest {
	private WebDriver driver;
	private WebDriverManagerSingleton instance;
	private HomePage hp;
	private LoginPage lp;
	private RegisterPage rp;
	
	@BeforeClass(alwaysRun = true)
	public void driverSetup() throws FileNotFoundException, IOException {
		instance=WebDriverManagerSingleton.getInstanceOfBrowser();
		driver=instance.getDriver();
		hp=new HomePage(driver);
		lp=new LoginPage(driver);
		rp=new RegisterPage(driver);
		hp.navigateToRegisterPage();
	}
	
	@Test(priority = 1, dataProvider = "testData", dataProviderClass = DataProviderClass.class, enabled=true, groups = "Register")
	public void ValidRegistrationTest(String userName, String password) {
		if(rp.isUserOnRegisterPage()) {
			rp.performUserRegistration(userName, password);
			SeleniumWrapper.webDriverWait(driver, ExpectedConditions.urlContains("login"), 15);
		}
		Assert.assertTrue(lp.isUserOnLoginPage());
		lp.navigateToRegisterFromLogin();
	}
	
	@Test(priority = 2, dataProvider = "testData", dataProviderClass = DataProviderClass.class, enabled=true, groups = "Register")
	public void InvalidRegistrationTest(String userName, String password) {
		if(rp.isUserOnRegisterPage()) {
			rp.performUserRegistration(userName, password);
		}
		Assert.assertTrue(lp.checkPresenceOfErrorPopup());
		rp.clearFieldsOnRegisterPage();
		lp.checkInvisibilityOfErrorPopup();
	}
	
	@AfterClass
	public void closeBrowser() {
		instance.quitBrowser();
	}
}
