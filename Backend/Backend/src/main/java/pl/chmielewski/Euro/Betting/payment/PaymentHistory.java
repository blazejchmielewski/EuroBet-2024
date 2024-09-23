package pl.chmielewski.Euro.Betting.payment;

import java.time.LocalDateTime;

public record PaymentHistory(
        String firstname,
        String lastname,
        Double payment,
        LocalDateTime timestamp
) {
}
