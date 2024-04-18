package org.inno.auto.steps.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.inno.auto.model.Employee;

import static org.inno.auto.service.PropertiesService.*;
import static org.inno.auto.steps.api.AuthApiSteps.*;


public class EmployeeApiSteps {

    public static Response getEmployeeByCompanyId(int companyId, int expectedStatusCode) {
        Response response =
                RestAssured.given()
                        .log().all()
                        .queryParam("company", companyId)
                        .when()
                        .get(getProp("BASE_URI") + "employee")
                        .then()
                        .log().all()
                        .extract().response();

        response.then().statusCode(expectedStatusCode);

        return response;
    }
//new ArrayList<>(JsonPath.from(response.getBody().asString()).getList("$", Employee.class))
    public static Response getEmployeeById(int employeeId, int expectedStatusCode) {
        Response response =
                RestAssured.given()
                        .log().all()
                        .pathParam("id", employeeId)
                        .when()
                        .get(getProp("BASE_URI") + "employee/{id}")
                        .then()
                        .log().all()
                        .extract().response();

        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public static Response postEmployee(Employee employee, int expectedStatusCode) {
        Response response =
                RestAssured.given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .header("x-client-token", getToken())
                        .body(employee)
                        .when()
                        .post(getProp("BASE_URI") + "employee")
                        .then()
                        .log().all()
                        .extract().response();

        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public static Response patchEmployee(Employee employeeForPatch, int employeeId, int expectedStatusCode) {
        Response response =
                RestAssured.given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .header("x-client-token", getToken())
                        .pathParam("id", employeeId)
                        .body(employeeForPatch)
                        .when()
                        .patch(getProp("BASE_URI") + "employee/{id}")
                        .then()
                        .log().all()
                        .extract().response();

        response.then().statusCode(expectedStatusCode);

        return response;
    }
}
