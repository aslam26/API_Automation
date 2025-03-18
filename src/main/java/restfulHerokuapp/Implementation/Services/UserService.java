package restfulHerokuapp.Implementation.Services;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restfulHerokuapp.Implementation.TestData.BookingData;
import restfulHerokuapp.Implementation.TestData.BookingDataBuilder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserService {

    public BookingData bookingData;
    public BookingData.partialBooking partialBooking;

    public Response getBookingIds(){
        return given()
                .when()
                . get();
    }

    public Response getBooking(int id){
        return given()
                .when()
                .get("/"+id);
    }

    public Response createBooking(String token, boolean isValid){
        if(isValid){
            bookingData=BookingDataBuilder.createNewBookingData();
        }else {
            bookingData=BookingDataBuilder.createInvalidBookingData();
        }
        return given()
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .body(bookingData)
                .when()
                .post();
    }

    public Response authentication(String usn,String psw){
        Map<String,String> loginRequest=new HashMap<>();
        loginRequest.put("username",usn);
        loginRequest.put("password",psw);
        return given()
                .body(loginRequest)
                .when()
                .post("https://restful-booker.herokuapp.com/auth");

    }

    public Response updateBooking(String token, String body, int id){

        return given()
                .header("Cookie", "token=" + token) //header has to be cookie
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/"+id);
    }

    public Response partialUpdateBooking(String token, int id) {
        partialBooking=BookingDataBuilder.partialBookingData();
        return given()
                .header("Cookie", "token=" + token)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body(partialBooking)
                .when()
                .patch("/" + id);

    }


    public Response deleteBooking(String authToken, int id){
        return given()
                .header("Cookie","token="+authToken)
                .when()
                .delete("/"+id);
    }

}
