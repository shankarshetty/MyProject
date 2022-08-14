package generics;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static RemoteWebDriver driver;
//	public static WebDriver driver;
	public static ExtentReports extentReport;
	public static  ExtentSparkReporter sparkReporter;
	public static ExtentTest extentTest;
	public static String testName;
	public static String author;
	public static String category;
	
	@BeforeSuite
	public void initializeReport()
	{
		extentReport = new ExtentReports();
		sparkReporter=new ExtentSparkReporter("D:\\Workspace-Cucumber\\Sauce-Proj\\SauceProj\\Report.html");
		extentReport.attachReporter(sparkReporter);
		
	}
	
	@Parameters("browserName")
	@BeforeMethod
	public  void setup(ITestContext context,String browsername) throws MalformedURLException
	{
		switch(browsername.toLowerCase())
		{
		case "chrome":
//			WebDriverManager.chromedriver().setup();
//			driver=new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
	        options.setCapability("platformName", "Windows 10");
	        options.setCapability("browserVersion", "latest");
	        URL url= new URL("https://oauth-shankarshetty.prakash-16f31:3f764ec6-63e4-4806-8fb5-2fc9732927af@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
	        driver = new RemoteWebDriver(url, options);
			break;
			
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		default:
			System.out.println("Invalid Browser");
			break;
		}
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
//		Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
		Capabilities cap = driver.getCapabilities();
		extentTest=extentReport.createTest(getClass().getName());
		String device=cap.getBrowserName()+cap.getVersion();
		extentTest.assignDevice(device);
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public  void tearDown(ITestResult result)
	{
		
		if(result.getStatus()==result.FAILURE)
		{
			
			String imagePath=null;
			imagePath=captureScreenshot(result.getMethod().getMethodName()+".jpg");
			extentTest.addScreenCaptureFromPath(imagePath);
			extentTest.fail("Failed");
		}else
		{
			extentTest.pass("Executed Successfully");
			
		}
		extentTest.assignAuthor(author);
		extentTest.assignCategory(category);
		String status = result.isSuccess() ? "passed" : "failed";
        driver.executeScript("sauce:job-result=" + status);
		driver.quit();
	}
	
	@AfterSuite
	public  void flushReport()
	{
		extentReport.flush();
	}
	
	public  String captureScreenshot(String filename)
	{
		System.out.println("testname="+filename);
		TakesScreenshot shot = (TakesScreenshot) driver;
		File fileSource = shot.getScreenshotAs(OutputType.FILE);
		File destFile= new File("./Images/"+filename);
		try {
			FileUtils.copyFile(fileSource, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}

}
