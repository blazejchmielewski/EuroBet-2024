package pl.chmielewski.Euro.Authentication.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.Euro.Authentication.user.User;
import pl.chmielewski.Euro.Authentication.user.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserService userService;

    @Autowired
    public TokenService(TokenRepository tokenRepository, UserService userService) {
        this.tokenRepository = tokenRepository;
        this.userService = userService;
    }

    public void saveUserToken(String jwtToken, User user) {
        Token token = new Token();
        token.setToken(jwtToken);
        token.setRevoked(false);
        token.setExpired(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public Optional<List<String>> allValidUserTokens(User user) {
        return Optional.ofNullable(tokenRepository.findAllValidTokenByUser(user.getId()).orElseThrow(() -> new RuntimeException("Nie ma aktywnych tokenów")));
    }

    public void saveAll(List<Token> tokenList) {
        tokenRepository.saveAll(tokenList);
    }

    public List<Optional<Token>> findAllByToken(List<String> tokens) {
        return tokens.stream().map(tokenRepository::findByToken).collect(Collectors.toList());
    }
}
