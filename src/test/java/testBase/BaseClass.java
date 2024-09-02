package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {


public static WebDriver driver;
public static Logger logger;
public Properties pr;
	
	@BeforeClass(groups= {"Regression","Sanity","Master"})
	@Parameters({"browser"})
	public void Setup(String br) throws IOException {
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		pr=new Properties();
		pr.load(file);
		
		logger=Logger.getLogger(this.getClass());
		
		  switch(br.toLowerCase()) { case "chrome" : driver=new ChromeDriver(); break;
		  case "edge": driver=new EdgeDriver();break; case "firefox": driver=new
		  FirefoxDriver();break;
		  default:System.out.println("invalid browser name");return;
		  
		  }
		 
		
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(pr.getProperty("appURL"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Regression","Sanity","Master"})
	public void tearDown() {
		
		driver.quit();
		
	}
	public String randomString() {
		
		
		return RandomStringUtils.randomAlphabetic(5);
		
	}

	public String randomPwd() {
		return RandomStringUtils.randomAlphanumeric(8);
		
	}
	
	public String randomtelephone() {
		return RandomStringUtils.randomNumeric(10);
		
	}
	
	public String CaptureScreen(String str) {
		
		String dateformat=new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		
		TakesScreenshot tk=(TakesScreenshot) driver;
		File src=tk.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+str+"_"+dateformat;
		File targetPath=new File(targetFilePath);
		
		src.renameTo(targetPath);
		return targetFilePath;
		
	}
	
	

}
