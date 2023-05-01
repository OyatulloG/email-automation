package com.github.ea.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {
    private static WebDriver driver;
    
    private DriverSingleton(){
    }
    
    public static WebDriver getDriver(){
      if (null == driver){
        switch (System.getProperty("browser")){
          case "firefox": {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            break;
          }
          default: {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);
          }
        }
        driver.manage().window().maximize();
      }
      return driver;
    }
    
    public static void closeDriver(){
      driver.quit();
      driver = null;
    }
}
