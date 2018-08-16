package selenium_api;

import org.testng.annotations.Test;

import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class Topic_07_UserInteractions {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void TC_HoverMouse() {
		driver.get("http://daominhdam.890m.com/");

		// Hover chuột vào title: 'Hover over me'
		WebElement hoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		Actions action = new Actions(driver);
		action.moveToElement(hoverText).perform();

		// Verify tooltip này được hiển thị
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@role='tooltip']/div[@class='tooltip-inner']")).getText(),
				"Hooray!");

	}

	@Test(enabled = false)
	public void TC_HoverText() {
		driver.get("http://www.myntra.com/");
		// Hover chuột vào menu login
		WebElement hovermenu = driver.findElement(By.xpath("//div[@class='desktop-userActions']"));
		Actions action = new Actions(driver);
		action.moveToElement(hovermenu).perform();

		// Chọn Login button
		String login = "//a[text()='login']";
		clickElementByJavascript(login);
		// Verify Login form được hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	}

	@Test
	public void TC_Click_holdElement() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		// Click and hold từ 1-> 4
		List<WebElement> listitems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Actions moveitems = new Actions(driver);
		moveitems.clickAndHold(listitems.get(0)).moveToElement(listitems.get(3)).release().perform();
		// Sau khi chọn kiểm tra rằng có đúng 4 phần tử đã được chọn thành công với
		List<WebElement> selectableselected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(selectableselected.size(), 4);
	}

	@Test(enabled = false)
	public void TC_Doubleclick() throws InterruptedException {
		driver.get("http://www.seleniumlearn.com/double-click");
		Thread.sleep(3000);

		// Double click vào element: Double-Click Me!
		WebElement DoubleclickMe = driver.findElement(By.xpath("//button [text()='Double-Click Me!']"));
		Actions action = new Actions(driver);
		action.doubleClick(DoubleclickMe).perform();

		// Verify text trong alert được hiển thị: 'The Button was double-clicked.'
		Alert alert = driver.switchTo().alert();
		String ConfirmAlert = alert.getText();
		Assert.assertEquals(ConfirmAlert, "The Button was double-clicked.");
		Thread.sleep(3000);
		// Accept Javascript alert
		alert.accept();

	}

	// Right click to element
	@Test(enabled = false)
	public void TC_Rightclick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		// Right click vào element
		WebElement RightclickMe = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		action.contextClick(RightclickMe).perform();

		// Hover chuột vào element quit
		WebElement hoverquit = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		Actions action2 = new Actions(driver);
		action2.moveToElement(hoverquit).perform();

		// Verify element Quit (visible + hover)
		Assert.assertTrue(driver.findElement(By.xpath(
				"//li[contains(@class,'context-menu-icon-quit')and contains(@class,'context-menu-hover')and contains(@class,'context-menu-visible')]"))
				.isDisplayed());
		// Click chọn Quit
		hoverquit.click();

		// Accept Javascript alert (cannot see this popup)
		Alert alert = driver.switchTo().alert();
		String textonAlert = alert.getText();
		Assert.assertEquals(textonAlert, "clicked: quit");
		// Accept alert
		alert.accept();
	}

	// Drag and drop element
	@Test(enabled = false)
	public void TC_Drag_drop() {
		// // Case 1
		// driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		// // Kéo hình tròn nhỏ vào hình tròn lớn
		// WebElement small_circle =
		// driver.findElement(By.xpath("//*[@id='draggable']"));
		// WebElement big_circle= driver.findElement(By.xpath("//*[@id='droptarget']"));
		// Actions action3 = new Actions(driver);
		// action3.dragAndDrop(small_circle, big_circle).build().perform();
		// // Verify message đã thay đổi: You did great!
		// Assert.assertEquals(big_circle.getText(), "You did great!");
		//--------------------------------------------------------------------------
		// Case 2
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		// Kéo hình chữ nhật: Drag me to my target vào hình Drop here
		WebElement small_rectangle = driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement big_rectangle = driver.findElement(By.xpath("//*[@id='droppable']"));
		Actions action4 = new Actions(driver);
		action4.dragAndDrop(small_rectangle, big_rectangle).build().perform();
		// Verify message đã thay đổi: Dropped!
		Assert.assertEquals(big_rectangle.getText(),"Dropped!");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void clickElementByJavascript(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}

}
