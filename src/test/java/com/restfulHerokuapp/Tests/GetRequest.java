package com.restfulHerokuapp.Tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRequest extends BaseTest{

    @Test
    public void getAllBookingIds() throws Exception {
        test=reports.createTest("Verify all Booking ids are displayed");
        Response response=userService.getBookingIds();
        helper.validateStatusCode(response,200,test);
      //  Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void getBooking() throws Exception {
        test=reports.createTest("Verify details of particular booking id is displayed.");
        Response response=userService.getBooking(7414);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
        String expectedRespone=("{\"firstname\":\"28029\",\"lastname\":\"88956\",\"totalprice\":31122,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2024-01-01\",\"checkout\":\"2024-01-02\"}}");
        String actualReponse=response.getBody().asString();
        Assert.assertEquals(actualReponse,expectedRespone,"Response body doesn't match");
        helper.validateStatusCode(response,200,test);

    }



}
