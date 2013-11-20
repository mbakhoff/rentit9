package esi.rentit9.interop;

public class InteropException extends RuntimeException {
    public InteropException() {
    }

    public InteropException(String message) {
        super(message);
    }

    public InteropException(String message, Throwable cause) {
        super(message, cause);
    }
}
