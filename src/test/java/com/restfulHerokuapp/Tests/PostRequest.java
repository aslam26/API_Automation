package com.restfulHerokuapp.Tests;

import com.google.gson.JsonSerializationContext;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostRequest extends BaseTest{

   // Response response;
    @Test(priority = 1)
    public void createBooking() throws Exception {
        test=reports.createTest("Create new booking id with valid data");
        response=userService.createBooking(authToken,true);
        //Assert.assertEquals(response.getStatusCode(),200);
        helper.validateStatusCode(response,200,test);
        bookingid=response.jsonPath().getInt("bookingid");
        System.out.println("Booking id:"+bookingid);

    }

    @Test(priority = 2)
    public void unauthorizedCreatingBooking() throws Exception {
        test=reports.createTest("Verify unauthorized user is not allowed to create new booking");
        response=userService.createBooking("eeee",true);
        helper.validateStatusCode(response,401,test);
        bookingid=response.jsonPath().getInt("bookingid");
        System.out.println(bookingid);
    }

    @Test(priority = 3)
    public void creatingWithInvalidBookingData() throws Exception {
        test=reports.createTest("Verify user is not able create booking with invalid data");
        response=userService.createBooking(authToken,false);
        Assert.assertEquals(response.getStatusCode(),500);
        System.out.println(response.getStatusCode());
        helper.validateStatusCode(response,500,test);
    }






}
