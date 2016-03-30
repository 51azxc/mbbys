package com.test1.htmlunit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private final WebDriver driver;
	
	@FindBy(id = "username")
	private WebElement username;
	@FindBy(css = "input[type=password]")
	private WebElement password;
	@FindBy(css = "button[type=submit]")
	private WebElement submit;
	@FindBy(className = "alert")
	private WebElement alert;
	
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public LoginPage submit(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		submit.click();
		return new LoginPage(driver);
	}
	
	public String getAlertMessage() {
		return this.alert.getText();
	}
}
