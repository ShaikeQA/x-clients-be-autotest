package org.inno.auto.employee;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.inno.auto.db.entity.CompanyEntity;
import org.inno.auto.db.entity.EmployeeEntity;
import org.inno.auto.db.jpa.PUI;
import org.inno.auto.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import static org.inno.auto.steps.api.EmployeeApiSteps.*;
import static org.inno.auto.steps.db.CompanyDbSteps.*;
import static org.inno.auto.steps.db.EmployeeDbSteps.*;
import static org.inno.auto.steps.generate.RandomDataForDB.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetEmployeeTests {
    EmployeeEntity employee1;
    EmployeeEntity employee2;
    EmployeeEntity employee3;
    CompanyEntity company;

    @BeforeEach
    public void setUp() {
        company = createRandomCompany();
        employee1 = createRandomEmployee(company);
        employee2 = createRandomEmployeeOnlyWithRequiredAttributes(company);
        employee3 = createRandomEmployee(company);

        createCompanyFromDB(company);
        createEmployeeFromDB(employee1);
        createEmployeeFromDB(employee2);
        createEmployeeFromDB(employee3);
    }


    @Test
    @DisplayName("Получение трех сотрудников по одному companyId")
    public void getThreeEmployeeFromOneCompany() {
       Response response =  getEmployeeByCompanyId(company.getId(),200);
       List<EmployeeEntity> employees = new ArrayList<>(JsonPath.from(response.getBody().asString()).getList("$", EmployeeEntity.class));

       assertTrue(employees.contains(employee1));
       assertTrue(employees.contains(employee2));
       assertTrue(employees.contains(employee3));
    }
}
