package com.qtpselenium.webdriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.qtpselenium.reports.ExtentManager;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.SoftAssertionsProvider;

import static org.assertj.core.api.Assertions.*;




public class WebConnector {
	
	WebDriver driver;
	public Properties prop;
	//public String name;
	public ExtentReports rep;
	public ExtentTest scenario;
	
	public WebConnector()
	{
		if(prop==null)
		{
			prop=new Properties();
			try {
				FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\Project.properties");
				prop.load(fis);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

	public void openbrowser(String browserName) {
		if(browserName.equals("FireFox"))
		{
			
			driver=new FirefoxDriver();
		}
		else if (browserName.equals("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if (browserName.equals("IE"))
		{
			driver=new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		infoLog("Opened Browser");
		
	}

	public void navigate(String urlKey) {
		//System.out.println(prop.getProperty(urlKey));
		driver.get(prop.getProperty(urlKey));
	}

	public void click(String ObjectKey) {
		getObject(ObjectKey).click();	
	}

	public void type(String ObjectKey, String data) {
		getObject(ObjectKey).sendKeys(data);	
	}

	/*public void explicitwait(String locatorkey) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(prop.getProperty(locatorkey)))));
	}*/
	//central function to extract object
	public WebElement getObject(String ObjectKey) {
		WebElement e=null;
		WebDriverWait wait = new WebDriverWait(driver, 20); 
		try {
		if(ObjectKey.endsWith("_xpath"))
			{
	           e=driver.findElement(By.xpath(prop.getProperty(ObjectKey)));
			   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(ObjectKey))));
		    }
		else if (ObjectKey.endsWith("_id"))
			{
				e=driver.findElement(By.id(prop.getProperty(ObjectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(prop.getProperty(ObjectKey))));
			}
		else if(ObjectKey.endsWith("_name"))
			{
				e=driver.findElement(By.name(prop.getProperty(ObjectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(prop.getProperty(ObjectKey))));
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			reportFailure("Unable to locate the element "+ObjectKey);
			
		}
		return e;
	}
		
		

		public void Login(String username, String password) {
			type("username_id",username);
			click("confirmemailbutton_id");
			type("password_xpath",password);
			click("loginsubmit_xpath");
			
		}
		
		public void clear(String ObjectKey) {
			getObject(ObjectKey).clear();	
		}
		
		public void acceptIfAlertIsPresent()
		{
			try {
			WebDriverWait wait= new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		public void validateLogin(String expectedresult) throws InterruptedException
		{
			Boolean result=isElelmentPresent("portfolio_id");
			System.out.println("Result is "+result);
			String actualresult="";
			if(result)
				actualresult="success";
				else
					actualresult="Failure";
			infoLog("Got the Actual Result as" + actualresult);
			infoLog("Got Expected result as "+ expectedresult);
			//System.out.println(expectedresult);
			if(!expectedresult.equals(actualresult))
				reportFailure("Scenario Failure");
		}
		
		public boolean isElelmentPresent(String ObjectKey) throws InterruptedException
		{
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 20); 
			List<WebElement> e=null;
		if(ObjectKey.endsWith("_xpath"))
			{
	           e=driver.findElements(By.xpath(prop.getProperty(ObjectKey)));
	           wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(ObjectKey))));
		    }
	    else if (ObjectKey.endsWith("_id"))
			{
				e=driver.findElements(By.id(prop.getProperty(ObjectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(prop.getProperty(ObjectKey))));
			}
		else if(ObjectKey.endsWith("_name"))
			{
				e=driver.findElements(By.name(prop.getProperty(ObjectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(prop.getProperty(ObjectKey))));
			}
			if(e.size()==0)
			{
				System.out.println("Element not present");
				return false;
			}
			else
			{
				System.out.println("Elelment Present");
				return true;
			}
			
		}
		
		/***************Logging Functions**************/
		
		public void infoLog(String msg)
		{
			scenario.log(Status.INFO,msg);
		}
		
		public void reportFailure(String errorMsg)
		{
			scenario.log(Status.FAIL, errorMsg);
			takeScreenshot();
			assertThat(false);
		}
		
		public void takeScreenshot()
		{
			Date d= new Date();
			String screenshotfile=d.toString().replace(":", "_").replace(" ", "_")+".png";
			File SrcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(SrcFile,new File(ExtentManager.screenshotFolderPath+screenshotfile));
				scenario.log(Status.FAIL, "Screenshot-> "+ scenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotfile));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		/******************Reporting******************/
		
		public void initReports(String scenarioName)
		{
			rep=ExtentManager.getInstance(prop.getProperty("reportPath"));
			scenario= rep.createTest(scenarioName);
			scenario.log(Status.INFO, "Starting "+scenarioName);
		}

		public void quit() {
			if(rep!=null)
				rep.flush();
			
		}



		
}

