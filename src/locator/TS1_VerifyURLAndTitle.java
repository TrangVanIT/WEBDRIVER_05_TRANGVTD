package locator;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TS1_VerifyURLAndTitle {
	WebDriver driver;
  @Test
  public void VerifyURLAndTitle() 
  {
	 // Kiểm tra title của Assert.assertEquals page là: "Home page"
	 // System.out.println("Check homepage title");
	 // String HomepageTitle= driver.getTitle();
	 // Assert.assertEquals(HomepageTitle, "Home page");
	 // Kiểm tra url của page đăng nhập 
	 // System.out.println("Check URL Login page ");
	 //	String LoginURL= driver.getCurrentUrl();
	 //	Assert.assertEquals(LoginURL, "http://live.guru99.com/index.php/customer/account/login/");
	 // Kiểm tra url của page tạo tài khoản 
	 System.out.println("Check URL Sign up page ");
	 String SignupURL= driver.getCurrentUrl();
	 Assert.assertEquals(SignupURL, "http://live.guru99.com/index.php/customer/account/create/");
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
	  //Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  //Back lại trang đăng nhập
	  driver.navigate().back();
	  //Forward tới trang tạo tài khoản
	  driver.navigate().forward();
	  
  }

  @AfterClass
  public void afterClass()
  {
	  driver.quit();
  }

}
