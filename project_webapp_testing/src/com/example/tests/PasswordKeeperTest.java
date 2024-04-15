package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;


import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.UUID;
import org.junit.Test;


public class PasswordKeeperTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  
//Helper Function for generating random strings so duplicate error doesn't occur
  static String usingRandomUUID() {

	    UUID randomUUID = UUID.randomUUID();

	    return randomUUID.toString().replaceAll("_", "");

	  }
  
//Helper method for login
  private void login(String username, String password) throws InterruptedException {
      driver.get("http://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:8080/PasswordKeeper/Login.jsp");
      driver.findElement(By.name("username")).sendKeys(username);
      driver.findElement(By.name("password")).sendKeys(password);
      driver.findElement(By.xpath("//input[@type='submit']")).click();
      Thread.sleep(2000);
  }
  
  
  @Before
  public void setUp() throws Exception {
      System.setProperty("webdriver.chrome.driver", //
    		  "lib\\win\\chromedriver.exe");
      driver = new ChromeDriver();
   // driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

//Test to navigate to the Insert page
  @Test
  public void testNavigateToInsertPage() throws Exception {
      login("group", "group");
      driver.findElement(By.xpath("//a[contains(text(),'Insert')]")).click();
      Thread.sleep(2000);
      String expected = "Register";
      String result = driver.findElement(By.xpath("//h1[contains(text(),'Register')]")).getAttribute("innerHTML");
      Assert.assertEquals(expected, result);
  }

  // Test to verify logout functionality
  @Test
  public void testLogout() throws Exception {
      login("group", "group");
      driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
      Thread.sleep(2000);
      String expected = "Login";
      String result = driver.findElement(By.xpath("//h1[contains(text(),'Login')]")).getAttribute("innerHTML");
      Assert.assertEquals(expected, result);
  }

  // Test to insert invalid data and check for error message
  @Test
  public void testInsertInvalidData() throws Exception {
	  String website  = "";
	  String username = "";
	  String password = "";
    driver.get("http://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:8080/PasswordKeeper/Login.jsp");
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//a[contains(text(),'Insert')]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys(website);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys(username);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[2]/input[1]")).sendKeys(password);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[4]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    String expected = "Data has been added!<br>";
    String result = driver.findElement(By.xpath("//tbody/tr[5]/td[1]")).getAttribute("innerHTML");
    assertNotEquals(expected, result);
  }

  // Test to view data on the dashboard
  @Test
  public void testViewDashboardData() throws Exception {
      login("group", "group");
      boolean isDataPresent = driver.findElement(By.xpath("//tbody/tr[2]/td[1]")).isDisplayed(); // Adjust selector as needed
      Assert.assertTrue(isDataPresent);
  }
  
  
  @Test
  public void testLoginSuccess() throws Exception {
    driver.get("http://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:8080/PasswordKeeper/Login.jsp");
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    String expected = "Dashboard";
    String result = driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]")).getAttribute("innerHTML");
    Assert.assertEquals(expected, result);
  }
  
  @Test
  public void testLoginFailure() throws Exception {
    driver.get("http://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:8080/PasswordKeeper/Login.jsp");
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys("12455");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys("1243");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    String expected = "Username or Password are not correct!<br>";
    String result = driver.findElement(By.xpath("//tbody/tr[4]/td[1]")).getAttribute("innerHTML");
    Assert.assertEquals(expected, result);
  }

  @Test
  public void testInsertSuccess() throws Exception {
	  String website = usingRandomUUID();
	  String username = usingRandomUUID();
	  String password = usingRandomUUID();
    driver.get("http://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:8080/PasswordKeeper/Login.jsp");
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//a[contains(text(),'Insert')]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys(website);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys(username);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[2]/input[1]")).sendKeys(password);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[4]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys(website);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys(username);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[2]/input[1]")).sendKeys(password);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[4]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    String expected = "Data has been added!<br>";
    String result = driver.findElement(By.xpath("//tbody/tr[5]/td[1]")).getAttribute("innerHTML");
    Assert.assertEquals(expected, result);
  }
  @Test
  public void DeleteSuccess() throws Exception {

    driver.get("http://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:8080/PasswordKeeper/Login.jsp");
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    String expected = driver.findElement(By.xpath("//tbody/tr/td[1]")).getAttribute("innerHTML");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr[2]/td[4]/form[1]/input[2]")).click();
    Thread.sleep(2000);
    String result = driver.findElement(By.xpath("//tbody/tr/td[1]")).getAttribute("innerHTML");
    assertNotEquals(expected, result);
  }
  
  @Test
  public void testLoginEmpty() throws Exception {
    driver.get("http://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:8080/PasswordKeeper/Login.jsp");
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys("");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys("");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    String expected = "Username or Password are not correct!<br>";
    String result = driver.findElement(By.xpath("//tbody/tr[4]/td[1]")).getAttribute("innerHTML");
    assertNotEquals(expected, result);
  }
  //We fixed a bug where it would delete the first row when clicking any delete button
  @Test
  public void TestDeletionof2ndRow() throws Exception {

    driver.get("http://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:8080/PasswordKeeper/Login.jsp");
    driver.findElement(By.xpath("//tbody/tr[1]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[2]/td[2]/input[1]")).sendKeys("group");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody/tr[3]/td[1]/input[1]")).click();
    Thread.sleep(2000);
    String expected = driver.findElement(By.xpath("//tbody/tr/td[1]")).getAttribute("innerHTML");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr[3]/td[4]/form[1]/input[2]")).click();
    Thread.sleep(2000);
    String result = driver.findElement(By.xpath("//tbody/tr/td[1]")).getAttribute("innerHTML");
    Assert.assertEquals(expected, result);
  }
  
  
  
  
 
@After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
