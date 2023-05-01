package com.github.ea.page;

import com.github.ea.model.Mail;
import com.github.ea.util.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.time.Duration;

public class YahooMailPage extends AbstractPage {   
    private final Logger logger = LogManager.getRootLogger();
    
    @FindBy(xpath = "//*[@data-test-id='compose-button']")
    private WebElement composeButton;

    private By mailToTextBoxBy = By.id("message-to-field");

    @FindBy(xpath = "//*[@data-test-id='compose-subject']")
    private WebElement mailSubjectTextBox;

    @FindBy(xpath = "//*[@data-test-id='rte']/div")
    private WebElement mailContentTextBox;
    
    @FindBy(xpath = "//*[@data-test-id='compose-send-button']")
    private WebElement sendMailButton;

    private By mailSentMessageTextBoxBy = By.xpath("//*[@data-test-id='navigate-button']/parent::span");
    private By unreadMailBy = By.xpath("//a[@data-test-read='false']");
    private By unreadMailFromTextBoxBy = By.xpath("//*[@data-test-id='message-from']");
    
    @FindBy(xpath = "//*[@data-test-id='message-view-body-content']")
    private WebElement unreadMailContentTextBox;
            
    public YahooMailPage(WebDriver driver) {
      super(driver);
      PageFactory.initElements(this.driver, this);
    }
    
    public boolean isPageOpen() {      
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
      return (null != wait.until(ExpectedConditions.visibilityOf(composeButton))) ? true : false;
    }

    public YahooMailPage sendNewMail(Mail mail) {
      composeButton.click();
      WebElement mailToTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(mailToTextBoxBy));
      mailToTextBox.sendKeys(mail.getEmail());
      mailSubjectTextBox.sendKeys(mail.getSubject());
      mailContentTextBox.sendKeys(mail.getContent());
      sendMailButton.click();
      logger.info("New Mail is sent");
      return this;
    }
    
    public boolean isMailSent() {
      WebElement mailSentMessageTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(mailSentMessageTextBoxBy));
      return (mailSentMessageTextBox != null) ? true : false;
    }
    
    public Mail checkMail(String subjectOfMail) {
      WebElement mail = isMailReceived(subjectOfMail);
      if (mail == null) {
        return null;		
      }            
      mail.click();      
      
      WebElement unreadMailFromTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(unreadMailFromTextBoxBy));
      String mailEmail = StringUtils.extractEmailForYahoo(unreadMailFromTextBox.getText());
      String mailContent = StringUtils.extractMailContentForYahoo(unreadMailContentTextBox.getText());
      return new Mail(mailEmail, subjectOfMail, mailContent);
    }
    
    private WebElement isMailReceived(String subjectOfMail) {
      List<WebElement> unreadMails = getUnreadMails();
      for (WebElement unreadMail : unreadMails) {
        if (unreadMail.getAttribute("aria-label").contains(subjectOfMail)) {
          return unreadMail;
        }
      }
      return null;      
    }
    
    private List<WebElement> getUnreadMails() {
      return driver.findElements(unreadMailBy);
    }
}
