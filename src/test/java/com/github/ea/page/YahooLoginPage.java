package com.github.ea.page;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class YahooLoginPage extends AbstractPage {
    private final String PAGE_URL = "https://login.yahoo.com";
    private final Logger logger = LogManager.getRootLogger();
        
    @FindBy(id = "login-username")
    private WebElement usernameEmailOrMobileTextBox;
    
    @FindBy(id = "login-signin")
    private WebElement signInButton;
    
    private By passwordTextBoxBy = By.id("login-passwd");
    private By emailNotRegisteredTextBoxBy = By.id("username-error");
    private By wrongPasswordTextBoxBy = By.xpath("//*[@id='password-container']/following-sibling::*[@class='error-msg']");
            
    public YahooLoginPage(WebDriver driver) {
      super(driver);
      PageFactory.initElements(this.driver, this);
    }
    
    public YahooLoginPage openPage(){
      driver.navigate().to(PAGE_URL);
      logger.info("Yahoo Login page opened");
      return this;
    }
    
    public YahooLoginPage enterEmail(String email) {
      usernameEmailOrMobileTextBox.sendKeys(email);     
      signInButton.click();
      return this;
    }
    
    public YahooHomePage enterPassword(String password) {
      WebElement passwordTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(passwordTextBoxBy));
      passwordTextBox.sendKeys(password);            
      signInButton.click();
      logger.info("Login performed");
      return new YahooHomePage(driver);
    }
    
    public boolean isEmailNotRegistered() {
      WebElement emailNotRegisteredTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(emailNotRegisteredTextBoxBy));
      return (emailNotRegisteredTextBox != null) ? true : false;
    }
    
    public boolean isPasswordWrong() {
      WebElement wrongPasswordTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(wrongPasswordTextBoxBy));
      return (wrongPasswordTextBox != null) ? true : false;
    }    
}
