package pl.chmielewski.Euro.Authentication.auth;

import org.springframework.web.bind.annotation.*;
import pl.chmielewski.Euro.Authentication.token.Token;
import pl.chmielewski.Euro.Authentication.token.TokenRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"https://euro2024-e0934.web.app", "https://localhost:4200"}, allowCredentials = "true")
public class TestController {

    private final TokenRepository tokenRepository;

    public TestController(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/admin")
    public String forAdmin(){
        return "admin";
    }

    @GetMapping("/user")
    public String forUser(){
        return "user";
    }

    @GetMapping("/guest")
    public String forGuest(){
        return "guest";
    }

    @GetMapping("/token/{id}")
    public Optional<List<String>> tokenList(@PathVariable("id") Long id){
        return tokenRepository.findAllValidTokenByUser(id);
    }

    @GetMapping("/tokens")
    public List<String> getAllTokens(){
        return tokenRepository.findAll().stream().map(Token::getToken).collect(Collectors.toList());
    }
}
