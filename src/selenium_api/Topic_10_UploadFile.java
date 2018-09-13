package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class Topic_10_UploadFile {
	WebDriver driver;
	// Tuyệt đối
	// String
	// filepath="F:\\AUTOMATION_TESTING\\WEBDRIVER_05_TRANGVTD\\Image\\upload.png";
	// -------------------------------------------------------------------------------
	String projectDirectory = System.getProperty("user.dir");
	String filename = "upload.png";
	String filename1 = "upload2.png";
	String filename2 = "upload3.png";
	// Tương đối
	String filepath = projectDirectory + "\\Image\\" + filename;
	String filepath1 = projectDirectory + "\\Image\\" + filename1;
	String filepath2 = projectDirectory + "\\Image\\" + filename2;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();

		// System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_SenkeyAPI() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		uploadElement.sendKeys(filepath);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + filename + "']")).isDisplayed());

		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + filename + "']")).isDisplayed());
	}

	@Test
	public void TC_01_Upload_Multiple_Files() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		String files[] = { filepath, filepath1, filepath2 };
		for (int i = 0; i < files.length; i++) {
			WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
			System.out.println("item" + i);
			uploadElement.sendKeys(files[i]);
		}
		// Check files displayed
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + filename + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + filename1 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + filename2 + "']")).isDisplayed());

		// Click start button
		List<WebElement> startbtn = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement start : startbtn) {
			start.click();
		}
		Thread.sleep(3000);
		// Check files uploaded
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + filename + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + filename1 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + filename2 + "']")).isDisplayed());
		// Check real images

		List<WebElement> images = driver.findElements(By.xpath("//table[@class='table table-striped']//img"));
		for (WebElement image : images) {
			Assert.assertTrue(checkAnyImageLoaded(image));
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean checkAnyImageLoaded(WebElement image) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (boolean) jsExecutor.executeScript("return arguments[0].complete &&"
				+ "typeof arguments[0].naturalWidth!= 'undefined' && arguments[0].naturalWidth >0", image);
	}

}
