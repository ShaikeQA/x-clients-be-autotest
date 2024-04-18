package org.inno.auto.steps.generate;

import com.github.javafaker.Faker;
import org.inno.auto.db.entity.CompanyEntity;
import org.inno.auto.db.entity.EmployeeEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Locale;

public class RandomDataForDB {
    private static final Faker faker = new Faker(new Locale("ru"));
    private static EmployeeEntity employee;

    public static CompanyEntity createRandomCompany() {
        CompanyEntity company = new CompanyEntity();

        company.setIsActive(faker.bool().bool());
        company.setCreateTimestamp(OffsetDateTime.now());
        company.setChangeTimestamp(OffsetDateTime.now());
        company.setName(faker.company().name());
        company.setDescription(faker.lorem().sentence(10));

        if (faker.bool().bool()) {
            company.setDeletedAt(Instant.now());
        }

        return company;
    }

    public static EmployeeEntity createRandomEmployee(CompanyEntity company) {
        employee = new EmployeeEntity();

        employee.setIsActive(faker.bool().bool());
        employee.setCreateTimestamp(OffsetDateTime.now());
        employee.setChangeTimestamp(OffsetDateTime.now());
        employee.setFirstName(faker.name().firstName());
        employee.setLastName(faker.name().lastName());
        employee.setMiddleName(faker.name().firstName() + "ович");
        employee.setPhone(faker.numerify("+79#########"));
        employee.setEmail(faker.internet().emailAddress());
        employee.setBirthdate(LocalDate.now());
        employee.setAvatarUrl(faker.internet().avatar());
        employee.setCompany(company);

        return employee;
    }

    public static EmployeeEntity createRandomEmployeeOnlyWithRequiredAttributes(CompanyEntity company) {
        employee = new EmployeeEntity();

        employee.setIsActive(faker.bool().bool());
        employee.setCreateTimestamp(OffsetDateTime.now());
        employee.setChangeTimestamp(OffsetDateTime.now());
        employee.setFirstName(faker.name().firstName());
        employee.setLastName(faker.name().lastName());
        employee.setPhone(faker.numerify("+79#########"));
        employee.setCompany(company);

        return employee;
    }
}
