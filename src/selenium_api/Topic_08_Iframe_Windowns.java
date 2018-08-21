package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_08_Iframe_Windowns {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait= new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("http://www.hdfcbank.com/");
		// step 1: Close popup nếu có hiển thị (switch qua iframe nếu có) - F5 (refresh page)
		// nhiều lần thì sẽ xuất hiện popup
		// Switch to iframe
		List<WebElement> notiIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if (notiIframe.size() > 0) {
			driver.switchTo().frame(notiIframe.get(0));
			WebElement closeIcon=driver.findElement(By.xpath("//*[@id='div-close']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			js.executeScript("arguments[0].click();",closeIcon);
		}
		//------------------------switch to default---------------------------
		driver.switchTo().defaultContent();
		
		// step 2: Verify đoạn text được hiển thị: What are you looking for? (switch qua iframe
		// nếu có)
		WebElement lookingforIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingforIframe);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='messageText']")).getText(),"What are you looking for?");
		
		//------------------------switch to default---------------------------
		driver.switchTo().defaultContent();

		// Step 3: Verify banner có đúng 6 images
		WebElement bannermageIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(bannermageIframe);
		By bannerImgXpath= By.xpath("//div[@id='productcontainer']//img");
		List<WebElement> Imgbanner= driver.findElements(bannerImgXpath);
		Assert.assertEquals(Imgbanner.size(), 6);
		//Check all images are presenced
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImgXpath));
	
		//------------------------switch to default---------------------------
		driver.switchTo().defaultContent();

		// Step 4: Verify flipper banner được hiển thị và có 8 items
		List<WebElement> flipperbanner= driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		int i=0;
		for(WebElement items:flipperbanner)
		{
			Assert.assertTrue(items.isDisplayed());
			i++;
			System.out.println("Items [" +i+ "] displayed!");
		}
		Assert.assertEquals(flipperbanner.size(), 8);

	}

	@Test(enabled = false)
	public void TC_02() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
