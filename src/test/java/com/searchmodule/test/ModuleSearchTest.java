package com.searchmodule.test;

import com.searchmodueles.pages.SearchPage;
import com.tests.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ModuleSearchTest extends BaseTest {

    private WebDriverWait wait;

     @Test
     @Parameters({"keyWord"})
     public void searchContent(String keyWord)
     {
         SearchPage searchPage = new SearchPage(driver);
         searchPage.goTo();
         searchPage.doSearch(keyWord);
         searchPage.goToVideos();
         int size = searchPage.getResult();
         Assert.assertTrue(size > 0);
     }

}
