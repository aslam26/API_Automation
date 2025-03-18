package com.restfulHerokuapp.Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import restfulHerokuapp.Implementation.Services.UserService;
import restfulHerokuapp.Implementation.Utils.Helper;

public class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static int bookingid;
    static String authToken;
    String currentWorkingDir=System.getProperty("user.dir");
    protected static UserService userService=new UserService();
    Helper helper=new Helper();
    Response response;

    //Allure Reports
    protected static ExtentTest test;
    protected static ExtentSparkReporter sparkReporter;
    protected static ExtentReports reports;

    @BeforeSuite
    public void setReports(){
        sparkReporter=new ExtentSparkReporter(currentWorkingDir+"/Reports/TestReport.html");
        reports=new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Envirnoment","QA");

    }

    @BeforeClass
    public void getAuthenticationToken(){
        Response response= userService.authentication("admin","password123");
        authToken=response.jsonPath().getString("token");
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("Auth Token: "+authToken);
    }

    @BeforeTest
    public void setUp(){
        requestSpec=new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .setBasePath("/booking")
                .addHeader("Content-type","application/json")
                .addHeader("Accept","application/json")
                .build();
        RestAssured.requestSpecification=requestSpec;
    }

    @AfterSuite
    public void tearDownReport(){
        reports.flush();
    }
}
