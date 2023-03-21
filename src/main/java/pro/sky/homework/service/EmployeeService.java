package pro.sky.homework.service;

import pro.sky.homework.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName,String lastName, int salary, int department);
    Employee removeEmployee(String firstName,String lastName, int salary, int department);
    Employee containsEmployee(String firstName,String lastName, int salary, int department);
    void checkEmployee(Employee employee);
    void checkEmployeeExistence(Employee employee);
    Collection<Employee> returnEmployees();
    Employee printMinSalaryName(int department);
    Employee printMaxSalaryName(int department);
    Map<String, List<Employee>> getAll(Integer departmentId);
    void checkName(String firstName, String lastName);
}