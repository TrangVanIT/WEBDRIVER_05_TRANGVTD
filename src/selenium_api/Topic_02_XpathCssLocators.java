package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_XpathCssLocators {
	WebDriver driver;
	String firstname, middlename, lastname, email, pass, confirmpass;

	By FnameTextbox = By.xpath("//input[@id='firstname']");
	By MidnameTextbox = By.xpath("//input[@id='middlename']");
	By LnameTextbox = By.xpath("//input[@id='lastname']");
	By EmailTextbox = By.xpath("//input[@id='email_address']");
	By PassTextbox = By.xpath("//input[@id='password']");
	By ConfirmPassTextbox = By.xpath("//input[@id='confirmation']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstname = "trang";
		middlename = "thi";
		lastname = "van";
		email = "doantrang" + RandomUniqueNumber() + "@gmail.com";
		pass = "123456";
		confirmpass = "123456";

	}

	@Test(enabled = false)
	public void TC1_VerifyURLAndTitle() {
		// Truy cập vào trang: http://live.guru99.com
		driver.get("http://live.guru99.com");
		// Kiểm tra title của Assert.assertEquals page là: "Home page"
		System.out.println("Check homepage title");
		String HomepageTitle = driver.getTitle();
		Assert.assertEquals(HomepageTitle, "Home page");

		// Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Back lại trang đăng nhập
		driver.navigate().back();
		// Kiểm tra url của page đăng nhập
		System.out.println("Check URL Login page ");
		String LoginURL = driver.getCurrentUrl();
		Assert.assertEquals(LoginURL, "http://live.guru99.com/index.php/customer/account/login/");

		// Forward tới trang tạo tài khoản
		driver.navigate().forward();
		// Kiểm tra url của page tạo tài khoản
		System.out.println("Check URL Sign up page ");
		String SignupURL = driver.getCurrentUrl();
		Assert.assertEquals(SignupURL, "http://live.guru99.com/index.php/customer/account/create/");
	}

	@Test(enabled = false)
	public void LoginEmpty() {
		driver.get("http://live.guru99.com");

		// Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		// Để trống Username/ Password
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");

		// Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Get error message at Email field and Verify error message xuất hiện tại Email
		// field:
		WebElement Email_errmessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
		Email_errmessage.getText();
		System.out.println("Verify error message at Email");
		Assert.assertTrue(Email_errmessage.isDisplayed());

		// Get error message at Password field and Verify error message xuất hiện tại
		// Password field:
		WebElement Pass_errmessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']"));
		Pass_errmessage.getText();
		System.out.println("Verify error message at Pass ");
		Assert.assertTrue(Pass_errmessage.isDisplayed());
	}

	@Test(enabled = false)
	public void LoginWithInvalidEmail() {
		driver.get("http://live.guru99.com");
		// Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		// Nhập invalid email
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");

		// Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		// Verify error message
		WebElement Email_errmessage1 = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
		Email_errmessage1.getText();
		System.out.println("Verify error message at Email");
		Assert.assertTrue(Email_errmessage1.isDisplayed());

	}

	@Test(enabled = false)
	public void LoginWithInvalidPass() {
		driver.get("http://live.guru99.com");
		// Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		// Nhập email correct and password incorrect
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");

		// Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		// Verify error message
		WebElement Pass_errmessage1 = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']"));
		Pass_errmessage1.getText();
		Assert.assertTrue(Pass_errmessage1.isDisplayed());

	}

	@Test
	public void CreateAccount() throws InterruptedException {
		driver.get("http://live.guru99.com");

		// Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Nhập data
		driver.findElement(FnameTextbox).clear();
		driver.findElement(FnameTextbox).sendKeys(firstname);
		driver.findElement(MidnameTextbox).sendKeys(middlename);
		driver.findElement(LnameTextbox).sendKeys(lastname);
		driver.findElement(EmailTextbox).sendKeys(email);
		driver.findElement(PassTextbox).sendKeys(pass);
		driver.findElement(ConfirmPassTextbox).sendKeys(confirmpass);

		// Click Register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		// Verify error message
		WebElement Success_message = driver
				.findElement(By.xpath("//div[@class='page']//li[@class='success-msg']//span"));
		Success_message.getText();
		Assert.assertTrue(Success_message.isDisplayed());

		// Logout
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Thread.sleep(5000);

		// Verify Home Page
		// System.out.println("Check homepage?");
		// String HomepageTitle= driver.getTitle();
		// Assert.assertEquals(HomepageTitle, "Home page");
		// div[@class='header-language-container']//p[@class='welcome-msg']

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int RandomUniqueNumber() {
		Random rand = new Random();
		int number = rand.nextInt(2222) + 1;
		return number;
	}

}
