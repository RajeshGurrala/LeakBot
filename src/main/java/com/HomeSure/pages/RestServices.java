package com.HomeSure.pages;

import com.jayway.restassured.response.Response;
import net.minidev.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;


public class RestServices {

    public Response response;
    String baseUrl = "https://test.leakbot.io/v1.0/User/Account/";

    public Response executeGetRequest(String parameter1, String parameter1value, String parameter2, String parameter2value, String endPointURL ) {
        response = given().
                header(parameter1, parameter1value).
                header(parameter2,parameter2value).
                contentType("application/json").
                when().
                get(baseUrl + endPointURL);
        return response;
    }
    public Response executePATCHRequest(JSONObject payload, String url){
        response = given().body(payload).
                contentType("application/json").
                when().
                patch(baseUrl + url);
        return response;
    }

}
