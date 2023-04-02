package pro.sky.homework.service;

import org.apache.commons.lang3.StringUtils;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, int salary, int departmentId);

    Employee containsEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    List<Employee> returnEmployees();
}