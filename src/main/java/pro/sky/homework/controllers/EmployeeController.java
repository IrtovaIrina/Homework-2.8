package pro.sky.homework.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.EmployeeNameException;
import pro.sky.homework.service.EmployeeService;
import pro.sky.homework.service.EmployeeServiceImpl;


@RestController
@RequestMapping
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    @ExceptionHandler(EmployeeNameException.class)
    public String handleException(EmployeeNameException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping("/employee/add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName
            ,@RequestParam("salary") int salary, @RequestParam("department") int department) {
        return employeeService.addEmployee(firstName,lastName,salary,department);


    }
    @GetMapping("/employee/remove")
    public Employee remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.removeEmployee(firstName,lastName);
    }
    @GetMapping("/employee/contains")
    public Employee contains(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.containsEmployee(firstName,lastName);
    }

}