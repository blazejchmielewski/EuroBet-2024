package pl.chmielewski.Euro.exception;

public class UserByIdNotFoundException extends RuntimeException {
    public UserByIdNotFoundException(Long id) {
        super("User was not found with id: " + id);
    }
}
