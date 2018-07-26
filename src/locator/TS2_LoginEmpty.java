package locator;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TS2_LoginEmpty 
{
	WebDriver driver;
	String Email_errmessage;
	String Pass_errmessage;
  @Test
  public void LoginEmpty()
  {
	  //Verify error message xuất hiện tại Email field:
		 System.out.println("Verify error message at Email");
		 Assert.assertEquals(Email_errmessage, "This is a required field.");
	 //Verify error message xuất hiện tại Password field:
		 System.out.println("Verify error message at Pass ");		
		 Assert.assertEquals(Pass_errmessage, "This is a required field.");
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
	  //Để trống Username/ Password
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
	  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
	  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	  //Click Login button
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  //
	  Email_errmessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
	  //
	  Pass_errmessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();

	  
  }

  @AfterClass
  public void afterClass()
  {
	  driver.quit();
  }

}
