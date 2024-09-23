package pl.chmielewski.Euro.request;

public record CreatePayment(
        Long userId,
        Integer amount
){
}
