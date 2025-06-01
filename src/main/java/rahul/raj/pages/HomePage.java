package rahul.raj.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahul.raj.helpers.SeleniumWrapper;

public class HomePage {
	private final WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	@FindBy(xpath = "(//input)[1]")
	WebElement searchBox;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement loginButton;
	
	@FindBy(xpath = "//button[normalize-space()='Register']")
	WebElement registerButton;
	
	public void navigateToLoginPage() {
		SeleniumWrapper.click(driver, loginButton);
	}
	
	public void navigateToRegisterPage() {
		SeleniumWrapper.click(driver, registerButton);
	}
	
	public void searchProduct(String productName) throws InterruptedException {
		SeleniumWrapper.clearText(searchBox);
		SeleniumWrapper.sendKeys(driver, searchBox, productName);
		Thread.sleep(5000);
	}
	
	public List<WebElement> getSearchResults() {
        return SeleniumWrapper.findElements(driver, By.cssSelector(".css-1qw96cp"));
	}
	
	public boolean checkSearchedProducts(List<WebElement> resultsList, String searchedProduct) {
		for(WebElement e:resultsList) {
			WebElement textElement=SeleniumWrapper.findElementUsingElement(e, By.cssSelector("p:nth-child(1)"));
			String productName=SeleniumWrapper.getText(driver, textElement);
			System.out.println(productName);
			if(!productName.toLowerCase().contains(searchedProduct.toLowerCase()))
				return false;
		}
		return true;
	}
	
	public boolean noProductFound() {
		WebElement messageElement=SeleniumWrapper.findElement(driver, By.cssSelector(".loading h4"));
		String messageText=SeleniumWrapper.getText(driver, messageElement);
		return messageText.equalsIgnoreCase("No products found");
	}
}
