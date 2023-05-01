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

public class MailRuLoginPage extends AbstractPage {    
    private final String PAGE_URL = "https://account.mail.ru";
    private final Logger logger = LogManager.getRootLogger();

    private By accountTextBoxBy = By.name("username");
    
    @FindBy(xpath = "//*[@data-test-id='next-button']")
    private WebElement enterPasswordButton;
    
    private By passwordTextBoxBy = By.name("password");
    
    @FindBy(xpath = "//*[@data-test-id='submit-button']")
    private WebElement signInButton;

    private By accountNotRegisteredTextBoxBy = By.xpath("//*[@data-test-id='accountNotRegistered']");
    private By wrongPasswordTextBoxBy = By.xpath("//*[@data-test-id='password-input-error']"); 
    private By accountEmptyTextBoxBy = By.xpath("//*[@data-test-id='required']");           
    
    public MailRuLoginPage(WebDriver driver) {
      super(driver);
      PageFactory.initElements(this.driver, this);
    }
    
    public MailRuLoginPage openPage(){
      driver.navigate().to(PAGE_URL);
      logger.info("MailRu Login page opened");
      return this;
    }
        
    public MailRuLoginPage enterEmail(String email) {
      WebElement accountTextBox = new WebDriverWait(driver,Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(accountTextBoxBy));
      accountTextBox.sendKeys(email);
      enterPasswordButton.click();
      return this;
    }
    
    public MailRuMailPage enterPassword(String password) {
      WebElement passwordTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(passwordTextBoxBy));
      passwordTextBox.sendKeys(password);
      signInButton.click();
      logger.info("Login performed");
      return new MailRuMailPage(driver);
    }

    public boolean isAccountNotRegistered() {
      WebElement accountNotRegisteredTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(accountNotRegisteredTextBoxBy));
      return accountNotRegisteredTextBox.isDisplayed();
    }    
    
    public boolean isPasswordWrong() {
      WebElement wrongPasswordTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(wrongPasswordTextBoxBy));
      return wrongPasswordTextBox.isDisplayed();
    }    
    
    public boolean isAccountEmpty() {
      WebElement accountEmptyTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(accountEmptyTextBoxBy));
      return accountEmptyTextBox.isDisplayed();
    }
}
