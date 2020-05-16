package com.qtpselenium.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
		dryRun=false,
		strict=true,
		monochrome=true,
		features= {"src/test/resources/"},
		glue= {"com.qtpselenium.steps"},
		plugin= {
				"html:target/site/cucumber.html",
				"json:target/cucumber1.json",				
		}
		//tags= "(@CreatePortfolio)"
	              )

public class Runner {

}
