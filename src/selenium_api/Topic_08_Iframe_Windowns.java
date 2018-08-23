package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
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
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void TC_01() {
		driver.get("http://www.hdfcbank.com/");
		// step 1: Close popup nếu có hiển thị (switch qua iframe nếu có) - F5 (refresh
		// page)
		// nhiều lần thì sẽ xuất hiện popup
		// Switch to iframe
		List<WebElement> notiIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if (notiIframe.size() > 0) {
			driver.switchTo().frame(notiIframe.get(0));
			WebElement closeIcon = driver.findElement(By.xpath("//*[@id='div-close']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", closeIcon);
		}
		// ------------------------switch to default---------------------------
		driver.switchTo().defaultContent();

		// step 2: Verify đoạn text được hiển thị: What are you looking for? (switch qua
		// iframe
		// nếu có)
		WebElement lookingforIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingforIframe);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='messageText']")).getText(),
				"What are you looking for?");

		// ------------------------switch to default---------------------------
		driver.switchTo().defaultContent();

		// Step 3: Verify banner có đúng 6 images
		WebElement bannermageIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(bannermageIframe);
		By bannerImgXpath = By.xpath("//div[@id='productcontainer']//img");
		List<WebElement> Imgbanner = driver.findElements(bannerImgXpath);
		Assert.assertEquals(Imgbanner.size(), 6);
		// Check all images are presenced
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImgXpath));

		// ------------------------switch to default---------------------------
		driver.switchTo().defaultContent();

		// Step 4: Verify flipper banner được hiển thị và có 8 items
		List<WebElement> flipperbanner = driver
				.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		int i = 0;
		for (WebElement items : flipperbanner) {
			Assert.assertTrue(items.isDisplayed());
			i++;
			System.out.println("Items [" + i + "] displayed!");
		}
		Assert.assertEquals(flipperbanner.size(), 8);

	}

	@Test(enabled = false)
	public void TC_02() {
		driver.get("http://daominhdam.890m.com/");
		//driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

		// Step 02 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		String parentWindow = driver.getWindowHandle();
		switchToChildWindow(parentWindow);
		//driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);

		// Step 03 - Kiểm tra title của window mới = Google
		Assert.assertEquals(driver.getTitle(), "Google");

		// Step 04 - Close window mới
		closeAllWithoutParentWindows(parentWindow);

		// Step 05 - Switch về parent window
		switchToParentWindow(parentWindow);

	}

	@Test
	public void TC_03() {
		driver.get("http://www.hdfcbank.com/");
		String parentGUID=driver.getWindowHandle();
		
		// Step 02 - Kiểm tra và close quảng cáo nếu có xuất hiện
		List<WebElement> notiIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if (notiIframe.size() > 0) {
			driver.switchTo().frame(notiIframe.get(0));
			WebElement closeIcon = driver.findElement(By.xpath("//*[@id='div-close']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", closeIcon);
			driver.switchTo().defaultContent();
		}
		// Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
		driver.findElement(By.xpath("//a[text()='Agri']")).click();	
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");

		// Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua
		// tab mới
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		
		// Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới
		// -> Switch qua tab mới
		WebElement PrivacyPolicy = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(PrivacyPolicy);
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		
		// Step 06- Click CSR link on Privacy Policy page		
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		
		// Step 08 - Close tất cả popup khác - chỉ giữ lại parent window
		closeAllWithoutParentWindows(parentGUID);
		Assert.assertEquals(driver.getTitle(),"HDFC Bank: Personal Banking Services");
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void switchToChildWindow(String parent) {
		// Get all current windown
		Set<String> allWindows = driver.getWindowHandles();
		// Duyệt qua tất cả window/tab
		for (String runWindow : allWindows) {
			// Nếu ko giống vs parent thì switch qua
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToParentWindow(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			// Nếu là parent thì switch qua
			if (runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String expectedTitle) {
		// Get all current windown
		Set<String> allWindows = driver.getWindowHandles();
		// Duyệt qua tất cả window/tab
		for (String runWindows : allWindows) 
		{
			//Switch qua từng window trước
			driver.switchTo().window(runWindows);
			//Get current title của window/tab đã switch qua
			String currentWin = driver.getTitle();
			//Nếu đúng là title =>break
			if (currentWin.equals(expectedTitle)) {
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

}
