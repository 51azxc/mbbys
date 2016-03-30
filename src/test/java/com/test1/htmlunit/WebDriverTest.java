package com.test1.htmlunit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.test1.config.WebConfig;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class })
public class WebDriverTest {

  @Autowired
  WebApplicationContext wac;
  WebDriver webDriver;
  
  
  @Before
  public void setup(){
    webDriver = MockMvcHtmlUnitDriverBuilder.webAppContextSetup(wac).build();
  }
  
  @Test
  public void testLogin() throws Exception {
    webDriver.get("http://127.0.0.1:8090/test1/login");
    LoginPage page = LoginPage.to(webDriver);
    HtmlPage toPage = page.login(HtmlPage.class, "1", "1", webDriver);
    assertThat(toPage.getUrl().toString()).endsWith("/u/1");
  }
  
  @After
  public void destroy(){
    if (webDriver != null) {
      webDriver.close();
    }
  }
}
