package pro.sky.homework.exeption;

public class EmployeeDepartmentException extends RuntimeException {
    public EmployeeDepartmentException() {
        super();
    }

    public EmployeeDepartmentException(String message) {
        super(message);
    }

    public EmployeeDepartmentException(String message, Throwable t) {
        super(message, t);
    }

    public EmployeeDepartmentException(Throwable t) {
        super(t);
    }


}
