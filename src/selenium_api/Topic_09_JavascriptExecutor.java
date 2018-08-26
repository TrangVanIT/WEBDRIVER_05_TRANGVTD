package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_JavascriptExecutor {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void TC_01() {

		// driver.get("http://live.guru99.com/");
		openAnyUrlByJS("http://live.guru99.com/");
		// Step 02 - Sử dụng JE để get domain của page (Verify domain = live.guru99.com)
		String Gurudomain = (String) executeJSForBrowser("return document.domain;");
		System.out.println("domain name: " + Gurudomain);
		Assert.assertEquals(Gurudomain, "live.guru99.com");

		// Step 03 - Sử dụng JE để get URL của page(Verify URL =http://live.guru99.com/)
		String GuruUrl = (String) executeJSForBrowser("return document.URL;");
		System.out.println("Url: " + GuruUrl);
		Assert.assertEquals(GuruUrl, "http://live.guru99.com/");
		// Step 04 - Open MOBILE page (Sử dụng JE)
		WebElement mobile = driver.findElement(By.xpath("//a[text()='Mobile']"));
		executeJSForWebElement(mobile);
		// verify mobile page
		String mobileTitle = (String) executeJSForBrowser("return document.title;");
		System.out.println("Mobile page title: " + mobileTitle);
		Assert.assertEquals(mobileTitle, "Mobile");

		// Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button
		// bằng JE)(Hướng dẫn: sử dụng following-sibling)
		WebElement AddSamsungToCart = driver.findElement(
				By.xpath("//h2[a[text()='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
		executeJSForWebElement(AddSamsungToCart);

		// Step 06 - Verify message được hiển thị: Samsung Galaxy was added to your
		// shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
		String SamsungAddedMsg = (String) executeJSForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(SamsungAddedMsg.contains("Samsung Galaxy was added to your shopping cart."));

		// Step 07 - Open PRIVACY POLICY page (Sử dụng JE)(Verify title của page =
		// Privacy Policy (Sử dụng JE))
		WebElement privacyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		executeJSForWebElement(privacyLink);
		String privacyTitle = (String) executeJSForBrowser("return document.title;");
		Assert.assertEquals(privacyTitle, "Privacy Policy");
		// Step 08 - Srcoll xuống cuối page
		scrollToBottomPage();

		// Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath:
		// WISHLIST_CNT The number of items in your Wishlist.
		// Hướng dẫn: sử dụng following-sibling
		// th[text()='WISHLIST_CNT']/following-sibling::td
		WebElement wishlistTableContent = driver.findElement(By.xpath(
				"//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertTrue(wishlistTableContent.isDisplayed());
		// Step 10 - Navigate tới domain: http://demo.guru99.com/v4/ (Sử dụng JE)
		// Verify domain sau khi navigate = demo.guru99.com
		openAnyUrlByJS("http://demo.guru99.com/v4/");
		String DemoGuru99Domain = (String) executeJSForBrowser("return document.domain;");
		Assert.assertEquals(DemoGuru99Domain, "demo.guru99.com");
	}

	@Test
	public void TC_02() {
		// Step 01 - Truy cập vào trang:
		// https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
		openAnyUrlByJS("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		WebElement resultIframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(resultIframe);
		// Step 02 - Remove thuộc tính disabled của field Last name(Switch qua iframe
		// nếu có)
		WebElement lastnameField = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeInDOM("disabled", lastnameField);

		// Step 03 - Sendkey vào field Last name (Eg. Automation Testing)
		lastnameField.sendKeys("Automation Testing");

		// Step 04 - Click Submit button
		WebElement submitbtn = driver.findElement(By.xpath("//input[@type='submit']"));
		// submitbtn.click();
		executeJSForWebElement(submitbtn);
		// Step 05 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field
		// Lastname (Automation Testing)
		String lastnametxt = driver.findElement(By.xpath("//div[@class='w3-container w3-large w3-border']")).getText();
		Assert.assertTrue(lastnametxt.contains("Automation Testing"));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public Object openAnyUrlByJS(String Url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + Url + "'");
	}

	public Object highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].style.border='6px groove red'", element);
	}

	public Object executeJSForBrowser(String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public Object executeJSForWebElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object removeAttributeInDOM(String attribute, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}

	public Object scrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

}
