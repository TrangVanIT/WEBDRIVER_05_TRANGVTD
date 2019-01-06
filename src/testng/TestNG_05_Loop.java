package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestNG_05_Loop {
	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void setupBrowser(String browserName) {
		System.out.println("Browser Name + " + browserName);
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();		
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}else if (browserName.equals("chromeheadless"))
		{
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		    ChromeOptions chromeOptions = new ChromeOptions();
		    chromeOptions.addArguments("--headless");
		    driver = new ChromeDriver(chromeOptions);
		}

		driver.get("http://live.guru99.com/index.php/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Parameters({"email","password"})
	@Test(invocationCount=2)
	public void TC_01_LoginToSystem(String Email, String Pass) {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Pass);
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+Email+ "')]")).isDisplayed());

		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();	
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for ')]")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
