
package com.selenium.assessment;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;




import io.github.bonigarcia.wdm.WebDriverManager;
@Test
public class SetUp {
	//global variables
	public static WebDriver driver = null;
	ExtentReports extent;
	ExtentTest test;
	Logger logger;

	@BeforeSuite
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		//starts reports
		ExtentSparkReporter spark = new ExtentSparkReporter("./src/test/resources/Spark.html");



		//create extent reports and attach reporters
		extent = new ExtentReports();
		extent.attachReporter(spark);

		logger = LogManager.getLogger(SetUp.class);
	}

	@Test
	public void test_case_one() {

		test = extent.createTest("Open orangeHRM URL", " get the title of the current page.");

		//link to the website
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		String actualTitle = driver.getTitle();
		String expectedTitle = "OrangeHRM";

		Assert.assertEquals(actualTitle, expectedTitle);

		test.pass("Test Passed");

	}

	@AfterMethod
	public void EvaluateResult(ITestResult Results) {

		if(Results.getStatus() == ITestResult.FAILURE) {
			test.fail("Test Failed");
			logger.info("Test Failed");
		}
		else {
			test.pass("Test Passed");
			logger.info("Test Passed");
		}
	}

	@AfterTest
	public void flushing() {
		//calling flush writes everything to the log file
		extent.flush();
	}
}



