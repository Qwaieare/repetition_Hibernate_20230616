package impl;

import dao.EmployeeDAO;
import models.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public void create(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }
    @Override
    public Employee readById(int id) {
       return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> readAll() {
        return entityManager.createQuery("SELECT e FROM Employee e").getResultList();
    }

    @Override
    public void updateEmployee(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.merge(employee);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
     }

}
