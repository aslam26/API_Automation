package restfulHerokuapp.Implementation.TestData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingData {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    @Getter
    @Setter
    public static class BookingDates{
        private String checkin;
        private String checkout;
    }

    @Getter
    @Setter
    public static class partialBooking{
        private String firstname;
        private int totalprice;
    }

}




