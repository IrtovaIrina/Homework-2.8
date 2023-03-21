package pro.sky.homework.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.EmployeeNameException;
import pro.sky.homework.service.EmployeeService;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeNameException.class)
    public String handleException(EmployeeNameException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping("/employee/add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName
            ,@RequestParam("salary") int salary, @RequestParam("department") int department) {
        employeeService.checkName(firstName,lastName);
        return employeeService.addEmployee(firstName,lastName,salary,department);


    }
    @GetMapping("/employee/remove")
    public Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName
            , @RequestParam("salary") int salary, @RequestParam("department") int department) {
        employeeService.checkName(firstName,lastName);
        return employeeService.removeEmployee(firstName,lastName,salary, department);
    }
    @GetMapping("/employee/contains")
    public Employee contains(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName
            ,@RequestParam("salary") int salary, @RequestParam("department") int department) {
        return employeeService.containsEmployee(firstName,lastName,salary,department);
    }
    @GetMapping("/departments/max-salary")
    public Employee maxSalary(@RequestParam("departmentId") String departmentId){
        return employeeService.printMaxSalaryName(Integer.parseInt(departmentId));
    }
    @GetMapping("/departments/min-salary")
    public Employee minSalary(@RequestParam("departmentId") String departmentId){
        return employeeService.printMinSalaryName(Integer.parseInt(departmentId));
    }
    @GetMapping("/departments/all")
    public Map<String, List<Employee>> allByDepartmentId(@RequestParam(required = false) Integer departmentId) {
        return employeeService.getAll(departmentId);
    }

    @GetMapping
    public Collection <Employee> allEmployees(){
        return employeeService.returnEmployees();
    }
}