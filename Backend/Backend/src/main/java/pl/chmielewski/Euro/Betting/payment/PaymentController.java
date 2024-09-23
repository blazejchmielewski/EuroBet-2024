package pl.chmielewski.Euro.Betting.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.Euro.exception.PaymentNotFoundException;
import pl.chmielewski.Euro.request.CreatePayment;
import pl.chmielewski.Euro.response.ResourceResponse;

import java.util.List;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = {"https://euro2024-e0934.web.app", "http://localhost:4200"}, allowCredentials = "true")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getUsersPayments(){
        return new ResponseEntity<>(paymentService.usersPayments(), HttpStatus.OK);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<UserStatus> getUsersPayments(@PathVariable("id") Long userId){
        return new ResponseEntity<>(paymentService.getUserPaymentStatus(userId), HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<PaymentHistory>> getPaymentHistories(){
        return new ResponseEntity<>(paymentService.getPaymentHistories().orElseThrow(PaymentNotFoundException::new), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ResourceResponse> addPayment(@RequestBody CreatePayment createPayment){
        paymentService.addPayment(createPayment);
        return new ResponseEntity<>(new ResourceResponse("Dodano płatność"), HttpStatus.CREATED);
    }
}
