package pl.chmielewski.Euro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.chmielewski.Euro.Authentication.user.*;
import pl.chmielewski.Euro.config.EmailSenderService;
import pl.chmielewski.Euro.request.RegisterRequest;

import javax.mail.MessagingException;
import java.util.*;
import java.util.function.Function;

@Component
public class CreateUserMapper implements Function<RegisterRequest, User> {

    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;

    @Autowired
    public CreateUserMapper(BCryptPasswordEncoder passwordEncoder, EmailSenderService emailSenderService, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
    }

    @Override
    public User apply(RegisterRequest newUser) {
        String generateRandomNumericPassword = generateRandomNumericPassword(6);
        generateEmail(generateRandomNumericPassword, newUser.email());
        Role role = Role.USER;

        // Tworzenie nowego użytkownika
        User user = new User(
                UUID.randomUUID().toString(),       // UUID
                newUser.firstname(),                 // Imię
                newUser.lastname(),                  // Nazwisko
                newUser.email(),                     // E-mail
                passwordEncoder.encode(generateRandomNumericPassword), // Hasło (zakodowane)
                newUser.department().name(),         // Dział
                role,                                // Rola
                true                                 // Czy konto jest aktywne
        );

        userRepository.save(user);
        return user;
    }

    private String generateRandomNumericPassword(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

    private void generateEmail(String generateRandomNumericPassword, String email){
        System.out.println(generateRandomNumericPassword);
        String emailContent = "<html lang=\"pl\">" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Registration Confirmation</title>\n" +
                "</head>" +
                "<body>" +
                "<p>Witaj!</p>" +
                "<p>Zapraszamy Cię na Euro 2024.</p>" +
                "<p>Twoje dane logowania:</p>" +
                "<p>Login: " + email + "</p>" +
                "<p>Hasło: " + generateRandomNumericPassword + "</p>" +
                "<p>Link do logowania: https://localhost:8080/</p>" +
                "<p>Życzymy udanej imprezy!</p>" +
                "</body></html>";

        try {
            emailSenderService.sendMail(email, "Euro 2024", emailContent, true);
        } catch (MessagingException | jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
