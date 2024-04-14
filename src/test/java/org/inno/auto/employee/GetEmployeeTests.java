package org.inno.auto.employee;

import org.inno.auto.model.Employee;
import org.inno.auto.model.PostEmployeeResponse;
import org.junit.jupiter.api.Test;
import static org.inno.auto.steps.EmployeeApiSteps.*;
import static org.junit.jupiter.api.Assertions.*;
public class GetEmployeeTests {

    @Test
    public void firstTest() {
        getEmployeeByCompanyId(1379, 200);
    }

    @Test
    public void secondTest(){
        getEmployeeById(4694,200);
    }

    @Test
    public void thirdTest(){
        Employee employee = new Employee()
                .setFirstName("Игорёк")
                .setLastName("Шестогоров")
                .setCompanyId(1543)
                .setPhone("+79998887766");
        postEmployee(employee,201);
    }
    @Test
    public void fourthTest(){
        Employee employee = new Employee()
                .setFirstName("Игорёк")
                .setLastName("Шестогоров")
                .setCompanyId(1543)
                .setPhone("+79998887766");
        PostEmployeeResponse response = postEmployee(employee,201);
        employee.setFirstName("CЕРЕГА");
        Employee employee1 =  patchEmployee(employee,response.getId(),200);
        System.out.println(employee1);
    }
}
