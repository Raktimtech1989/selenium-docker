package com.newtours.test;

import com.newtours.pages.*;
import com.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers" , "expectedPrice"})
    public void setUpParameter(String noOfPassengers , String expectedPrice)
    {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void registrationPageTest()
    {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium" , "docker");
        registrationPage.enterUserCredentials("selenium" , "docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest()
    {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightDetailsPage();
    }

    @Test(dependsOnMethods =  "registrationConfirmationPageTest")
    public void flightDetailsPageTest()
    {
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
  }

  @Test(dependsOnMethods = "flightDetailsPageTest")
  public void bookFlightPageTest()
  {
      BookFlightPage bookFlightPage = new BookFlightPage(driver);
      bookFlightPage.submitFindFlightPage();
      bookFlightPage.goToFlightConfirmationPage();
  }

  @Test(dependsOnMethods =  "bookFlightPageTest")
  public void flightConfirmationPage()
  {
      FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
      String actualPrice = flightConfirmationPage.getPrice();
      Assert.assertEquals(actualPrice , expectedPrice);
  }
}