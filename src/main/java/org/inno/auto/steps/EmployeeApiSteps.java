package org.inno.auto.steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.inno.auto.model.Employee;
import org.inno.auto.model.PostEmployeeResponse;

import java.util.ArrayList;

import static org.inno.auto.service.PropertiesService.*;
import static org.inno.auto.steps.AuthApiSteps.*;


public class EmployeeApiSteps {

    public static ArrayList<Employee> getEmployeeByCompanyId(int companyId, int expectedStatusCode) {
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

        return new ArrayList<>(JsonPath.from(response.getBody().asString()).getList("$", Employee.class));
    }

    public static Employee getEmployeeById(int employeeId, int expectedStatusCode) {
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

        return response.getBody().as(Employee.class);
    }

    public static PostEmployeeResponse postEmployee(Employee employee, int expectedStatusCode) {
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

        return response.getBody().as(PostEmployeeResponse.class);
    }

    public static Employee patchEmployee(Employee employeeForPath, int employeeId, int expectedStatusCode) {
        Response response =
                RestAssured.given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .header("x-client-token", getToken())
                        .pathParam("id", employeeId)
                        .body(employeeForPath)
                        .when()
                        .patch(getProp("BASE_URI") + "employee/{id}")
                        .then()
                        .log().all()
                        .extract().response();

        response.then().statusCode(expectedStatusCode);

        return response.getBody().as(Employee.class);
    }
}
