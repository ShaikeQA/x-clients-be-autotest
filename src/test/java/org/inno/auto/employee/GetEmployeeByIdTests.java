package org.inno.auto.employee;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.inno.auto.db.entity.CompanyEntity;
import org.inno.auto.db.entity.EmployeeEntity;
import org.inno.auto.model.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.inno.auto.steps.api.EmployeeApiSteps.getEmployeeByCompanyId;
import static org.inno.auto.steps.api.EmployeeApiSteps.getEmployeeById;
import static org.inno.auto.steps.db.CompanyDbSteps.createCompanyFromDB;
import static org.inno.auto.steps.db.CompanyDbSteps.deleteCompanyFromDB;
import static org.inno.auto.steps.db.EmployeeDbSteps.createEmployeeFromDB;
import static org.inno.auto.steps.db.EmployeeDbSteps.deleteEmployeeFromDB;
import static org.inno.auto.steps.generate.RandomDataForDB.createRandomCompany;
import static org.inno.auto.steps.generate.RandomDataForDB.createRandomEmployeeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetEmployeeByIdTests {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    static EmployeeEntity employee1;
    static CompanyEntity company1;

    @BeforeAll
    public static void setUp() {
        company1 = createRandomCompany();
        employee1 = createRandomEmployeeEntity(company1);

        createCompanyFromDB(company1);
        createEmployeeFromDB(employee1);
    }

    @AfterAll
    public static void tearDown() {
        if (employee1 != null) {
            deleteEmployeeFromDB(employee1);
        }
        if (company1 != null) {
            deleteCompanyFromDB(company1);
        }
    }

    @Test
    @DisplayName("Получение одного сотрудников, со всеми заполнеными полями по employeeId")
    @Description("Валидация выходны параметров, сравнение с БД")
    public void getOneEmployeeWithAllProperties() {
        Response response = getEmployeeById(employee1.getId(), 200);
        Employee employeeFromResponse = response.as(Employee.class);

        assertEquals(employee1.getId(), employeeFromResponse.getId());
        assertEquals(employee1.getFirstName(), employeeFromResponse.getFirstName());
        assertEquals(employee1.getLastName(), employeeFromResponse.getLastName());
        assertEquals(employee1.getMiddleName(), employeeFromResponse.getMiddleName());
        assertEquals(employee1.getPhone(), employeeFromResponse.getPhone());
        assertEquals(employee1.getEmail(), employeeFromResponse.getEmail());
        assertEquals(employee1.getBirthdate().toString(), employeeFromResponse.getBirthdate());
        assertEquals(employee1.getAvatarUrl(), employeeFromResponse.getAvatar_url());
        assertEquals(employee1.getCreateTimestamp().withOffsetSameInstant(ZoneOffset.UTC).format(formatter), employeeFromResponse.getCreateDateTime());
        assertEquals(employee1.getChangeTimestamp().withOffsetSameInstant(ZoneOffset.UTC).format(formatter), employeeFromResponse.getLastChangedDateTime());
        assertEquals(employee1.getIsActive(), employeeFromResponse.getIsActive());
        assertEquals(employee1.getCompany().getId(), employeeFromResponse.getCompanyId());
    }
}

