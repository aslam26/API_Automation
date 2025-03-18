package restfulHerokuapp.Implementation.TestData;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BookingDataBuilder{

    protected static Faker faker=new Faker();
    protected static Random random=new Random();

    public static BookingData createNewBookingData(){

        BookingData bookingData=new BookingData();
        bookingData.setFirstname(faker.name().firstName());
        bookingData.setLastname(faker.name().lastName());
        bookingData.setTotalprice(faker.number().numberBetween(100,200));
        bookingData.setDepositpaid(random.nextBoolean());

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");

        BookingData.BookingDates bookingDates=new BookingData.BookingDates();
        bookingDates.setCheckin(simpleDateFormat.format(faker.date().past(20, TimeUnit.DAYS)));
        bookingDates.setCheckout(simpleDateFormat.format(faker.date().future(5,TimeUnit.DAYS)));
        bookingData.setBookingdates(bookingDates);

        bookingData.setAdditionalneeds(faker.options().option("breakfast","lunch","dinner"));

        return bookingData;

    }

    public static BookingData createInvalidBookingData(){
        BookingData bookingData=new BookingData();
        bookingData.setFirstname(null);
        bookingData.setLastname("");
        bookingData.setTotalprice(-10);
        bookingData.setDepositpaid(false);
        bookingData.setAdditionalneeds(""); //
        return bookingData;
    }

    public static BookingData.partialBooking partialBookingData(){
        BookingData.partialBooking partialBooking=new BookingData.partialBooking();
        partialBooking.setFirstname(faker.name().firstName());
        partialBooking.setTotalprice(faker.number().numberBetween(700,800));
        return partialBooking;
    }
}
