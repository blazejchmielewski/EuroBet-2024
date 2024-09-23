package pl.chmielewski.Euro.exception;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException() {
        super("Nie znaleziono wpłat");
    }
}
