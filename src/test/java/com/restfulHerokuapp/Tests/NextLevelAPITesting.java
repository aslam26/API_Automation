package com.restfulHerokuapp.Tests;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class NextLevelAPITesting extends BaseTest{

    //Rate Limiting and DoS Protection
    @Test
    public void RateLimitingTest() throws Exception {
        test=reports.createTest("Rate Limit Testing: Verify simulating multiple requests in quick succession to trigger rate limiting");
        for(int i=0;i<100;i++){
            response=userService.getBookingIds();
            if(i >50 ) {
                helper.validateStatusCode(response, 429, test);
                response.then().body("message", equalTo("Rate limit exceeded"));
            }
        }
    }

    @Test
    public void sql_Injection() throws Exception {
        test=reports.createTest("Verify for sql injection");
        String sqlInjectionPayload = "' OR 1=1 --";
        response=userService.authentication(sqlInjectionPayload,"password123");
        helper.validateStatusCode(response,400,test);
        response.then().body("message",equalTo("Invalid credentials"));
        authToken=response.jsonPath().getString("token");
        System.out.println("Auth Token: "+authToken);
    }


}
