package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_06_Button_RadioButton_Checkbox_Alert {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void TC_01() {
		driver.get("http://live.guru99.com/");
		Assert.assertEquals(driver.getTitle(), "Home page");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");

		// Click vào button CREATE AN ACCOUNT (Sử dụng Javascript Executor code)
		
		String creatacc= "//a[@class='button']";
		clickElementByJavascript(creatacc);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");

	}

	@Test(enabled = false)
	public void TC_02() 
	{
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		//Click vào checkbox: Dual-zone air conditioning (Thẻ input ko được sử dụng thuộc tính id)
		String dualzone_checkbox= "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		clickElementByJavascript(dualzone_checkbox);
		//Kiểm tra checkbox đó đã chọn
		Assert.assertTrue(isElementselected(dualzone_checkbox));
		//Sau khi checkbox đã được chọn - deselect nó và kiểm tra nó chưa được chọn
		if(isElementselected(dualzone_checkbox))
		{
			clickElementByJavascript(dualzone_checkbox);
			Assert.assertTrue(!isElementselected(dualzone_checkbox));
		}

	}
	@Test(enabled = false)
	public void TC_03() 
	{
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		//Click vào radiobutton:  2.0 Petrol, 147kW (Thẻ input ko được sử dụng thuộc tính id)
		String Petrol_RadioButton= "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		//Kiểm tra radio button đó đã chọn hay chưa/ nếu chưa chọn lại
		if(!isElementselected(Petrol_RadioButton))
		{
			clickElementByJavascript(Petrol_RadioButton);
		}
	}
	
	@Test(enabled = false)
	public void TC_04() 
	{
		driver.get("http://daominhdam.890m.com/");
		//Click vào button: Click for JS Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		//Verify message hiển thị trong alert là: I am a JS Alert
		Alert alert= driver.switchTo().alert();
		String textonAlert= alert.getText();
		Assert.assertEquals(textonAlert, "I am a JS Alert");
		//Accept alert và verify message hiển thị tại Result là:  You clicked an alert successfully
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
	}
	@Test(enabled = false)
	public void TC_05() throws InterruptedException 
	{
		driver.get("http://daominhdam.890m.com/");
		//Click vào button: Click for JS Confirm
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		//Verify message hiển thị trong alert là: I am a JS Confirm
		Alert alert= driver.switchTo().alert();
		String ConfirmAlert= alert.getText();
		Assert.assertEquals(ConfirmAlert, "I am a JS Confirm");
		Thread.sleep(3000);
		//Accept alert và verify message hiển thị tại Result là:  You clicked: Cancel
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}
	@Test
	public void TC_06() throws InterruptedException 
	{
		driver.get("http://daominhdam.890m.com/");
		//Click vào button: Click for JS Prompt
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		//Verify message hiển thị trong alert là: I am a JS prompt
		Alert alert= driver.switchTo().alert();
		String ConfirmAlert= alert.getText();
		Assert.assertEquals(ConfirmAlert, "I am a JS prompt");
		Thread.sleep(3000);
		//Accept alert và verify message hiển thị tại Result là:  You clicked: Cancel
		alert.sendKeys("pratice alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: pratice alert");
	}
	@AfterClass
	
	public void afterClass() {
		driver.quit();
	}

	public void clickElementByJavascript(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}
	public boolean isElementselected (String locator)
	{
		WebElement element= driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

}
