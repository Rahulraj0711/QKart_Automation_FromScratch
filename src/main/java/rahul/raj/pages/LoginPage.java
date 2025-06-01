package rahul.raj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahul.raj.helpers.SeleniumWrapper;

public class LoginPage {
	private final WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	@FindBy(css = "input#username")
	WebElement usernameBox;
	
	@FindBy(css = "input#password")
	WebElement passwordBox;
	
	@FindBy(xpath = "//button[normalize-space()='Login to QKart']")
	WebElement loginButton;
	
	@FindBy(partialLinkText = "Register")
	WebElement registerLink;
	
	public boolean isUserOnLoginPage() {
		return SeleniumWrapper.getCurrentUrl(driver).contains("login");
	}
	
	public boolean checkPresenceOfErrorPopup() {
		return SeleniumWrapper.fluentWait(driver, ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='alert']")), 30, 3);
	}
	
	public boolean checkInvisibilityOfErrorPopup() {
		return SeleniumWrapper.fluentWait(driver, ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[role='alert']")), 30, 3);
	}
	
	public void performLogin(String username, String password) {
		SeleniumWrapper.sendKeys(driver, usernameBox, username);
		SeleniumWrapper.sendKeys(driver, passwordBox, password);
		SeleniumWrapper.click(driver, loginButton);
	}
	
	public void clearFieldsOnLoginPage() {
		SeleniumWrapper.clearText(usernameBox);
		SeleniumWrapper.clearText(passwordBox);
	}
	
	public void navigateToRegisterFromLogin() {
		SeleniumWrapper.click(driver, registerLink);
	}
}
