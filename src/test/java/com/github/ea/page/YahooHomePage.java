package com.github.ea.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class YahooHomePage extends AbstractPage {
    private By mailButtonBy = By.id("ybar-navigation-item-mail");
    
    public YahooHomePage(WebDriver driver) {
      super(driver);
    }

    public YahooMailPage switchToMailPage() {
      WebElement mailButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(mailButtonBy));      
      mailButton.click();      
      return new YahooMailPage(driver);
    }
 }
