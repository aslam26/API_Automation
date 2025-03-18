package com.restfulHerokuapp.Tests;

import org.testng.annotations.Test;

import java.util.Objects;

import static org.hamcrest.core.IsEqual.equalTo;

public class E2ETesting extends BaseTest{

    @Test
    public void booking_LifeCycle_Testing() throws Exception {
        test = reports.createTest("Verify Creating a new booking, Update the booking details, Retrieve the updated product details, Delete the product");
       //Create new booking
        response = userService.createBooking(authToken, true);
        helper.validateStatusCode(response, 200, test);
        bookingid=response.jsonPath().getInt("bookingid");
        System.out.println("Booking id:"+bookingid);
        System.out.println(response.asString());
        // Update booking details
        response=userService.partialUpdateBooking(authToken,bookingid);
        helper.validateStatusCode(response,200,test);
        response.then().body("firstname", equalTo(userService.partialBooking.getFirstname()))
                .body("totalprice", equalTo(userService.partialBooking.getTotalprice()));
        System.out.println(response.asString());
        //Retrieve updates details
        response=userService.getBooking(bookingid);
        helper.validateStatusCode(response,200,test);
        System.out.println(response.asString());
        //Delete Booking
        response=userService.deleteBooking(authToken,bookingid);
        helper.validateStatusCode(response,201,test);

    }
}

