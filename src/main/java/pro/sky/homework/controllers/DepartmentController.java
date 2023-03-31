package pro.sky.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.EmployeeDepartmentException;
import pro.sky.homework.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeDepartmentException.class)
    public String handleException(EmployeeDepartmentException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @GetMapping("/{id}/salary/max")
    public int maxSalary(@PathVariable("id") int id){
        return departmentService.maxSalaryName(id).getSalary();
    }
    @GetMapping("{id}/salary/min")
    public int minSalary(@PathVariable("id") int id){
        return departmentService.minSalaryName(id).getSalary();
    }
    @GetMapping( "/{id}/employees")
    public Map<Integer, List<Employee>> allByDepartmentId(@PathVariable("id") int id) {
        return departmentService.getAllByDepartmentId(id);
    }
    @GetMapping( "/employees")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }
    @GetMapping("/{id}/salary/sum")

    public int allSalary(@PathVariable("id") int id) {
        return departmentService.allSalary(id);
    }

    @GetMapping
    public Collection<Employee> allEmployees(){
        return departmentService.returnEmployees();
    }

}
