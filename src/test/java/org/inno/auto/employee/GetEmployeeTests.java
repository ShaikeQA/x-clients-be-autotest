package org.inno.auto.employee;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.inno.auto.db.entity.CompanyEntity;
import org.inno.auto.db.entity.EmployeeEntity;
import org.inno.auto.model.Employee;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.inno.auto.steps.api.EmployeeApiSteps.*;
import static org.inno.auto.steps.db.CompanyDbSteps.*;
import static org.inno.auto.steps.db.EmployeeDbSteps.*;
import static org.inno.auto.steps.generate.RandomDataForDB.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetEmployeeTests {
    static EmployeeEntity employee1;
    static EmployeeEntity employee2;
    static EmployeeEntity employee3;
    static EmployeeEntity employee4;
    static EmployeeEntity employee5;
    static CompanyEntity company1;
    static CompanyEntity company2;
    static CompanyEntity company3;

    //Решил использовать @BeforeAll и @AfterAll тк рестовый метод производит только чтение.
    @BeforeAll
    public static void setUp() {
        //Создание компании с тремя сотрудниками
        company1 = createRandomCompany();
        employee1 = createRandomEmployee(company1);
        employee2 = createRandomEmployeeOnlyWithRequiredAttributes(company1);
        employee3 = createRandomEmployee(company1);
        //Создание компании с 1 сотрудником с полными данными
        company2 = createRandomCompany();
        employee4 = createRandomEmployee(company2);
        //Создание компании с 1 сотрудником только с обязательными данными
        company3 = createRandomCompany();
        employee5 = createRandomEmployeeOnlyWithRequiredAttributes(company3);

        //Запись компаний и сотрудников в БД
        createCompanyFromDB(company1);
        createCompanyFromDB(company2);
        createCompanyFromDB(company3);
        createEmployeeFromDB(employee1);
        createEmployeeFromDB(employee2);
        createEmployeeFromDB(employee3);
        createEmployeeFromDB(employee4);
        createEmployeeFromDB(employee5);

    }

    @AfterAll
    public static void tearDown() {
        if (employee1 != null) {
            deleteEmployeeFromDB(employee3);
        }
        if (employee2 != null) {
            deleteEmployeeFromDB(employee2);
        }
        if (employee3 != null) {
            deleteEmployeeFromDB(employee1);
        }
        if (employee4 != null) {
            deleteEmployeeFromDB(employee4);
        }
        if (employee5 != null) {
            deleteEmployeeFromDB(employee5);
        }
        if (company1 != null) {
            deleteCompanyFromDB(company1);
        }
        if (company2 != null) {
            deleteCompanyFromDB(company2);
        }
        if (company3 != null) {
            deleteCompanyFromDB(company3);
        }

    }

    @Test
    @DisplayName("Получение одного сотрудников, со всеми заполнеными полями по companyId")
    @Description("Валидация выходны параметров, валидация размера массива")
    public void getOneEmployeeWithAllProperties() {
        Response response = getEmployeeByCompanyId(company2.getId(), 200);
        List<Employee> employees = new ArrayList<>(JsonPath.from(response.getBody().asString()).getList("$", Employee.class));
        assertEquals(1, employees.size());

        assertEquals(employee1.getId(), employees.get(0).getId());
        assertEquals(employee1.getFirstName(), employees.get(0).getFirstName());
        assertEquals(employee1.getLastName(), employees.get(0).getLastName());
        assertEquals(employee1.getMiddleName(), employees.get(0).getMiddleName());
        assertEquals(employee1.getPhone(), employees.get(0).getPhone());
        assertEquals(employee1.getEmail(), employees.get(0).getEmail());
        assertEquals(employee1.getBirthdate(), employees.get(0).getBirthdate());
        assertEquals(employee1.getAvatarUrl(), employees.get(0).getAvatar_url());
        assertEquals(employee1.getCreateTimestamp(), employees.get(0).getCreateDateTime());
        assertEquals(employee1.getChangeTimestamp(), employees.get(0).getLastChangedDateTime());
        assertEquals(employee1.getIsActive(), employees.get(0).getIsActive());
        assertEquals(employee1.getCompany().getId(), employees.get(0).getCompanyId());}

    @Test
    @DisplayName("Получение трех сотрудников по одному companyId")
    @Description("Валидация размера массива")
    public void getThreeEmployeeFromOneCompany() {
        Response response = getEmployeeByCompanyId(company1.getId(), 200);
        List<Employee> employees = new ArrayList<>(JsonPath.from(response.getBody().asString()).getList("$", Employee.class));
        assertEquals(3, employees.size());
    }
}
