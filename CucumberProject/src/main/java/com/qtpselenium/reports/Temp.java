package com.qtpselenium.reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Temp {

	public static void main(String[] args) {
		Date d= new Date();
		//System.out.println(d.toString().replace(":", "_"));
		//new File("C:\\Users\\Ruchi\\Documents\\Reports\\a").mkdir();
		
		ExtentReports rep=ExtentManager.getInstance("C:\\Users\\Ruchi\\Documents\\Reports\\");
		ExtentTest test=rep.createTest("TestA");
		test.log(Status.INFO, "Starting Test A");
		test.log(Status.INFO, "Executing");
		test.log(Status.FAIL, "Failed");
		
		rep.flush();

	}

}
