package pl.chmielewski.Euro.exception;

public class UserByEmailNotFoundException extends RuntimeException{
    public UserByEmailNotFoundException(String email) {
        super("User was not found with mail: " + email);
    }
}
