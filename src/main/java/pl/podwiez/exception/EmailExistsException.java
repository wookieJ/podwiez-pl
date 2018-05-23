package pl.podwiez.exception;

public class EmailExistsException extends Exception {
    public EmailExistsException(String message) {
        super(message);
    }
}
