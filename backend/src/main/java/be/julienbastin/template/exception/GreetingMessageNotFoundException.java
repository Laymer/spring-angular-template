package be.julienbastin.template.exception;

public class GreetingMessageNotFoundException extends GlobalException {
    public GreetingMessageNotFoundException(String key) {
        super("No greeting message found for key " + key);
    }
}
