package testCases;




import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class Tc001_AccountRegistrationTest extends BaseClass {
	
	
	
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("**************Starting account registration**************");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("**************clicked on my account*******************");
		hp.clickRegister();
		logger.info("************providing account details******************");
		AccountRegisterPage arp=new AccountRegisterPage(driver);
		arp.enterfName(randomString().toUpperCase());
		arp.enterlName(randomString().toUpperCase());
		arp.enterEmail(randomString()+"@abc.com");
		
		arp.enterTelephone(randomtelephone());
		
		String pwd=randomPwd();
		arp.enterPwd(pwd);
		arp.entercnfrmPwd(pwd);
		arp.clickAgree();
		logger.info("***************clicked on agreement***************");
		arp.clickContinue();
		logger.info("*****************clicked continue button**************");
		String confmsg=arp.getConfirmationMsg();
		
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Testcase failed");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		logger.info("****************Finished account registration testcase****************");
	}
	


}
