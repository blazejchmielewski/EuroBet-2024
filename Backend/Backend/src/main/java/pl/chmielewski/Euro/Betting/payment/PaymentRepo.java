package pl.chmielewski.Euro.Betting.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

    @Query(value = "select COALESCE(SUM(p.payment), 0) AS totalPayment " +
            "FROM euro.payments p " +
            "JOIN euro.users u ON u.user_id = p.pay_user_id " +
            "WHERE u.user_id = :userId " +
            "GROUP BY u.firstname, u.lastname",
            nativeQuery = true)
    Optional<Double> getUserPaymentStatus(@Param("userId") Long userId);


    @Query(value = "select u.firstname, u.lastname, p.payment, p.timestamp from euro.payments p " +
            "join euro.users u on u.user_id = p.pay_user_id order by (p.timestamp)",
            nativeQuery = true)
    Optional<List<Object[]>> getPaymentHistory();



}

