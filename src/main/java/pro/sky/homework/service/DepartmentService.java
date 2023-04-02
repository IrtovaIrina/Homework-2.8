package pro.sky.homework.service;

import pro.sky.homework.Employee;
import pro.sky.homework.exeption.EmployeeDepartmentException;

import java.util.*;
import java.util.stream.Collectors;

public interface DepartmentService {

    Employee minSalaryName(int department);
    Employee maxSalaryName(int department);

    Map<Integer, List<Employee>> getAllByDepartmentId(Integer departmentId);
    Map<Integer, List<Employee>> getAll();
    int allSalary(int departmentId);
    Collection<Employee> returnEmployees();
}
