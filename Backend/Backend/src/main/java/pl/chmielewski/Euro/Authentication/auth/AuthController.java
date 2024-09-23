package pl.chmielewski.Euro.Authentication.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.Euro.request.LoginRequest;
import pl.chmielewski.Euro.request.RegisterRequest;
import pl.chmielewski.Euro.response.LoginResponse;
import pl.chmielewski.Euro.response.RegisterResponse;
import pl.chmielewski.Euro.Authentication.user.Department;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) throws MessagingException {
        return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.CREATED);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments(){
        return List.of(Department.values());
    }

}
