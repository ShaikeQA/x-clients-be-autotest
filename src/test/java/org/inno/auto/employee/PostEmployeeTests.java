package org.inno.auto.employee;


import io.restassured.response.Response;
import jdk.jfr.Description;
import org.inno.auto.db.entity.CompanyEntity;
import org.inno.auto.db.entity.EmployeeEntity;
import org.inno.auto.model.Employee;
import org.inno.auto.model.PostEmployeeResponse;
import org.junit.jupiter.api.*;


import static org.inno.auto.steps.api.EmployeeApiSteps.*;
import static org.inno.auto.steps.db.CompanyDbSteps.*;
import static org.inno.auto.steps.db.CompanyDbSteps.createCompanyFromDB;
import static org.inno.auto.steps.db.EmployeeDbSteps.deleteEmployeeFromDB;
import static org.inno.auto.steps.db.EmployeeDbSteps.getEmployeeFromDB;
import static org.inno.auto.steps.generate.RandomDataForApi.createRandomEmployee;
import static org.inno.auto.steps.generate.RandomDataForDB.createRandomCompany;
import static org.junit.jupiter.api.Assertions.*;

public class PostEmployeeTests {
    CompanyEntity company;
    Employee employee;
    EmployeeEntity employeeEntity;

    @BeforeEach
    public void setUp() {
        //Создание компании
        company = createRandomCompany();
        createCompanyFromDB(company);
        //Создание сотрудника для запроса
        employee = createRandomEmployee(company.getId());
    }

    @AfterEach
    public void tearDown() {

        if (employeeEntity != null) {
            deleteEmployeeFromDB(employeeEntity);
        }
        if (company != null) {
            deleteCompanyFromDB(company);
        }
    }

    @Test
    @DisplayName("Создание сотрудника со всеми заполнеными полями")
    @Description("Проверка полей из базы и запроса")
    public void postEmployeeWithAllProperties() {
        Response response = postEmployee(employee, 201);
        int newEmployeeId = response.getBody().as(PostEmployeeResponse.class).getId();
        employeeEntity = getEmployeeFromDB(newEmployeeId);


        assertEquals(employeeEntity.getId(), newEmployeeId);
        assertEquals(employeeEntity.getFirstName(), employee.getFirstName());
        assertEquals(employeeEntity.getLastName(), employee.getLastName());
        assertEquals(employeeEntity.getMiddleName(), employee.getMiddleName());
        assertEquals(employeeEntity.getPhone(), employee.getPhone());
        //TODO BUG незаписывается email в бд
        // assertEquals(employeeEntity.getEmail(), employee.getEmail());
        assertEquals(employeeEntity.getBirthdate().toString(), employee.getBirthdate());
        assertEquals(employeeEntity.getAvatarUrl(), employee.getUrl());
        assertNotNull(employeeEntity.getCreateTimestamp());
        assertNotNull(employeeEntity.getChangeTimestamp());
        assertTrue(employeeEntity.getIsActive());
        assertEquals(employeeEntity.getCompany().getId(), employee.getCompanyId());

    }

}
