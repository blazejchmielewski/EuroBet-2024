package pl.chmielewski.Euro.exception;

public class TokenWasNotPrepareWell extends RuntimeException {
    public TokenWasNotPrepareWell() {
        super("Provided token is invalid");
    }
}
