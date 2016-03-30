package com.test1.htmlunit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.AbstractPage;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebWindow;

public class LoginPage extends AbstractPage {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public LoginPage(WebResponse webResponse, WebWindow enclosingWindow) {
    super(webResponse, enclosingWindow);
  }
  
  @FindBy(css = "input[type=text]")
  private WebElement username;
  @FindBy(css = "input[type=password]")
  private WebElement password;
  @FindBy(css = "button[type=submit]")
  private WebElement submit;
  
  public <T> T login(Class<T> resultPage, String username, String password, WebDriver driver) {
    this.username.sendKeys(username);
    this.password.sendKeys(password);
    this.submit.click();
    return PageFactory.initElements(driver, resultPage);
  }
  
  public static LoginPage to(WebDriver driver) {
    return PageFactory.initElements(driver, LoginPage.class);
  }

}
