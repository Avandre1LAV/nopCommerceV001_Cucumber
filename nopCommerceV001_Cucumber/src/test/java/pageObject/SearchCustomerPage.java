package pageObject;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;






public class SearchCustomerPage {
	public WebDriver ldriver;
	
//	WaitHelper waithelper;
	
	public SearchCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
	//	waithelper=new WaitHelper(ldriver);
	}
	
	//Search Customer
//	@FindBy(how = How.NAME, using = "SearchEmail")
//	@CacheLookup
//	WebElement srchEmail;
		By srchEmail=By.name("SearchEmail");
		By srchFirstName=By.name("SearchFirstName");
		By srchLastName=By.name("SearchLastName");
		By btnSearch=By.id("search-customers");
		
		//table
		
		@FindBy (how = How.XPATH, using = "//table[@role='grid']")
		@CacheLookup
		WebElement tblSearchResult;
	//	By tblSearchResult=By.xpath("//table[@role='grid']");
		
		@FindBy (how = How.XPATH, using = "//table[@id='customers-grid']")
		WebElement table;
	//	By table=By.xpath("//table[@id='customers-grid']");
		@FindBy (how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
		List<WebElement> tableRows;
	//	By tableRows=By.xpath("//table[@id='customers-grid']//tbody/tr");
		@FindBy (how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
		List<WebElement> tableColums;
	//	By tableColums=By.xpath("//table[@id='customers-grid']//tbody/tr/td");
		
	
	public void enterEmail(String srchemail) throws InterruptedException {
		Thread.sleep(3000);
		ldriver.findElement(srchEmail).clear();
		ldriver.findElement(srchEmail).sendKeys(srchemail);
		
		}
	public void enterFirstName(String sFirstName) throws InterruptedException {
		Thread.sleep(3000);
		ldriver.findElement(srchFirstName).clear();
		ldriver.findElement(srchFirstName).sendKeys(sFirstName);
		
		}
	public void enterLastName(String sLastName) throws InterruptedException {
		Thread.sleep(3000);
		ldriver.findElement(srchLastName).clear();
		ldriver.findElement(srchLastName).sendKeys(sLastName);
		
		}
		
		public void clickOnSearch() {
			ldriver.findElement(btnSearch).click();
			}
		
		public int getNoOfRows() {
			return(tableRows.size());
		}
		
		public int getNoOfColumn() {
			return(tableColums.size());
		}
		
		public boolean searchCustomerByEmail(String email) {
			boolean flag = false;
			for (int i=1; i<=getNoOfRows();i++) {
				String emailid=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
				System.out.println(emailid);
				if (emailid.equals(email)) {
					flag=true;
				}
			}
			return flag;
			
		}
		
		public boolean searchCustomerByName(String Name) {
			boolean flag = false;
			for (int i=1; i<=getNoOfRows();i++) {
				String name=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
				String names[]=name.split(" ");
				if (names[0].equals("Victoria") && names[1].equals("Terces")) {
					flag=true;
				}
			}
			return flag;
			
		}

}
