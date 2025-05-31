package rahul.raj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahul.raj.helpers.SeleniumWrapper;

public class UserHomePage {
	private WebDriver driver;
	
	public UserHomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	@FindBy(xpath = "(//input)[1]")
	WebElement searchBox;
	
	@FindBy(css = "div.search-desktop div div svg")
	WebElement searchIcon;
	
	@FindBy(xpath = "//button[normalize-space()='Logout']")
	WebElement logoutButton;
	
	@FindBy(css = "p.username-text")
	WebElement usernameText;
	
	public void performLogout() {
		SeleniumWrapper.click(driver, logoutButton);
	}
	
	public boolean checkUserLoggedInSuccessfully() {
		return SeleniumWrapper.webDriverWait(driver, ExpectedConditions.visibilityOf(usernameText), 15);
	}
}
