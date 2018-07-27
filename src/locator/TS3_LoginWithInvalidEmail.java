package locator;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class TS3_LoginWithInvalidEmail
{
	WebDriver driver;
  @Test
  public void LoginWithInvalidEmail () 
  {
	  
  }
  @BeforeClass
  public void beforeClass() 
  {
	  //Truy cập vào trang: http://live.guru99.com
	  driver = new FirefoxDriver();
	  driver.get("http://live.guru99.com");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  //Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  //Nhập invalid email 
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
	  //Click Login button
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterClass() 
  {
	  driver.quit();
  }

}
