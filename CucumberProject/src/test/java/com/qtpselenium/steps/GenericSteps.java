package com.qtpselenium.steps;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qtpselenium.webdriver.WebConnector;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;


public class GenericSteps {
	
	WebConnector con;
	
	public GenericSteps(WebConnector con) {
		this.con=con;
	}
	
	
    @Before
    public void before(Scenario s)
    {
    	System.out.println("******Before**********" +s.getName());
    	con.initReports(s.getName());
    }
    
    @After
    public void after()
    {
    	System.out.println("******After**********");
    	con.quit();
    }
	
	@Given("^I open (.*)$")
	public void OpenBrowser(String browserName)
	{
		//System.out.println("Opening "+browserName);
		con.infoLog("Opening Browser" +browserName);
		//System.out.println("Opening browser "+com.name);
		con.openbrowser(browserName);
		/*if(browserName.equals("FireFox"))
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
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
	}
	
	@And("^I go to (.*)$")
	public void navigate(String URL)
	{
		// System.out.println("Navigating to "+URL);
		con.infoLog("Navigating to "+URL);
		 //System.out.println("Navigating to "+ comm.name);
		 //comm.name="D";
		 con.navigate(URL);
	}
	
	
	
	@And("^I type (.*) in (.*) field$")
	public void type(String data,String locatorkey)
	{
		//System.out.println("Typing in "+data+" and locator " +locatorkey);
		con.infoLog("Typing in "+data+" and locator " +locatorkey);
		con.type(locatorkey,data);
		//con.explicitwait(locatorkey);
		}
	
	@And("^i click on the (.*)$")
	public void click(String locatorkey)
	{
		//System.out.println("clicking on "+locatorkey);
		con.click(locatorkey);
	}
	
	@And("^And I type (.*) in (.*) field$")
	public void typepassword(String data,String locatorkey)
	{
		System.out.println("typing " +"data"+ "in locator password" +locatorkey);
		con.type(locatorkey,data);
	}
	
	@And("^I click on (.*)$")
	public void submit(String locatorkey)
	{
		con.click(locatorkey);
	}
	
	
	@And("^I clear (.*)$")
	public void clearfield(String locatorkey)
	{
		con.clear(locatorkey);
	}

}
