package pro.sky.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.EmployeeAlreadyAddedException;
import pro.sky.homework.exeption.EmployeeDepartmentException;
import pro.sky.homework.exeption.EmployeeNotFoundException;
import pro.sky.homework.exeption.EmployeeStorageIsFullException;
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

    @Override
    public Map<String, List<Employee>> getAll(Integer departmentId) {
        return employeesList.stream()
                .filter(employee -> departmentId == null || employee.getDepartment() == departmentId)
                .collect(Collectors.groupingBy(
                        employee -> Objects.toString(employee.getDepartment()),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );
    }
}

