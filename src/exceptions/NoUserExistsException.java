package exceptions;

public class NoUserExistsException extends Exception {
    public NoUserExistsException() {
        super("User was not found.");
    }

    public NoUserExistsException(String email, String password) {
        super("User with email \"" + email + "\" and password \"" + password + "\" was not found.");
    }

    public NoUserExistsException(long id) {
        super("User with id " + id + " was not found.");
    }
}
