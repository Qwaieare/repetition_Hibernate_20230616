package dao;

import models.Employee;

import java.util.List;

public interface EmployeeDAO {

    void create(Employee employee);

    Employee readById(int id);

    List<Employee> readAll();

    void updateEmployee(Employee employee);

    void delete(Employee employee);
}
