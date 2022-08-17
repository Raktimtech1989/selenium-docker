package com.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.*;
import org.testng.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setUpDriver() throws MalformedURLException {
       String host = "localhost";
       DesiredCapabilities cap = new DesiredCapabilities();
        //cap.setBrowserName(BrowserType.CHROME);
     if(System.getProperty("BROWSER") != null &&
        System.getProperty("BROWSER").equalsIgnoreCase("firefox"))
        {
            cap.setBrowserName(BrowserType.FIREFOX);
            System.out.println("**EXECUTION STARTED** "  +  System.getProperty("BROWSER"));
        }
         else if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("chrome"))
        {
            cap.setBrowserName(BrowserType.CHROME);
            System.out.println("**EXECUTION STARTED** "  +  System.getProperty("BROWSER"));
        }

     if(System.getProperty("HUB_HOST") != null )
        {
              host = System.getProperty("HUB_HOST");
        }
        //String testName = ctx.getCurrentXmlTest().getName();

       // options.addArguments("--headless");
        String completeURL =  "http://" + host + ":4444/wd/hub";
        //cap.setCapability("name" , testName);
        this.driver = new RemoteWebDriver(new URL(completeURL)  , cap );
    }

    @AfterTest
    public void quitDriver()
    {
        this.driver.quit();
    }
}
