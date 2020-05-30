public class CabInvoiceException extends RuntimeException{

    public enum ExceptionType {
        IMPROPER_SUBSCRIPTION,CANNOT_CREATE_FILE, ENTERED_NULL,ENTERED_EMPTY;
    }
    ExceptionType type;

    public CabInvoiceException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
