package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage{

	public AccountRegisterPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement textfname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement textlname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement textemail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement textPhone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement textpwd;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement textcnfrmpwd;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnClick;
	
	@FindBy(xpath="//div[@id='content']/h1")
	WebElement txtConfirmation;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement clickPrivacyAgree;
	
	public void enterfName(String fname) {
		textfname.sendKeys(fname);
	}
	
	public  void enterlName(String lname) {
		textlname.sendKeys(lname);
		
	
	}
	
	public void enterEmail(String email) {
		
		textemail.sendKeys(email);
		
	}
	
	public void enterTelephone(String tele) {
		textPhone.sendKeys(tele);	}
	
	public void enterPwd(String pwd) {
		 textpwd.sendKeys(pwd);
		 
		
		
	}
	
	public void entercnfrmPwd(String cnfrmpwd) {
		textcnfrmpwd.sendKeys(cnfrmpwd);
		
	}
	
	public void clickAgree() {
		clickPrivacyAgree.click();
	}
	
	public void clickContinue() {
		btnClick.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return txtConfirmation.getText();
		}catch(Exception e) {
			return e.getMessage();
		}
		
	}

}
