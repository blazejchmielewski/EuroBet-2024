package pl.chmielewski.Euro.Betting.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.Euro.Authentication.user.User;
import pl.chmielewski.Euro.Authentication.user.UserRepository;
import pl.chmielewski.Euro.exception.PaymentNotFoundException;
import pl.chmielewski.Euro.request.CreatePayment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepo paymentRepo;
    private final UserRepository userRepository;

    @Autowired
    public PaymentService(PaymentRepo paymentRepo, UserRepository userRepository) {
        this.paymentRepo = paymentRepo;
        this.userRepository = userRepository;
    }

    public List<Payment> usersPayments(){
        return paymentRepo.findAll();
    }

    public void addPayment(CreatePayment newPayment){
        User user = userRepository.findUserById(newPayment.userId());
        Payment payment = new Payment(user, new BigDecimal(newPayment.amount()), LocalDateTime.now());
        paymentRepo.save(payment);
    }

    public UserStatus getUserPaymentStatus(Long id){
        double userPaymentStatus = paymentRepo.getUserPaymentStatus(id).orElseThrow(PaymentNotFoundException::new);
        return new UserStatus(userPaymentStatus);
    }

    public Optional<List<PaymentHistory>> getPaymentHistories() {
        Optional<List<Object[]>> results = paymentRepo.getPaymentHistory();
        return results.map(objects -> objects.stream()
                .map(this::mapToPaymentHistory)
                .collect(Collectors.toList()));
    }

    private PaymentHistory mapToPaymentHistory(Object[] result) {
        String firstname = (String) result[0];
        String lastname = (String) result[1];
        Double payment = result[2] != null ? ((Number) result[2]).doubleValue() : 0.0;
        LocalDateTime timestamp = result[3] != null ? ((Timestamp) result[3]).toLocalDateTime() : null;

        return new PaymentHistory(firstname, lastname, payment, timestamp);
    }
}
