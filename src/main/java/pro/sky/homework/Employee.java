package pro.sky.homework;


import java.util.Objects;

public class Employee {

    private final String firstName;
    private final String lastName;
    private int  salary;
    private int department;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Employee(String firstName,String lastName, int salary, int department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Имя сотрудника - " + this.firstName + "/n"
                + "Фамилия сотрудника - " + this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.firstName,this.lastName);
    }
}
