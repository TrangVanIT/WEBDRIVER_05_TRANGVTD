package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.support.ui.Select;

public class Topic_04_WebElement_WebBrowserCommands 
{
	WebDriver driver;

  @BeforeClass
  public void beforeClass() 
  {
	//Truy cập vào trang: http://daominhdam.890m.com/
	  driver = new FirefoxDriver();
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  @Test
  public void TS1_dropdown_list()
  {
	  //Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
	  WebElement JobRole01_Dropdown = driver.findElement(By.xpath("//select[@id='job1']"));
	  Select select= new Select(JobRole01_Dropdown);
	  Assert.assertFalse(select.isMultiple());
	  
	  //Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible 
	  //và Kiểm tra giá trị đã được chọn thành công
	  select.selectByVisibleText("Automation Tester");
	  Assert.assertEquals(select.getFirstSelectedOption().getText(),"Automation Tester");
	  select.deselectByVisibleText("Automation Tester");
	  
	  //Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
	  select.selectByValue("manual");
	  Assert.assertEquals(select.getFirstSelectedOption().getText(),"Manual Tester");
  }
  @AfterClass
  public void afterClass() 
  {
	  driver.quit();
  }

}
