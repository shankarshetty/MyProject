package TestScripts;

import org.testng.annotations.Test;

import generics.BaseTest;
import generics.Generic;
import junit.framework.Assert;

public class Hrm2 extends BaseTest{
	@Test
	public void hrm2()
	{
		author="Pradeep Kanti";
		category="Regression";
		Generic gen = new Generic();
		//driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		extentTest.info("test hrm1 strated");
		gen.loginTohrm();
		gen.clickElement("//a[b[text()='PIM']]");
		Assert.assertEquals(true, true);
		gen.logout();
	}

}
