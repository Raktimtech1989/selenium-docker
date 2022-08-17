package com.searchmodueles.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {
private WebDriver driver;
private WebDriverWait wait;

@FindBy(name = "q")
private WebElement searchTextBox;

@FindBy(linkText="Videos")
private WebElement videoLink;

@FindBy(xpath="//input[@type='submit']")
private WebElement searchBtn;

@FindBy(className = "tile--vid")
private List<WebElement> allVideos;

public SearchPage(WebDriver driver)
{
    this.driver = driver;
    this.wait = new WebDriverWait(driver , 30);
    PageFactory.initElements(driver , this);

}
public void goTo()
{
    this.driver.get("https://duckduckgo.com");
    this.driver.manage().window().maximize();
    this.driver.manage().deleteAllCookies();
}
public void doSearch(String keyword)
{
   this.wait.until(ExpectedConditions.visibilityOf(this.searchTextBox));
   this.searchTextBox.sendKeys(keyword);
   this.searchBtn.click();
}

public void goToVideos()
{
    this.wait.until(ExpectedConditions.visibilityOf(this.videoLink));
    this.videoLink.click();
}

public int getResult()
{
    By by = By.className("tile--vid");
    this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by , 0));
    System.out.println(this.allVideos.size());
    return this.allVideos.size();

}

}
