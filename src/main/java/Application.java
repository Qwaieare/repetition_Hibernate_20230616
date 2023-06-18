import dao.EmployeeDAO;
import impl.EmployeeDAOImpl;
import models.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IllegalArgumentException {
        // Создаем экземпляр EntityManagerFactory, указывая persistence unit
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        // Создаем экземпляр EntityManager из EntityManagerFactory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Начинаем транзакцию
        entityManager.getTransaction().begin();

        // Создаем JPQL-запрос для выборки студентов с возрастом больше minAge
        String jpqlQuery = "SELECT s FROM Employee s WHERE s.age > :minAge";
        // Создаем объект запроса с указанием типа возвращаемого результата
        TypedQuery<Employee> query = entityManager.createQuery(jpqlQuery, Employee.class);
        // Устанавливаем значение параметра minAge в запросе
        query.setParameter("minAge", 31);
        // Выполняем запрос и получаем результат в виде списка
        List<Employee> employees = query.getResultList();

        // Завершаем транзакцию
        entityManager.getTransaction().commit();

        // Выводим информацию в консоль
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("Employee first_name: " + employee.getFirst_name());
            System.out.println("Employee last_name: " + employee.getLast_name());
            System.out.println("Employee gender: " + employee.getGender());
            System.out.println("Student age: " + employee.getAge());
            System.out.println("------------");
        }
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = new Employee("Alisa", "Vov", "woman", 18, 3);
        employeeDAO.create(employee);

        System.out.println(employeeDAO.readById(3));

        List<Employee> employeez = employeeDAO.readAll();
        employeez.forEach(System.out::println);

        Employee employeel = new Employee(117, "Sima", "Simov", "man", 28, 6);
        employeeDAO.updateEmployee(employeel);

        Employee employeed = employeeDAO.readById(20);
        employeeDAO.delete(employeed);

        // Закрываем EntityManager и EntityManagerFactory
       entityManager.close();
       entityManagerFactory.close();
    }

}


