package com.globant;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Response response;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://65443fb45a0b4b04436c3388.mockapi.io/api/";
    }

}
