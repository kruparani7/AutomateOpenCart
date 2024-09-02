package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class Tc002_LoginTest extends BaseClass {
	
	
	@Test(groups= {"Sanity","Master"})
	public void verify_loginTest() {
		
		
		logger.info("************login entry***************");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("*************clicked on my account********");
		hp.clickLogin();
		logger.info("************clicked for login*************");
		
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(pr.getProperty("email"));
		logger.info("************enter email in login page*************");
		lp.enterPwd(pr.getProperty("password"));
		logger.info("************enter password in login page*************");
		lp.clickLoginbtn();
		logger.info("************clicked for login button*************");
	}catch(Exception e) {
		logger.error("failed tc");
		Assert.fail();
	}
		logger.info("*************login page tc completed*************");
		
	}
	
	
	

}
