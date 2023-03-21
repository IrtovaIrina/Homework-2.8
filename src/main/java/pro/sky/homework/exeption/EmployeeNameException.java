package pro.sky.homework.exeption;

public class EmployeeNameException extends RuntimeException {
    public EmployeeNameException() {
        super();
    }

    public EmployeeNameException(String message) {
        super(message);
    }

    public EmployeeNameException(String message, Throwable t) {
        super(message, t);
    }

    public EmployeeNameException(Throwable t) {
        super(t);
    }


}

