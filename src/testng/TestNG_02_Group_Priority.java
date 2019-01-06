package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterSuite;

public class TestNG_02_Group_Priority {
	@Test(groups="user")
	public void TC_01() {
		System.out.println("TC_01");
	}

	@Test(groups="user")
	public void TC_02() {
		System.out.println("TC_02");
	}
	@Test(groups="product")
	public void TC_03() {
		System.out.println("TC_03");

	}
	@Test(groups="product")
	public void TC_04() {
		System.out.println("TC_04");
	}

	@Test(groups="payment")
	public void TC_05() {
		System.out.println("TC_05");
	}
	@Test(groups="payment")
	public void TC_06() {
		System.out.println("TC_06");

	}

}
