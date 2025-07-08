package be.julienbastin.template.exception;

public abstract class GlobalException extends RuntimeException {
    protected GlobalException(String message) {
        super(message);
    }
}
