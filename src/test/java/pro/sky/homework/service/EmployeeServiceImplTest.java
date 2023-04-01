package pro.sky.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.homework.Employee;

import java.util.List;

public class EmployeeServiceImplTest {
    private final EmployeeServiceImpl out = new EmployeeServiceImpl();
    Employee e1 = new Employee("Ivan", "Ivanov", 4574, 2);
    Employee e2 =new Employee("Petr", "Petrov", 896, 4);
    Employee e3 =new Employee("Petr", "Ivanov", 45774, 2);
    Employee e4 =new Employee("Ivan", "Petrov", 4575674, 2);
    @Test
    void addEmployee_success(){
        Assertions.assertEquals(out.addEmployee("Ivan", "Ivanov", 4574, 2),e1);
        Assertions.assertTrue(out.returnEmployees().contains(e1));
    }
    @Test
    void removeEmployee_success(){
        Assertions.assertTrue(out.returnEmployees().contains(e1));
        out.removeEmployee("Ivan", "Ivanov", 4574, 2);
        Assertions.assertFalse(out.returnEmployees().contains(e1));
    }
    @Test
    void containsEmployee_success(){
        Assertions.assertEquals(out.containsEmployee("Ivan", "Ivanov", 4574, 2),e1);
        Assertions.assertTrue(out.returnEmployees().contains(e1));
    }


}
