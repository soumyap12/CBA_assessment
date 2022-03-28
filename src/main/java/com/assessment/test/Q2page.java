package com.assessment.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Q2page {

	public static WebDriver driver;
	static String currentUsersHomeDir = System.getProperty("user.home");
	
	@BeforeClass
	public void setup() throws InterruptedException {
		//Setting system properties of ChromeDriver
		System.setProperty("webdriver.chrome.driver", currentUsersHomeDir+"\\eclipse-workspace\\test_assessments\\src\\main\\resources\\chromedriver.exe");

		//Creating an object of ChromeDriver
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		//Deleting all the cookies
		driver.manage().deleteAllCookies();

		//Specifiying pageLoadTimeout and Implicit wait
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		//launching the specified URL 
		
	}
	
	@AfterClass
	public void quit() {
		driver.quit();
	}

	public String[] getRepayAndInterestTotal(String loanamount,String years,String intrestOnlyYears,String productPecent) { 
		driver.get(" https://www.commbank.com.au");
		WebElement element = driver.findElement(By.xpath("//a[text()='Repayments calculator']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
		driver.findElement(By.id("amount")).clear();
		driver.findElement(By.id("amount")).sendKeys(loanamount);
		driver.findElement(By.id("term")).clear();
		driver.findElement(By.id("term")).sendKeys(years);
		Select replacementType = new Select(driver.findElement(By.id("interestOnly")));
		replacementType.selectByVisibleText(intrestOnlyYears);
		Select productId = new Select(driver.findElement(By.id("productId")));
		productId.selectByVisibleText(productPecent);
		driver.findElement(By.id("submit")).click();
		String repayTotal=driver.findElement(By.xpath("//span[@data-tid='total-repayment']")).getText();
		String interestTotal=driver.findElement(By.xpath("//span[@data-tid='total-interest']")).getText();
		String[] rtn= {repayTotal,interestTotal};
		return rtn;
	}
}
