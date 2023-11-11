package com.globant;

import com.globant.pojos.Account;
import com.globant.pojos.Movement;
import com.globant.pojos.User;
import com.globant.utils.TestUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BankAPI_Tests extends BaseTest {

    @Test
    public void testEmptyList() {
        JsonPath jsResponse = given()
                .when()
                    .get("users")
                .then()
                    .statusCode(200)
                    .extract().jsonPath();

        List<User> users = jsResponse.getList("", User.class);

        try {
            Assert.assertTrue(users.isEmpty());
        } catch (AssertionError e) {
            users.forEach(user -> {
                given()
                .when()
                    .delete("users/" + user.getId())
                .then()
                    .statusCode(200);
            });
        }
    }

    @Test(dependsOnMethods = {"testEmptyList"})
    public void testCreateUser() {

        Set<User> users = TestUtil.getExampleUsers(8);

        users.forEach(user -> {
            given()
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("users")
            .then()
                .statusCode(201);
        });
    }

    @Test(dependsOnMethods = {"testCreateUser"})
    public void testGetUsers() {
        JsonPath jsResponse = given()
                .when()
                    .get("users")
                .then()
                    .statusCode(200)
                    .extract().jsonPath();

        List<User> users = jsResponse.getList("", User.class);

        Set<String> emails = new HashSet<>();

        for (int i = 0; i < users.size(); i++) {
            Assert.assertEquals(i, emails.size());
            emails.add(users.get(i).getEmail());
        }
    }

    @Test
    public void testCreateAccount() {

        given()
            .contentType(ContentType.JSON)
            .body(new Account("012345", "Mi cuenta"))
        .when()
            .post("accounts")
        .then()
            .statusCode(201);
    }

    @Test
    public void testMakeDeposit() {

        response = given()
                .when()
                    .get("accounts")
                .then()
                    .statusCode(200)
                    .extract().response();

        Account account = response.jsonPath().getList("", Account.class).getFirst();

        account.getDeposits().add(new Movement(500d));

        given()
            .contentType(ContentType.JSON)
            .body(account)
        .when()
            .put("accounts/" + account.getId())
        .then()
            .statusCode(200);
    }

    @Test(dependsOnMethods = {"testMakeDeposit"})
    public void testUpdateAccount() {

        Account account = response.jsonPath().getList("", Account.class).getFirst();
        account.setName("Nuevo nombre");
        account.setNumber("546545465");

        given()
            .contentType(ContentType.JSON)
            .body(account)
        .when()
            .put("accounts/" + account.getId())
        .then()
            .statusCode(200)
            .body("name", equalTo(account.getName()))
            .body("number", equalTo(account.getNumber()));
    }
}