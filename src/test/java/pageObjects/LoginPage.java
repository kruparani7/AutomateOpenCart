package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement enterUname;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement enterPwd;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement clickLogin;
	
	
	public void enterEmail(String uname) {
		enterUname.sendKeys(uname);
	}
	
	public void enterPwd(String pwd) {
		enterPwd.sendKeys(pwd);
	}
	
	public void clickLoginbtn() {
		clickLogin.click();
		
	}
	
	

}
