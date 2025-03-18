package com.restfulHerokuapp.Tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class GetRequestInterview {

   static RequestSpecification requestSpec;

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

   @Test
    public void getAllBookingIds(){
       Response response=given()
               .when()
               .get()
               .then()
               .statusCode(200)
               .extract().response();
       response.prettyPrint();
   }

   @Test
    public void getBookingById(){
       Response response=given()
               .when()
               .get("/"+1814)
               .then()
               .statusCode(200)
               .extract().response();
       String actualResponse=response.getBody().asString();
       String expResponse=("{\"firstname\":\"yxgpwx\",\"lastname\":\"zwwgvm\",\"totalprice\":109,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2024-01-01\",\"checkout\":\"2024-01-02\"}}");
       Assert.assertEquals(actualResponse,expResponse,"Not matching");

   }

   @Test
   public void createBookingid(){
      Map<String,String> loginSetup=new HashMap<>();
      loginSetup.put("username","admin");
      loginSetup.put("password","password123");
      Response response=given()
              .contentType(ContentType.JSON)
              .body(loginSetup)
              .when()
              .post("https://restful-booker.herokuapp.com/auth");
      String authToken=response.jsonPath().getString("token");

      Response response1=given()
              .contentType(ContentType.JSON)
              .header("Authorization","Bearer "+authToken)
              .body("{\"firstname\":\"\",\"lastname\":\"\",\"totalprice\":111,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"Breakfast\"}")
              .when()
              .post();
      Assert.assertEquals(response1.getStatusCode(),200);
      String bookingId=response1.jsonPath().getString("bookingid");
      System.out.println("Booking id: "+bookingId);
   }
}
