package rahul.raj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahul.raj.helpers.SeleniumWrapper;

public class RegisterPage {
	private final WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	@FindBy(css = "input#username")
	WebElement usernameBox;
	
	@FindBy(css = "input#password")
	WebElement passwordBox;
	
	@FindBy(css = "input#confirmPassword")
	WebElement confirmPasswordBox;
	
	@FindBy(xpath = "//button[normalize-space()='Register Now']")
	WebElement registerButton;
	
	public boolean isUserOnRegisterPage() {
		return SeleniumWrapper.getCurrentUrl(driver).contains("register");
	}
	
	public void performUserRegistration(String username, String password) {
		SeleniumWrapper.sendKeys(driver, usernameBox, username);
		SeleniumWrapper.sendKeys(driver, passwordBox, password);
		SeleniumWrapper.sendKeys(driver, confirmPasswordBox, password);
		SeleniumWrapper.click(driver, registerButton);
	}
	
	public void clearFieldsOnRegisterPage() {
		SeleniumWrapper.clearText(usernameBox);
		SeleniumWrapper.clearText(passwordBox);
		SeleniumWrapper.clearText(confirmPasswordBox);
	}
}
