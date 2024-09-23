package pl.chmielewski.Euro.Betting.payment;

import jakarta.persistence.*;
import pl.chmielewski.Euro.Authentication.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments", schema = "euro")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pay_user_id")
    private User user;

    private BigDecimal payment;

    private LocalDateTime timestamp;

    public Payment() {}

    public Payment(User user, BigDecimal payment, LocalDateTime timestamp) {
        this.user = user;
        this.payment = payment;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
