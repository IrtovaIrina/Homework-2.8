package pro.sky.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.homework.Employee;
import pro.sky.homework.exeption.EmployeeAlreadyAddedException;
import pro.sky.homework.exeption.EmployeeNotFoundException;
import pro.sky.homework.exeption.EmployeeStorageIsFullException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {
    private final EmployeeServiceImpl out = new EmployeeServiceImpl();
    Employee e1 = new Employee("Ivan", "Ivanov", 4574, 2);
    @Test
    void addEmployee_success(){
        Assertions.assertEquals(out.addEmployee("Ivan", "Ivanov", 4574, 2),e1);
        Assertions.assertTrue(out.returnEmployees().contains(e1));
        //out.removeEmployee("Ivan", "Ivanov");
    }
    @Test
    void removeEmployee_success(){
        out.addEmployee("Ivan", "Ivanov", 4574, 2);
        Assertions.assertEquals(out.removeEmployee("Ivan", "Ivanov"),e1);
        Assertions.assertFalse(out.returnEmployees().contains(e1));
        out.addEmployee("Ivan", "Ivanov", 4574, 2);
        out.removeEmployee("Ivan", "Ivanov");
        Assertions.assertFalse(out.returnEmployees().contains(e1));

    }
    @Test
    void containsEmployee_success(){
        out.removeEmployee("Ivan", "Ivanov");
        out.addEmployee("Ivan", "Ivanov", 4574, 2);
        Assertions.assertEquals(out.containsEmployee("Ivan", "Ivanov"),e1);
        Assertions.assertTrue(out.returnEmployees().contains(e1));
    }
    @Test
    void addEmployees_withEmployeeStorageIsFullException() {
        out.addEmployee("Ivan", "Ivanov", 4574, 2);
        out.addEmployee("Ivana", "Ivanova", 4574, 2);
        assertThrows(EmployeeStorageIsFullException.class,
                () -> {
                    out.addEmployee("Ivana", "Petrova", 4574, 2);
                });
        out.removeEmployee("Ivan", "Ivanov");
        out.removeEmployee("Ivana", "Ivanova");

    }
    @Test
    void addEmployees_withEmployeeAlreadyAddedException() {

        out.addEmployee("Ivan", "Ivanov", 4574, 2);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> {
                    out.addEmployee("Ivan", "Ivanov", 4574, 2);
                });
    }
    @Test
    void removeEmployee_withEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class,
                () -> {
                    out.removeEmployee("Ivan", "Ivanov");
                });
    }
    @Test
    void containsEmployee_success_withEmployeeNotFoundException() {
        out.removeEmployee("Ivan", "Ivanov");
        assertThrows(EmployeeNotFoundException.class,
                () -> {
                    out.containsEmployee("Ivan", "Ivanov");
                });
    }
}
