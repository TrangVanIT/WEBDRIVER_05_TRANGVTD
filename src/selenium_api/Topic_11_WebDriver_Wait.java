package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_11_WebDriver_Wait {
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
