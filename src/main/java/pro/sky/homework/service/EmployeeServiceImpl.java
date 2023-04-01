package pro.sky.homework.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private static List<Employee> employeesList = new ArrayList<>();

    public EmployeeServiceImpl() {
        employeesList = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName,String lastName, int salary, int department)  {
        Employee employee = new Employee( firstName,lastName,salary,department);
        checkEmployee(employee);
        employeesList.add(employee);
        return employee;
    }
    @Override
    public Employee removeEmployee(String firstName,String lastName, int salary, int department)  {
        Employee employee = new Employee( firstName, lastName, salary, department);
        checkEmployeeExistence(employee);
        for (Employee e : employeesList) {
            if (Objects.equals(e.getFullName(),employee.getFullName())) {
                employeesList.remove(e);
                return e;
            }
        }
        return employee;
    }
    @Override
    public Employee containsEmployee(String firstName,String lastName, int salary, int department){
        Employee employee = new Employee( firstName, lastName,salary,department);
        checkEmployeeExistence(employee);
        employeesList.contains(employee);
        return employee;
    }
    @Override
    public Collection<Employee> returnEmployees() {
        return employeesList;
    }
    @Override
    public void checkEmployeeExistence(Employee employee) {
        if (!employeesList.contains(employee.getFullName())){
            throw new EmployeeNotFoundException();
        }
    }
    @Override
    public void checkEmployee(Employee employee) {
        if (employeesList.size() > 10){
            throw new EmployeeStorageIsFullException();
        }
        if (employeesList.contains(employee.getFullName())){
            throw new EmployeeAlreadyAddedException();
        }
        if (employee.getDepartment() < 1 || employee.getDepartment() > 5){
            throw new EmployeeDepartmentException();
        }
    }

    @Override
    public void checkName(String firstName, String lastName){
        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)){
            throw new EmployeeNameException("Имя или фамилия не заполнены");
        }
        if(!StringUtils.capitalize(StringUtils.lowerCase(firstName)).equals(firstName)
                || !StringUtils.capitalize(StringUtils.lowerCase(lastName)).equals(lastName)) {
            throw new EmployeeNameException("Имя и Фамилия должны быть с большой буквы");
        }
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)){
            throw new EmployeeNameException("Имя и Фамилия должны состоять из букв");
        }
    }

}

