package selenium_api;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_11_WebDriver_Wait {
	WebDriver driver;
	WebDriverWait waitExplicit;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button [text()='Start']")).click();
		String helloText = driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText();
		Assert.assertEquals(helloText, "Hello World!");

	}

	@Test
	public void TC_02() {
		//
		driver.get(
				"http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// Wait cho "Date Time Picker" được hiển thị
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));

		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='raDiv']")));
		// In ra ngày đã chọn (Before AJAX call)
		WebElement todayBefore = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(todayBefore.getText(), "No Selected Dates to display.");

		// Chọn ngày hiện tại
		driver.findElement(By.xpath("//a[text()='23']//parent::td")).click();

		// Wait cho đến khi "loader ajax" không còn visible
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		// Wait cho selected date = 23 được visible
		waitExplicit.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[text()='23']//parent::td[@class='rcSelected']")));
		Assert.assertTrue(
				driver.findElement(By.xpath("//a[text()='23']//parent::td[@class='rcSelected']")).isDisplayed());

		// Verify ngày đã chọn bằng = Saturday, September 23, 2017
		WebElement todayAfter = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(todayAfter.getText(), "Sunday, September 23, 2018");

	}

	@Test
	public void TC_03() {
		driver.get("https://daominhdam.github.io/fluent-wait/");
		// Wait cho đến khi countdown time được visible
		WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitExplicit.until(ExpectedConditions.visibilityOf(countdown));
		// Mỗi 1s kiểm tra countdount= 00 được xuất hiện trên page hay chưa (giây đếm
		// ngược về 00)
		new FluentWait<WebElement>(countdown)
				// Tổng time wait là 15s
				.withTimeout(12, TimeUnit.SECONDS)
				// Tần số mỗi 1s check 1 lần
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				// Nếu gặp exception là find ko thấy element sẽ bỏ qua
				.ignoring(NoSuchElementException.class)
				// Kiểm tra điều kiện
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						// Kiểm tra điều kiện countdount = 00
						boolean flag = element.getText().endsWith("00");
						System.out.println("Time = " + element.getText());
						// return giá trị cho function apply
						return flag;
					}
				});
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
