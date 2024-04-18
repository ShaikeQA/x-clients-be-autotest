package org.inno.auto.steps.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.transaction.Transactional;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.inno.auto.db.entity.CompanyEntity;
import org.inno.auto.db.entity.EmployeeEntity;
import org.inno.auto.db.jpa.PUI;

import java.util.Properties;

import static org.inno.auto.steps.db.CompanyDbSteps.getCompanyFromDB;

public class EmployeeDbSteps {

    private static final EntityManager entityManager;

    static {
        String connectionString = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
        String user = "x_clients_user";
        String pass = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";

        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.connection.url", connectionString);
        props.put("hibernate.connection.username", user);
        props.put("hibernate.connection.password", pass);
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.connection.autocommit", "true");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "validate"); //update

        PersistenceUnitInfo persistenceUnitInfo = new PUI(props);
        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory factory = hibernatePersistenceProvider.createContainerEntityManagerFactory(persistenceUnitInfo, persistenceUnitInfo.getProperties());
        entityManager = factory.createEntityManager();
    }

    public static void createEmployeeFromDB(EmployeeEntity employeeEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(employeeEntity);
        entityManager.getTransaction().commit();
    }

    public static EmployeeEntity getEmployeeFromDB(int employeeId) {
        return entityManager.find(EmployeeEntity.class, employeeId);
    }

    public static void deleteEmployeeFromDB(EmployeeEntity employeeEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(employeeEntity);
        entityManager.getTransaction().commit();
    }

    public static void deleteEmployeeFromDB(int employeeId) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(EmployeeEntity.class, employeeId));
        entityManager.getTransaction().commit();
    }

    public static void deleteAllEmployeeFromDB(int companyId) {
        Query query = entityManager.createQuery("DELETE FROM EmployeeEntity e WHERE e.company = :companyId");
        query.setParameter("companyId", getCompanyFromDB(companyId));
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}

