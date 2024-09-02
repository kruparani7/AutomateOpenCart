package utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.activation.DataSource;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	String repName;
	public ExtentSparkReporter sparkreport;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testcontext) {
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentTimeStamp=df.format(dt);
		
		repName="Test-Report-"+currentTimeStamp+".html";
		sparkreport=new ExtentSparkReporter("D:\\Eclipse\\OpenCart\\reports" + repName);
		sparkreport.config().setDocumentTitle("Opencart Automation");
		sparkreport.config().setReportName("OpenCart functional Test");
		sparkreport.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreport);
		extent.setSystemInfo("Applocation", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Modules", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
	
		
	}
	
	public void onSuccess(ITestResult result) {
		test=extent.createTest(result.getClass().getName());
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}
	
	public void onFail(ITestResult result) {
		test=extent.createTest(result.getClass().getName());
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath=new BaseClass().CaptureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
	
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public void onSkipped(ITestResult result) {
		test=extent.createTest(result.getClass().getName());
		test.log(Status.SKIP, result.getName()+"got skipped");
	}
	
	public void onFinish() throws MalformedURLException, EmailException {
		extent.flush();
		
		try {
		URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		
		ImageHtmlEmail email=new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("somakruparani7@gmail.com", "Krupa@2103"));
		email.setSSLOnConnect(true);
		email.setFrom("somakruparani7@gmail.com");
		email.setSubject("TestResults");
		email.setMsg("please find attahed tested report....");
		email.addTo("somakruparani7@gmail.com");
		email.attach(url,"extentReport","Please find attached report....");
		email.send();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	

}
