package pro.sky.homework.service;

import org.apache.commons.lang3.StringUtils;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee addEmployee(String firstName,String lastName, int salary, int department);
    Employee removeEmployee(String firstName,String lastName, int salary, int department);
    Employee containsEmployee(String firstName,String lastName, int salary, int department);
    void checkEmployeeExistence(Employee employee);
    void checkEmployee(Employee employee);
    Collection<Employee> returnEmployees();
    void checkName(String firstName, String lastName);

}