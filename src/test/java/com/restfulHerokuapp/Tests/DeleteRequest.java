package com.restfulHerokuapp.Tests;

import org.testng.annotations.Test;

public class DeleteRequest extends BaseTest {

    @Test
    public void DeleteBooking() throws Exception {
        test=reports.createTest("Verify created booking is deleted");
        response=userService.deleteBooking(authToken,882);
        helper.validateStatusCode(response,201,test);
        response=userService.getBooking(882);
        helper.validateStatusCode(response,404,test);
    }
}
