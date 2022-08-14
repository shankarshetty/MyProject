package generics;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Generic extends BaseTest
{
	
	public void loginTohrm()
	{
		WebDriverWait wait= new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='txtUsername']")));
		driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@name='Submit']")).click();
	}
	
	public void verifyPage(String xpath)
	{
		WebDriverWait wait= new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		String text=driver.findElement(By.xpath(xpath)).getText();
		System.out.println(text);
	}
	
public  void logout() {
		
		try {
			driver.findElement(By.xpath("//a[@id='welcome']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[text()='Logout']")).click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void clickElement(String xpath)
{
	WebDriverWait wait= new WebDriverWait(driver,10);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	driver.findElement(By.xpath(xpath)).click();
}

}
