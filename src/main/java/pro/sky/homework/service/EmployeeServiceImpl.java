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
    private final Map<String, Employee> employees ;
    private static List<Employee> employeesList = new ArrayList<>();

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
        this.employeesList = new ArrayList<>();
    }

    @GetMapping
    public Employee addEmployee(String firstName,String lastName, int salary, int department)  {
        Employee employee = new Employee( firstName,lastName,salary,department);
        checkEmployee(employee);
        employees.put(employee.getFullName(),employee);
        employeesList.add(employee);
        return employee;
    }
    @GetMapping
    public Employee removeEmployee(String firstName,String lastName, int salary, int department)  {
        Employee employee = new Employee( firstName, lastName, salary, department);
        checkEmployeeExistence(employee);
        employees.remove(employee.getFullName());
        employeesList.remove(employee);
        return employee;

    }
    @GetMapping
    public Employee containsEmployee(String firstName,String lastName, int salary, int department){
        Employee employee = new Employee( firstName, lastName,salary,department);
        checkEmployeeExistence(employee);
        employees.containsKey(employee.getFullName());
        employeesList.contains(employee);
        return employee;
    }
    @GetMapping
    public void checkEmployeeExistence(Employee employee) {
        if (!employees.containsKey(employee.getFullName())){
            throw new EmployeeNotFoundException();
        }
    }
    @GetMapping
    public void checkEmployee(Employee employee) {
        if (employees.size() > 10){
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(employee.getFullName())){
            throw new EmployeeAlreadyAddedException();
        }
        if (employee.getDepartment() < 1 || employee.getDepartment() > 5){
            throw new EmployeeDepartmentException();
        }
    }
    @GetMapping
    public Collection<Employee> returnEmployees() {
        return employeesList;
    }

    @GetMapping
    public Employee printMinSalaryName(int department){
        return employeesList.stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary)).get();

    }

    @GetMapping
    public Employee printMaxSalaryName(int department){
        return employeesList.stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
    }

    @GetMapping
    public Map<String, List<Employee>> getAll(Integer departmentId) {
        return employeesList.stream()
                .filter(employee -> departmentId == null || employee.getDepartment() == departmentId)
                .collect(Collectors.groupingBy(
                        employee -> Objects.toString(employee.getDepartment()),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );
    }
    @GetMapping
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

