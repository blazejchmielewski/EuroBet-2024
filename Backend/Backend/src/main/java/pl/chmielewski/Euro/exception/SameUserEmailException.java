package pl.chmielewski.Euro.exception;

public class SameUserEmailException extends RuntimeException{
    public SameUserEmailException() {
        super("The email is already taken");
    }
}
