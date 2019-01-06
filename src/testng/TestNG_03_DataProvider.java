package testng;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestNG_03_DataProvider {
	WebDriver driver;

	@BeforeClass
	public void setupBrowser() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@DataProvider
	public Object[][] EmailAndPass() {
		return new Object[][] { { "trangvtdtest@gmail.com", "123456" }, { "trangtest2@gmail.com", "123456" } };
	}

	@Test(dataProvider = "EmailAndPass")
	public void TC_01_LoginToSystem(String email, String pass) 
	{
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+email+ "')]")).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();	
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for ')]")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
