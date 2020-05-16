package com.qtpselenium.steps;

import java.util.Map;

import com.qtpselenium.webdriver.WebConnector;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ApplicationSteps {
	
	WebConnector con;
	
	public ApplicationSteps(WebConnector con)
	{
		this.con=con;
	}
	
	@And("^I login inside the application$")
	public void Login(Map<String,String> data)
	
	{
		con.infoLog("Logging into the application");
		con.infoLog(data.get("Username"));
		con.infoLog(data.get("Password"));
		//System.out.println(data.get("Username"));
		//System.out.println(data.get("Password"));
		con.Login(data.get("Username"),data.get("Password"));
	}
	
	@Then("^login should be (.*)$")
	public void validateLogin(String expectedResult) throws InterruptedException
	{
	con.validateLogin(expectedResult);	
	}

}
