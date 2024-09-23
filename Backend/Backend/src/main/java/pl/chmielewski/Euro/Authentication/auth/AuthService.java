package pl.chmielewski.Euro.Authentication.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.chmielewski.Euro.exception.SameUserEmailException;
import pl.chmielewski.Euro.exception.UserByEmailNotFoundException;
import pl.chmielewski.Euro.mapper.CreateUserMapper;
import pl.chmielewski.Euro.request.LoginRequest;
import pl.chmielewski.Euro.request.RegisterRequest;
import pl.chmielewski.Euro.response.LoginResponse;
import pl.chmielewski.Euro.response.RegisterResponse;
import pl.chmielewski.Euro.Authentication.token.Token;
import pl.chmielewski.Euro.Authentication.token.TokenService;
import pl.chmielewski.Euro.Authentication.user.User;
import pl.chmielewski.Euro.Authentication.user.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final CreateUserMapper createUserMapper;
    private final TokenService tokenService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService, CreateUserMapper createUserMapper, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.createUserMapper = createUserMapper;
        this.tokenService = tokenService;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()));
        User user = userService.findUserByEmail(loginRequest.email())
                .orElseThrow(() -> new UserByEmailNotFoundException(loginRequest.email()));
        revokeAllUsersToken(user);
        String token = jwtService.generateToken(user);
        tokenService.saveUserToken(token, user);
        return new LoginResponse(token);
    }


    public RegisterResponse register(RegisterRequest registerRequest) {
        if (!userService.existsByEmail(registerRequest.email())) {
            User user = userService.saveUser(createUserMapper.apply(registerRequest));
            String token = jwtService.generateToken(user);
            tokenService.saveUserToken(token, user);
            return new RegisterResponse(token);
        } else throw new SameUserEmailException();
    }

    private void revokeAllUsersToken(User user) {
        Optional<List<String>> allValidUserTokens = tokenService.allValidUserTokens(user);
        if(allValidUserTokens.isPresent()){
            List<Optional<Token>> allByToken = tokenService.findAllByToken(allValidUserTokens.orElse(null));

            List<Token> tokensToUpdate = allByToken.stream()
                    .map(Optional::orElseThrow)
                    .peek(token -> {
                        token.setRevoked(true);
                        token.setExpired(true);
                    })
                    .collect(Collectors.toList());

            tokenService.saveAll(tokensToUpdate);
        }
    }
}
