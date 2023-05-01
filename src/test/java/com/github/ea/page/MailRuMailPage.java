package com.github.ea.page;

import com.github.ea.model.Mail;
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
import java.util.List;

public class MailRuMailPage extends AbstractPage {    
    private final Logger logger = LogManager.getRootLogger();

    private By composeButtonBy = By.xpath("//*[@href='/compose/']");
    private By mailToTextBoxBy = By.xpath("//*[@data-type='to']//input");

    @FindBy(name = "Subject")
    private WebElement mailSubjectTextBox;
    
    @FindBy(xpath = "//*[@role='textbox']//div")
    private WebElement mailContentTextBox;
    
    private By sendMailButtonBy = By.xpath("//*[@data-test-id='send']");
    private By mailSentMessageTextBoxBy = By.className("layer-sent-page");  
    private By unreadMailBy = By.xpath("//*[contains(@class, 'subject_unread')]");
    private By unreadMailFromTextBoxBy = By.xpath("//*[@class='letter__author']//*[contains(@class,'letter-contact')]");
            
    @FindBy(className = "letter-body")
    private WebElement unreadMailContentTextBox;
            
    public MailRuMailPage(WebDriver driver) {
      super(driver);
      PageFactory.initElements(this.driver, this);
    }
    
    public boolean isPageOpen() {
      WebElement composeButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(composeButtonBy));
      return (composeButton != null) ? true : false;
    }

    public MailRuMailPage sendNewMail(Mail mail) {
      WebElement composeButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(composeButtonBy));
      composeButton.click();            
      WebElement mailToTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(mailToTextBoxBy));
      mailToTextBox.sendKeys(mail.getEmail());	
      mailSubjectTextBox.sendKeys(mail.getSubject());
      mailContentTextBox.sendKeys(mail.getContent());
      WebElement sendMailButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(sendMailButtonBy));      
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
      String mailFrom = unreadMailFromTextBox.getAttribute("title");
      String mailContent = unreadMailContentTextBox.getText();      
      return new Mail(mailFrom, subjectOfMail, mailContent);      
    }
        
    private WebElement isMailReceived(String subjectOfMail) {
      List<WebElement> unreadMails = getUnreadMails();
      for (WebElement unreadMail : unreadMails) {
        if (unreadMail.getText().equals(subjectOfMail)) {
          return unreadMail;
        }
      }
      return null;
    }
    
    private List<WebElement> getUnreadMails() {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
      wait.until(ExpectedConditions.presenceOfElementLocated(unreadMailBy));
      return driver.findElements(unreadMailBy);
    }
}
