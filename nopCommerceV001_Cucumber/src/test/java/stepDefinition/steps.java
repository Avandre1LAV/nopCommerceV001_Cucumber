package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.AddCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;

public class steps extends BaseClass {
	@Before
	public void setup() throws IOException {
		logger=Logger.getLogger("nopCommerceV001_Cucumber"); //Added logger
		PropertyConfigurator.configure("log4j.properties"); //Added Logger
		
		//Reading configure properties file
		configProp=new Properties();
		FileInputStream configPropfile=new FileInputStream("config.properties");
		configProp.load(configPropfile);
		String br=configProp.getProperty("browser");
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}
		
		logger.info("******** Launching browser ********");
	}
	
	
	@Given("^User Launch Chrome Browser$")
	public void user_Launch_Chrome_Browser() throws Throwable {
		//You need 2 steps below for Logger
//		logger=Logger.getLogger("nopCommerceV001_Cucumber"); //Added logger
//		PropertyConfigurator.configure("log4j.properties"); //Added Logger
//		
//		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\mc56370\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
//		driver = new ChromeDriver();
//		logger.info("******** Launching browser ********");
		
		lp=new LoginPage(driver);
		driver.manage().window().maximize();
	}

	@When("^User open URL \"([^\"]*)\"$")
	public void user_open_URL(String url) throws Throwable {
		logger.info("******** Opening the url *******");
		driver.get(url);
	    
	}

	@And("^User Enter Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_Enter_Email_as_and_Password_as(String uname, String pwd) throws Throwable {
		logger.info("******** Providing login credentials *******");
		driver.findElement(By.name("Email")).clear();
		driver.findElement(By.name("Email")).sendKeys(uname);
		driver.findElement(By.name("Password")).clear();
		driver.findElement(By.name("Password")).sendKeys(pwd);
		
//		lp.setUserName(uname);
//		lp.setPassword(pwd);
	}

	@And("^Click on Login$")
	public void click_on_Login() throws InterruptedException {
		logger.info("******** Started login process *******");
		driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/input")).click();
//	    lp.clickLogin();
		Thread.sleep(3000);
	}

	@Then("^Page Title should be \"([^\"]*)\"$")
	public void page_Title_should_be(String arg1) throws InterruptedException {
		String txtTitle = driver.getTitle();
		Assert.assertEquals(true,txtTitle.contains("Dashboard / nopCommerce administration"));
		logger.info("******** Login passed *******");
		Thread.sleep(3000);
	}

	@When("^User click on Logout link$")
	public void user_click_on_Logout_link() throws  InterruptedException {
		logger.info("******** Click on Logout link *******");
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
	    
	}
	@Then("^Next Page Title should be \"([^\"]*)\"$")
	public void Next_page_Title_should_be(String title) throws InterruptedException {
		
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.quit();
			Assert.assertTrue(false);
			logger.info("******** Login passed *******");
		}else {
			logger.info("******** Login failed *******");
			Assert.assertEquals(title, driver.getTitle());
			
		}
	}


	@And("^Close Browser$")
	public void close_Browser() throws Throwable {
		logger.info("******** Close Browser *******");
		driver.quit();
	    
	//Customers.feature step definition.......................
	
	}
	@Then("^User can view Dashborad$")
	public void user_can_view_Dashborad() throws Throwable {
		addCust=new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	    
	}

	@When("^User click on customers Menu$")
	public void user_click_on_customers_Menu() throws InterruptedException {
	    addCust.clickOnCustomerMenu();
	    Thread.sleep(3000);
	}

	@When("^Click on Customer Menu Item$")
	public void click_on_Customer_Menu_Item() throws InterruptedException {
		addCust.clickOnCustomerMenuItem();
		Thread.sleep(3000);  
	}

	@When("^Click on Add New Button$")
	public void click_on_Add_New_Button() throws InterruptedException {
		addCust.clickOnAddNew();
		Thread.sleep(3000);
		addCust.clickCustomerInfo();
		Thread.sleep(3000);
	}

	@Then("^user can view Add new Customer page$")
	public void user_can_view_Add_new_Customer_page() throws InterruptedException {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	    Thread.sleep(3000);
	}

	@When("^User enter customer info$")
	public void user_enter_customer_info() throws InterruptedException {
		String email=randomestring()+ "@yourstore.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setFirstName("Azelena");
		addCust.setLastName("Vandre");
		addCust.setGender("Male");
		addCust.setDateOfBirth("5/12/1920");
		addCust.setCompanyName("No company name");
		addCust.setIsTaxExempt();
		
		// addCust.setNewsletter("Test Store 2");
	    //Registered is default
		//Customer cannot be in both "Guests" and "Registered" customer roles
		//Add the customer to "Guests" or "Registered" customer role
		//addCust.setCustomerRole("Administrator");
		//addCust.setManagerOfVendor("vendor 1");
		
		addCust.setAdminComment("This is for testing.....");
		Thread.sleep(3000);
	}

	@When("^Click Save button$")
	public void click_Save_button() throws InterruptedException {
		addCust.clickOnSave();
		Thread.sleep(3000);
	    
	}

	@Then("^User can view confirmation message \"([^\"]*)\"$")
	public void user_can_view_confirmation_message(String arg1) throws Throwable {
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	}
	
	//Steps for searching a customer using emailID.....

	@When("^Enter customer Email$")
	public void enter_customer_Email() throws Throwable {
		sp= new SearchCustomerPage(driver);
		sp.enterEmail("victoria_victoria@nopCommerce.com");
	    
	}
	@When("^Click on search button$")
	public void click_on_search_button() throws InterruptedException {
		sp.clickOnSearch();
		Thread.sleep(3000);
	    
	}

	@Then("^User should found Email in the Search table$")
	public void user_should_found_Email_in_the_Search_table() {
	    boolean status=sp.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	    Assert.assertEquals(true, status);
	}
	
	//Search customer by First and Last Name
	
	@When("^Enter customer First Name$")
	public void enter_customer_First_Name() throws InterruptedException {
		sp= new SearchCustomerPage(driver);
		sp.enterFirstName("Victoria");
	    
	}

	@When("^Enter customer Last Name$")
	public void enter_customer_Last_Name() throws InterruptedException {
		sp.enterLastName("Terces");
		
	   
	}

	@Then("^User should found Name in the Search table$")
	public void user_should_found_Name_in_the_Search_table() throws Throwable {
		boolean status=sp.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	    
	}
}
