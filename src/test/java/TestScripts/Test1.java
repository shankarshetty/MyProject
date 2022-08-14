package TestScripts;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test1 
{
	protected RemoteWebDriver driver;
	@BeforeMethod
    public void setup(Method method) throws MalformedURLException {
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", System.getenv("oauth-shankarshetty.prakash-16f31"));
        sauceOptions.setCapability("accessKey", System.getenv("3f764ec6-63e4-4806-8fb5-2fc9732927af"));
        sauceOptions.setCapability("name", method.getName());
        sauceOptions.setCapability("browserVersion", "latest");

        ChromeOptions options = new ChromeOptions();
        options.setCapability("platformName", "Windows 10");
        options.setCapability("browserVersion", "latest");
        
//        options.setCapability("sauce:options", sauceOptions);
//        URL url = new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub");
        URL url= new URL("https://oauth-shankarshetty.prakash-16f31:3f764ec6-63e4-4806-8fb5-2fc9732927af@ondemand.eu-central-1.saucelabs.com:443/wd/hub");

        driver = new RemoteWebDriver(url, options);
    }

    @Test
    public void correctTitle() {
        driver.navigate().to("https://www.saucedemo.com");
        Assert.assertEquals("Swag Labs", driver.getTitle());
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        String status = result.isSuccess() ? "passed" : "failed";
        driver.executeScript("sauce:job-result=" + status);
    }

}
