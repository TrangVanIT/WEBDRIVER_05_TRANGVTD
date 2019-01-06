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
import org.openqa.selenium.support.ui.Select;

public class Topic_04_Textbox_Textarea_Dropdown {
	WebDriver driver;
	String name, dob, address, city, state, pin, phonenumber, email, pass, customerid = null, newaddress, newcity;

	By NameTxb = By.xpath("//input[@name='name']");
	By DOBTxb = By.xpath("//input[@id='dob']");
	By AddressTextarea = By.xpath("//textarea[@name='addr']");
	By CityTxb = By.xpath("//input[@name='city']");
	By StateTxb = By.xpath("//input[@name='state']");
	By PinTxb = By.xpath("//input[@name='pinno']");
	By MobileNumberTxb = By.xpath("//input[@name='telephoneno']");
	By EmailTxb = By.xpath("//input[@name='emailid']");
	By PassTxb = By.xpath("//input[@name='password']");

	@BeforeClass
	public void beforeClass() 
	{
		// Truy cập vào trang: http://daominhdam.890m.com/
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		name = "trang";
		dob = "1994-01-01";
		address = "123 xa ba";
		city = "Tam Ky";
		state = "Dong Giang";
		pin = "233233";
		phonenumber = "0962345567";
		email = "trang" + RandomUniqueNumber() + "@gmail.com";
		pass = "123456";
		newaddress = "xa ba edit";
		newcity = "Tam Ky edit";
	}
	@Test(enabled = false)
	public void TS1_dropdown_list() throws InterruptedException {
		driver.get("http://daominhdam.890m.com/");
		// Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
		WebElement JobRole01_Dropdown = driver.findElement(By.xpath("//select[@id='job1']"));
		Select select = new Select(JobRole01_Dropdown);
		Assert.assertFalse(select.isMultiple());

		// Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		// và Kiểm tra giá trị đã được chọn thành công
		select.selectByVisibleText("Automation Tester");

		// Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");

		// Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");

		// Kiểm tra dropdown có 5 giá trị:
		Assert.assertEquals(select.getOptions().size(), 5);
		Thread.sleep(3000);
	}

	// textbox/ textarea
	// account: mngr146220/bEmEvYp
	// By NameTxb = By.xpath("//input[@name='name']");
	// By DOBTxb = By.xpath("//input[@id='dob']");
	// By AddressTextarea = By.xpath("//textarea[@name='addr']");
	// By CityTxb = By.xpath("//input[@name='city']");
	// By StateTxb = By.xpath("//input[@name='state']");
	// By PinTxb = By.xpath("//input[@name='pinno']");
	// By MobileNumberTxb = By.xpath("//input[@name='telephoneno']");
	// By EmailTxb = By.xpath("//input[@name='emailid']");
	// By PassTxb = By.xpath("//input[@name='password']");

	@Test
	public void TS3_Textbox_Textarea() {
		driver.get("http://demo.guru99.com/v4");

		WebElement userid = driver.findElement(By.xpath("//input[@name='uid']"));
		userid.clear();
		userid.sendKeys("mngr158770");
		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
		password.clear();
		password.sendKeys("nYgugut");

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		String HomePageTitle = driver.getTitle();
		Assert.assertEquals(HomePageTitle, "Guru99 Bank Manager HomePage");

		// Chọn menu New Customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Nhập toàn bộ dữ liệu đúng
		driver.findElement(NameTxb).clear();
		driver.findElement(NameTxb).sendKeys(name);
		driver.findElement(DOBTxb).sendKeys(dob);
		driver.findElement(AddressTextarea).sendKeys(address);
		driver.findElement(CityTxb).sendKeys(city);
		driver.findElement(StateTxb).sendKeys(state);
		driver.findElement(PinTxb).sendKeys(pin);
		driver.findElement(MobileNumberTxb).sendKeys(phonenumber);
		driver.findElement(EmailTxb).sendKeys(email);
		driver.findElement(PassTxb).sendKeys(pass);
		
		// Click submit
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// can verify create new account success
		 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob);
		 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
		 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
		 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
		 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),phonenumber);
		 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);
		

		// Get ra thông tin của Customer ID
		customerid = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		// System.out.println(customerid);

		// Chọn menu Edit Customer > Nhập Customer ID > Submit
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerid);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		// Verify giá trị tại 2 field: Customer Name và Address
		Assert.assertEquals(driver.findElement(NameTxb).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);

		// Nhập giá trị mới tại 2 field Customer Address và City > Submit
		driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(newaddress);
		
		driver.findElement(By.xpath("//input[@name='city']")).clear();
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(newcity);
		
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// Verify giá trị tại 2 field: Customer Address và City đúng với dữ liệu sau khi
		// đã Edit thành công
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),newaddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(),newcity);

	}

	public int RandomUniqueNumber()
	{
		Random rand = new Random();
		int number = rand.nextInt(2222) + 1;
		return number;
	}

	@AfterClass
	public void afterClass()
	{
		driver.quit();
	}

}
