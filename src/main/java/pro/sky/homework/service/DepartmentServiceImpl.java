package pro.sky.homework.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.EmployeeAlreadyAddedException;
import pro.sky.homework.exeption.EmployeeDepartmentException;
import pro.sky.homework.exeption.EmployeeNameException;
import pro.sky.homework.exeption.EmployeeStorageIsFullException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public Collection<Employee> returnEmployees() {
        return employeeService.returnEmployees();
    }

    @Override
    public Employee minSalaryName(int departmentId){
        checkDepartment(departmentId);
        return returnEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparing(Employee::getSalary)).get();

    }

    @Override
    public Employee maxSalaryName(int departmentId){
        checkDepartment(departmentId);
        return returnEmployees().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
    }


    @Override
    public Map<Integer, List<Employee>> getAllByDepartmentId(Integer departmentId) {
        return returnEmployees().stream()
                .filter(employee -> departmentId == null || employee.getDepartment() == departmentId)
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment(),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );
    }
    public Map<Integer, List<Employee>> getAll() {
        return returnEmployees().stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment(),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );
    }
    @Override
    public void checkDepartment(int department) {
        if (department < 1 || department > 5){
            throw new EmployeeDepartmentException();
        }
    }
    @Override
    public int allSalary (int departmentId) {
        int sum = 0;
        for (Employee e : returnEmployees()){
            sum += e.getDepartment();
        }
        return sum;
    }
}
