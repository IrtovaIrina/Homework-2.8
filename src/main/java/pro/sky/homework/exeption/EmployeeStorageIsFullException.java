package pro.sky.homework.exeption;


public class EmployeeStorageIsFullException extends RuntimeException  {
    public EmployeeStorageIsFullException() {
        super();
    }

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }

    public EmployeeStorageIsFullException(String message, Throwable t) {
        super(message, t);
    }

    public EmployeeStorageIsFullException(Throwable t) {
        super(t);
    }




}
