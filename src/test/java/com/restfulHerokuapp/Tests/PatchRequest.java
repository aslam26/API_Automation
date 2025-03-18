package com.restfulHerokuapp.Tests;

import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class PatchRequest extends BaseTest {

    @Test
    public void update_a_current_booking_with_a_partial_payload() throws Exception {

        test = reports.createTest("Verfiy Updates a current booking with a partial payload");
        response = userService.partialUpdateBooking(authToken, 562);
        helper.validateStatusCode(response, 200, test);
        response.then().body("firstname", equalTo(userService.partialBooking.getFirstname()))
                .body("lastname",equalTo("Brown"))
                .body("totalprice", equalTo(userService.partialBooking.getTotalprice()));
    }

}