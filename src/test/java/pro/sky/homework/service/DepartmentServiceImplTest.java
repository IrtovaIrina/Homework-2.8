package pro.sky.homework.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.EmployeeDepartmentException;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

        @Mock
        private EmployeeService employeeService;
        @InjectMocks
        private DepartmentServiceImpl out;
        Integer departmentId = 1;
        Integer minSalary = 258;
        Integer maxSalary = 30000;

        Employee employeeWithMaxSalary = new Employee("Ivan", "Ivanov", maxSalary,departmentId);
        Employee employeeWithMinSalary = new Employee("Petr", "Petrov", minSalary, departmentId);
        Employee employeeFromOtherDepartmentWithMinSalary = new Employee("Ivan", "Petrov", 30005,3);
        Employee employeeFromOtherDepartmentWithMaxSalary = new Employee("Ivan", "Petrov", 25,3);

        List<Employee> employees = List.of(employeeWithMaxSalary, employeeWithMinSalary
                        ,employeeFromOtherDepartmentWithMinSalary,employeeFromOtherDepartmentWithMaxSalary);

        Map<Integer, List<Employee>> expectedMapForGetAllDepartmentId = employees.stream()
                .filter(employee ->  employee.getDepartment() == departmentId)
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment(),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );
        Map<Integer, List<Employee>> expectedMapForGetAll = employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment(),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );

        @Test
        void getEmployeeWithMinSalary_success() {

                when(employeeService.returnEmployees()).thenReturn(employees);
                Employee actualEmployee = out.minSalaryName(departmentId);
                assertEquals(employeeWithMinSalary, actualEmployee);
                assertTrue(minSalary < maxSalary);
        }
        @Test
        void getEmployeeWithMinSalary_withEmployeeDepartmentExceptionException() {
                when(employeeService.returnEmployees()).thenReturn(employees);
                assertThrows(EmployeeDepartmentException.class,
                                () -> { out.minSalaryName(7);
                                });
        }
        @Test
        void getEmployeeWithMaxSalary_success() {

                when(employeeService.returnEmployees()).thenReturn(employees);
                Employee actualEmployee = out.maxSalaryName(departmentId);
                assertEquals(employeeWithMaxSalary, actualEmployee);
                assertTrue(minSalary < maxSalary);
        }
        @Test
        void getEmployeeWithMaxSalary_withEmployeeDepartmentExceptionException() {
                when(employeeService.returnEmployees()).thenReturn(employees);
                assertThrows(EmployeeDepartmentException.class,
                        () -> { out.maxSalaryName(7);
                        });
        }
        @Test
        void getAllByDepartmentId_success() {

                when(employeeService.returnEmployees()).thenReturn(employees);
                Map<Integer,List<Employee>> actualEmployee = out.getAllByDepartmentId(departmentId);

                assertEquals(expectedMapForGetAllDepartmentId, actualEmployee);
        }
        @Test
        void getAllByDepartmentId_departmentIdIsNull() {
                when(employeeService.returnEmployees()).thenReturn(Collections.emptyList());
                Map<Integer, List<Employee>> actualResult = out.getAllByDepartmentId(departmentId);
                assertTrue(actualResult.isEmpty());
        }
        @Test
        void getAll_success() {

                when(employeeService.returnEmployees()).thenReturn(employees);
                Map<Integer,List<Employee>> actualEmployee = out.getAll();
                assertEquals(expectedMapForGetAll, actualEmployee);
        }
        @Test
        void getAll_departmentIdIsNull() {
                when(employeeService.returnEmployees()).thenReturn(Collections.emptyList());
                Map<Integer, List<Employee>> actualResult = out.getAll();
                assertTrue(actualResult.isEmpty());
        }
        @Test
        void allSalary_success() {

                when(employeeService.returnEmployees()).thenReturn(employees);
                int actualResult = out.allSalary(departmentId);
                assertEquals(minSalary + maxSalary, actualResult);
        }
        @Test
        void allSalary_withEmployeeListIsNull() {
                when(employeeService.returnEmployees()).thenReturn(Collections.emptyList());
                int actualResult = out.allSalary(departmentId);
                assertEquals(0, actualResult);
        }
        @Test
        void returnEmployees_success() {
                when(employeeService.returnEmployees()).thenReturn(employees);
                assertEquals(employees, employeeService.returnEmployees());
        }
        @Test
        void returnEmployees_withEmployeeListIsNull() {
                when(employeeService.returnEmployees()).thenReturn(Collections.emptyList());
                assertTrue(out.returnEmployees().isEmpty());
        }





}
