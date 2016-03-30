package com.test1.htmlunit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.test1.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value="test1/src/main/webapp")
@ContextConfiguration(classes = { WebConfig.class })
public class WebDriverTest {

  @Autowired
  WebApplicationContext wac;
  WebDriver webDriver;
  
  @Before
  public void setup(){
    webDriver = MockMvcHtmlUnitDriverBuilder
    		.webAppContextSetup(wac)
    		.javascriptEnabled(true)
    		.contextPath("/test1")
    		.build();
  }
  
  @Test
  public void testLogin() throws Exception {
    webDriver.get("http://127.0.0.1:8090/test1/login");
    webDriver.findElement(By.id("username")).sendKeys("1");
    webDriver.findElement(By.id("password")).sendKeys("2");
    webDriver.findElement(By.cssSelector("button[type=submit]")).click();
    assertThat(webDriver.getCurrentUrl().toString()).endsWith("/login");
    String msg = webDriver.findElement(By.className("alert")).getText();
    System.out.println(msg);
  }
  
  @Test
  public void testUserLogin() throws Exception {
	  webDriver.get("http://127.0.0.1:8090/test1/login");
	  LoginPage fromPage = new LoginPage(webDriver);
	  LoginPage toPage = fromPage.submit("1", "1");
	  System.out.println(toPage.getAlertMessage());
  }
  
  @After
  public void destroy(){
    if (webDriver != null) {
      webDriver.close();
    }
  }
}
