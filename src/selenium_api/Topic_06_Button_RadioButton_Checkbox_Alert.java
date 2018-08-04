package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_06_Button_RadioButton_Checkbox_Alert 
{
	WebDriver driver;
  @BeforeClass
  public void beforeClass() 
  {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
  }
  @Test
  public void TC_01() 
  {
	  driver.get("http://live.guru99.com/");
	  Assert.assertEquals(driver.getTitle(), "Home page");
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
	  
	  //Click vào button CREATE AN ACCOUNT (Sử dụng Javascript Executor code)
	  WebElement enable = driver.findElement(By.id("choose_selenium_btn"));
	  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", enable);
  }
  
  @Test
  public void TC_02() 
  {
	  
  }

  @AfterClass
  public void afterClass() 
  {
		driver.quit();
  }


}
