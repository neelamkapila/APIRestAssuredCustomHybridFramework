package com.thetestingacademy.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.thetestingacademy.payload.Booking;
import com.thetestingacademy.payload.BookingDates;

public class PayloadManager {

    //ABSTRACTION

    //Convert Java -> JSON
    //Create a Payload

    ObjectMapper objectMapper;
    public String createPayload() throws JsonProcessingException {

        Faker faker = new Faker();
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(7999);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Lunch");
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2023-11-01");
        bookingDates.setCheckout("2023-11-09");
        booking.setBookingdates(bookingDates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public String updatePayload() throws JsonProcessingException {

        Faker faker = new Faker();
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(7999);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Lunch");
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2023-11-01");
        bookingDates.setCheckout("2023-11-09");
        booking.setBookingdates(bookingDates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }
}
