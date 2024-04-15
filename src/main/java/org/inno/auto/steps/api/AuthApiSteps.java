package org.inno.auto.steps.api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.inno.auto.service.PropertiesService.*;

public class AuthApiSteps {

    public static String getToken() {

        String getTokenBody = """
                {
                  "username": "leonardo",
                  "password": "leads"
                }
                """;
        return new JsonPath( given()
                .contentType(ContentType.JSON)
                .body(getTokenBody).when()
                .post(getProp("BASE_URI") + "auth/login")
                .then()
                .log().all()
                .extract().response().getBody().asString()).getString("userToken");

    }
}
