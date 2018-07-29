package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class Topic_03_XpathCss2 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//
		// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();

		// System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();

		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test(enabled = false)
	public void TC_01() {
		driver.get("http://daominhdam.890m.com/");
		// Email
		WebElement email = driver.findElement(By.xpath("//input[@id='mail']"));
		Assert.assertTrue(email.isDisplayed());
		email.sendKeys("Automation Testing ");

		// Age (Under 18)
		WebElement age = driver.findElement(By.xpath("//input[@id='under_18']"));
		Assert.assertTrue(age.isDisplayed());
		age.click();

		// Education
		WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		Assert.assertTrue(education.isDisplayed());
		education.sendKeys("Automation Testing ");
	}

	@Test(enabled = false)
	public void TC_02() {
		driver.get("http://daominhdam.890m.com/");
		// -------------------------------ENABLE ELEMENT------------------------------
		// Email
		WebElement email = driver.findElement(By.xpath("//input[@id='mail']"));
		if (email.isEnabled()) {
			System.out.print("\nEmail is enable");
		} else {
			System.out.print("\nEmail is disable");
		}

		// Age (Under 18)
		WebElement age = driver.findElement(By.xpath("//input[@id='under_18']"));
		if (age.isEnabled()) {
			System.out.print("\nAge(Under 18) is enable");
		} else {
			System.out.print("\nAge(Under 18) is disable");
		}

		// Education
		WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if (education.isEnabled()) {
			System.out.print("\nEducation is enable");
		} else {
			System.out.print("\nEducation is disable");
		}

		// Job Role 01
		WebElement Job1 = driver.findElement(By.xpath("//select[@id='job1']"));
		if (Job1.isEnabled()) {
			System.out.print("\nJob Role 01 is enable");
		} else {
			System.out.print("\nJob Role 01 is disable");
		}
		// Interests (Development)
		WebElement Dev_Interests = driver.findElement(By.xpath("//input[@id='development']"));
		if (Dev_Interests.isEnabled()) {
			System.out.print("\nInterests (Development) is enable");
		} else {
			System.out.print("\nInterests (Development)is disable");
		}
		// Slider 01
		WebElement Slider1 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		if (Slider1.isEnabled()) {
			System.out.print("\nSlider 01 is enable");
		} else {
			System.out.print("\nSlider 01 is disable");
		}
		// Button enable
		WebElement btn2 = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		if (btn2.isEnabled()) {
			System.out.print("\nButton is enable");
		} else {
			System.out.print("\nButton is disable");
		}
		// -------------------------------DISABLED ELEMENT------------------------------

		// Password
		WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
		if (!pass.isEnabled()) {
			System.out.print("\nPassword is disable");
		} else {
			System.out.print("\nPassword is enable");
		}
		// Age (Radiobutton is disabled)
		WebElement age_radiobtton = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		if (!age_radiobtton.isEnabled()) {
			System.out.print("\nAge (Radiobutton is disabled) is disable");
		} else {
			System.out.print("\nAge (Radiobutton is disabled) is enable");
		}
		// Biographys
		WebElement biography = driver.findElement(By.xpath("//textarea[@id='bio']"));
		if (!biography.isEnabled()) {
			System.out.print("\nBiographys is disable");
		} else {
			System.out.print("\nBiographys is enable");
		}
		// Job Role 02
		WebElement job2 = driver.findElement(By.xpath("//select[@id='job2']"));
		if (!job2.isEnabled()) {
			System.out.print("\nJob Role 02 is disable");
		} else {
			System.out.print("\nJob Role 02 is enable");
		}
		// Interests (Checkbox is disabled)
		WebElement interest = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		if (!interest.isEnabled()) {
			System.out.print("\nInterests is disable");
		} else {
			System.out.print("\nInterests is enable");
		}
		// Slider 02
		WebElement slider2 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		if (!slider2.isEnabled()) {
			System.out.print("\nSlider 02  is disable");
		} else {
			System.out.print("\nSlider 02  is enable");
		}
		// Button
		WebElement btn1 = driver.findElement(By.xpath("//button [@id='button-disabled']"));
		if (!btn1.isEnabled()) {
			System.out.print("\nPassword is disable");
		} else {
			System.out.print("\nPassword is enable");
		}

	}

	@Test
	public void TC_03() throws InterruptedException {

		// Age (Under 18)
		WebElement age = driver.findElement(By.xpath("//input[@id='under_18']"));
		age.click();
		Assert.assertTrue(age.isSelected());
		// Thread.sleep(3000);
		if (!age.isSelected()) {
			age.click();
		}

		// Interests (Development)
		WebElement Dev_Interests = driver.findElement(By.xpath("//input[@id='development']"));
		Dev_Interests.click();
		Assert.assertTrue(Dev_Interests.isSelected());
		if (Dev_Interests.isSelected()) {
			Dev_Interests.click();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
