package org.inno.auto.employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.inno.auto.db.entity.CompanyEntity;
import org.inno.auto.db.entity.EmployeeEntity;
import org.inno.auto.db.jpa.PUI;
import org.inno.auto.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Properties;

import static org.inno.auto.steps.api.EmployeeApiSteps.*;

public class GetEmployeeTests {


    @BeforeEach
    public void setUp() {

    }


@Test
    public void test() {
            CompanyEntity company1543 = entityManager.find(CompanyEntity.class, 1543);

    EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName("Rinat");
        employee.setLastName("Rinat");
        employee.setEmail("rinat@gmail.com");
        employee.setPhone("+79998887766");
        employee.setCreateTimestamp(OffsetDateTime.now());
        employee.setChangeTimestamp(OffsetDateTime.now());
        employee.setCompany();

}


//    @Test
//    public void testGetEmployee() {
////        CompanyEntity company1543 = entityManager.find(CompanyEntity.class, 1543);
////        System.out.println("company2779 = " + company1543);
////        entityManager.persist();
//        CompanyEntity createCompany = new CompanyEntity();
//        createCompany.setName("RinatCompany");
//        createCompany.setChangeTimestamp(OffsetDateTime.now());
//        createCompany.setCreateTimestamp(OffsetDateTime.now());
//        System.out.println(createCompany);
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(createCompany);
//        entityManager.getTransaction().commit();
//        System.out.println("createCompany.getId() = " + createCompany.getId());
//    }
//
//    @Test
//    public void firstTest() {
//        getEmployeeByCompanyId(1379, 200);
//    }
//
//    @Test
//    public void secondTest(){
//        getEmployeeById(4694,200);
//    }
//
//    @Test
//    public void thirdTest(){
//        Employee employee = new Employee()
//                .setFirstName("Игорёк")
//                .setLastName("Шестогоров")
//                .setCompanyId(1543)
//                .setPhone("+79998887766");
//        postEmployee(employee,201);
//    }
//    @Test
//    public void fourthTest(){
//        Employee employee = new Employee()
//                .setFirstName("Игорёк")
//                .setLastName("Шестогоров")
//                .setCompanyId(1543)
//                .setPhone("+79998887766");
//        PostEmployeeResponse response = postEmployee(employee,201);
//        employee.setFirstName("CЕРЕГА");
//        Employee employee1 =  patchEmployee(employee,response.getId(),200);
//        System.out.println(employee1);
//    }
}
