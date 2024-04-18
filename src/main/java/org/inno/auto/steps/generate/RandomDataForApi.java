package org.inno.auto.steps.generate;

import com.github.javafaker.Faker;
import org.inno.auto.model.Employee;

import java.time.LocalDate;
import java.util.Locale;


public class RandomDataForApi {
    private static final Faker faker = new Faker(new Locale("ru"));
    private static Employee employee;

    public static Employee forCreateRandomEmployee(int companyId) {
        employee = new Employee();

        employee.setIsActive(faker.bool().bool());
        employee.setFirstName(faker.name().firstName());
        employee.setLastName(faker.name().lastName());
        employee.setMiddleName(faker.name().firstName() + "ович");
        employee.setPhone(faker.numerify("+79#########"));
        employee.setEmail(faker.internet().emailAddress());
        employee.setBirthdate(LocalDate.now().toString());
        employee.setUrl(faker.internet().avatar());
        employee.setCompanyId(companyId);

        return employee;
    }
    public static Employee forPatchRandomEmployee() {
        employee = new Employee();

        employee.setIsActive(faker.bool().bool());
        employee.setLastName(faker.name().lastName());
        employee.setPhone(faker.numerify("+79#########"));
        employee.setEmail(faker.internet().emailAddress());
        employee.setUrl(faker.internet().avatar());

        return employee;
    }

}
