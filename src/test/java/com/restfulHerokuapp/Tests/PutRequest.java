package com.restfulHerokuapp.Tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRequest extends BaseTest{

    @Test
    public void updateBooking() throws Exception {
        test=reports.createTest("Verify user is able update current booking.");
        String body="{\"firstname\":\"Jim\",\"lastname\":\"sdasdasd\",\"totalprice\":500,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"dinner\"}";
        response= userService.updateBooking(authToken,body,2480);
        helper.validateStatusCode(response,200,test);
        response.then().body("lastname",equalTo("sdasdasd"))
                .body("totalprice",equalTo(500));
    }

    @Test
    public void updateBooking400BadRequest() throws Exception{
        test=reports.createTest("Verify server returns 400, for passing invalid payload or missing lastname");
        String body="{\"firstname\":\"Jim\",\"totalprice\":111,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"Breakfast\"}";
        response=userService.updateBooking(authToken,body,2480);
        helper.validateStatusCode(response,400,test);

    }


    @Test
    public void updateBooking403Forbidden() throws Exception{
        test=reports.createTest("Verify server returns 403, user is authenticated but not authorized. ");
        String body="{\"firstname\":\"Jim\",\"lastname\":\"sdasdasd\",\"totalprice\":500,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"dinner\"}";
        response=userService.updateBooking("1223333",body,2480);
        helper.validateStatusCode(response,403,test);

    }

    @Test
    public void updateBooking404NotFound() throws Exception{
        test=reports.createTest("Verify server returns 404, not found, if No id provided for update");
        String body="{\"firstname\":\"Jim\",\"lastname\":\"sdasdasd\",\"totalprice\":500,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"dinner\"}";
        response=given()
                .header("Cookie", "token=" + authToken) //header has to be cookie
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/");
        helper.validateStatusCode(response,404,test);

    }

    @Test
    public void updateBooking405MethodNotAllowed() throws Exception{
        test=reports.createTest("Verify server returns 405, resources doesn't exist");
        String body="{\"firstname\":\"Jim\",\"lastname\":\"sdasdasd\",\"totalprice\":500,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"dinner\"}";
        response=userService.updateBooking(authToken,body,568955);
        helper.validateStatusCode(response,405,test);
        String message=response.asString();
        System.out.println(message);
    }





}
