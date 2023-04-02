package pro.sky.homework.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_EMPLOYEES_COUNT = 2;

    private static List<Employee> employees = new ArrayList<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int departmentId) {

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee employee = new Employee(firstName, lastName, salary, departmentId);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник");
        }

        employees.add(employee);

        return employee;
    }

    @Override
    public Employee containsEmployee(String firstName, String lastName) {
        Employee employee = null;

        for (Employee e : employees) {
            if (e != null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())) {
                employee = e;
            }
        }

        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = containsEmployee(firstName, lastName);

        for (Employee e : employees) {
            if (e.equals(employee)) {
                employees.remove(e);
                return e;
            }
        }
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public List<Employee> returnEmployees() {
        return employees;
    }

}


