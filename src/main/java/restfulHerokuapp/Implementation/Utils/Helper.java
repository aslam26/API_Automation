package restfulHerokuapp.Implementation.Utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;

public class Helper {

    public void validateStatusCode(Response response, int expectedStatusCode, ExtentTest test) throws Exception {
        int actualStatusCode= response.getStatusCode();

        if(actualStatusCode==expectedStatusCode){
            test.log(Status.PASS,"Expected Status code: "+expectedStatusCode+", received: "+actualStatusCode);
        }else {
            test.log(Status.FAIL, "Expected Status code: " + expectedStatusCode + ", But received: " + actualStatusCode);
            Assert.fail("Status code validation failed, Expected: " + expectedStatusCode + ", but was: " + actualStatusCode);
        }

    }

}
