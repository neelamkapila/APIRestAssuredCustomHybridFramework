package com.thetestingacademy.endpoints;

public class APIConstants {

    //All the URLs - Should keep in Excel in resource folder
    //Can fetch the test data via Excel, CSV, JSON, Txt, Properties, YAML(Key-Value pair - Preferred nowadays)

    // Stage - URL 1    -   stage_td.xlsx
    //   UAT - URL 2    -   UAT_td.xlsx
    //  Prod - URL 3    -   prod_td.xlsx

    // public static String BASE_URL =FillowUtils.fetchDataFromXLSX("Sheet1","BaseUrl","Value");
    public static String BASE_URL = "https://restful-booker.herokuapp.com";
    public static String CREATE_TOKEN_AUTH_URL = "/auth";
    public static String CREATE_UPDATE_DELETE_BOOKING_URL = "/booking";
}
