package com.test1.htmlunit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.test1.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class })
public class HtmlUnitTest {
  @Autowired
  WebApplicationContext wac;
  WebClient webClient;
  
  
  @Before
  public void setup(){
    webClient = MockMvcWebClientBuilder.webAppContextSetup(wac).build();
  }
  
  @Test
  public void testLogin() throws Exception {
    HtmlPage page = webClient.getPage("http://127.0.0.1:8090/test1/login");
    HtmlForm form = page.getHtmlElementById("loginForm");
    HtmlTextInput usernameInput = page.getHtmlElementById("username");
    usernameInput.setValueAttribute("1");
    HtmlPasswordInput passwordInput = page.getHtmlElementById("password");
    passwordInput.setValueAttribute("2");
    HtmlButton submitButton = form.getOneHtmlElementByAttribute("button", "type", "submit");
    HtmlPage newPage = submitButton.click();
   
    //assertThat(newPage.getUrl().toString()).endsWith("/u/1");
    assertThat(newPage.getUrl().toString()).endsWith("/login");
    String msg = newPage.getHtmlElementById("alert").getTextContent();
    System.out.println(msg);
  }
  
}
