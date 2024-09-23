package pl.chmielewski.Euro.Betting;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.chmielewski.Euro.Authentication.user.*;
import pl.chmielewski.Euro.Betting.country.Country;
import pl.chmielewski.Euro.Betting.country.CountryRepo;
import pl.chmielewski.Euro.Betting.match.Match;
import pl.chmielewski.Euro.Betting.match.MatchRepo;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Component
public class Init {

    private final CountryRepo countryRepo;
    private final MatchRepo matchRepo;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Init(CountryRepo countryRepo, MatchRepo matchRepo, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.countryRepo = countryRepo;
        this.matchRepo = matchRepo;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initAll() {
        initUsers();
        initCountires();
        initMatches();
    }


    private void initUsers() {

        if(userRepository.findAll().isEmpty()){
            Role role = Role.ADMIN;
            User user = new User(
                    UUID.randomUUID().toString(),
                    "Blazej",
                    "Chmielewski",
                    "blazejchmielewski01@gmail.com",
                    passwordEncoder.encode("haslo123!"),
                    "BI",
                    role,
                    true
            );
            userRepository.save(user);
        }
    }
    
    private void initCountires() {
        List<Country> countryList = new LinkedList<>();
        if(countryRepo.findAll().isEmpty()) {
            countryList.add(new Country("Niemcy"));
            countryList.add(new Country("Hiszpania"));
            countryList.add(new Country("Szkocja"));
            countryList.add(new Country("Francja"));
            countryList.add(new Country("Holandia"));
            countryList.add(new Country("Anglia"));
            countryList.add(new Country("Włochy"));
            countryList.add(new Country("Turcja"));
            countryList.add(new Country("Chorwacja"));
            countryList.add(new Country("Albania"));
            countryList.add(new Country("Czechy"));
            countryList.add(new Country("Belgia"));
            countryList.add(new Country("Austria"));
            countryList.add(new Country("Węgry"));
            countryList.add(new Country("Serbia"));
            countryList.add(new Country("Dania"));
            countryList.add(new Country("Słowenia"));
            countryList.add(new Country("Rumunia"));
            countryList.add(new Country("Szwajcaria"));
            countryList.add(new Country("Portugalia"));
            countryList.add(new Country("Słowacja"));
            countryList.add(new Country("Polska"));
            countryList.add(new Country("Ukraina"));
            countryList.add(new Country("Gruzja"));
            countryRepo.saveAll(countryList);
        }

    }

    private void initMatches() {
        List<Match> matchesList = new LinkedList<>();

        if(matchRepo.findById(1L).isEmpty()){
            matchesList.add(new Match(null, null, 1L, 3L, LocalDateTime.of(2024, 10, 14, 21, 0))); // Niemcy - Szkocja

            // sobota, 15 czerwca 2024
            matchesList.add(new Match(null, null, 14L, 19L, LocalDateTime.of(2024, 10, 15, 15, 0))); // Węgry - Szwajcaria
            matchesList.add(new Match(null, null, 2L, 9L, LocalDateTime.of(2024, 10, 15, 18, 0))); // Hiszpania - Chorwacja
            matchesList.add(new Match(null, null, 7L, 10L, LocalDateTime.of(2024, 10, 15, 21, 0))); // Włochy - Albania

            // niedziela, 16 czerwca 2024
            matchesList.add(new Match(null, null, 22L, 5L, LocalDateTime.of(2024, 10, 16, 15, 0))); // Polska - Holandia
            matchesList.add(new Match(null, null, 17L, 16L, LocalDateTime.of(2024, 10, 16, 18, 0))); // Słowenia - Dania
            matchesList.add(new Match(null, null, 15L, 6L, LocalDateTime.of(2024, 10, 16, 21, 0))); // Serbia - Anglia

            // poniedziałek, 17 czerwca 2024
            matchesList.add(new Match(null, null, 18L, 23L, LocalDateTime.of(2024, 10, 17, 15, 0))); // Rumunia - Ukraina
            matchesList.add(new Match(null, null, 13L, 12L, LocalDateTime.of(2024, 10, 17, 18, 0))); // Belgia - Słowacja
            matchesList.add(new Match(null, null, 8L, 4L, LocalDateTime.of(2024, 10, 17, 21, 0))); // Austria - Francja

            // wtorek, 18 czerwca 2024
            matchesList.add(new Match(null, null, 20L, 24L, LocalDateTime.of(2024, 10, 18, 18, 0))); // Turcja - Gruzja
            matchesList.add(new Match(null, null, 11L, 21L, LocalDateTime.of(2024, 10, 18, 21, 0))); // Portugalia - Czechy

            // środa, 19 czerwca 2024
            matchesList.add(new Match(null, null, 9L, 10L, LocalDateTime.of(2024, 10, 19, 15, 0))); // Chorwacja - Albania
            matchesList.add(new Match(null, null, 1L, 14L, LocalDateTime.of(2024, 10, 19, 18, 0))); // Niemcy - Węgry
            matchesList.add(new Match(null, null, 3L, 19L, LocalDateTime.of(2024, 10, 19, 21, 0))); // Szkocja - Szwajcaria

            // czwartek, 20 czerwca 2024
            matchesList.add(new Match(null, null, 16L, 15L, LocalDateTime.of(2024, 10, 20, 15, 0))); // Słowenia - Serbia
            matchesList.add(new Match(null, null, 17L, 6L, LocalDateTime.of(2024, 10, 20, 18, 0))); // Dania - Anglia
            matchesList.add(new Match(null, null, 2L, 7L, LocalDateTime.of(2024, 10, 20, 21, 0))); // Hiszpania - Włochy

            matchesList.add(new Match(null, null, 22L, 13L, LocalDateTime.of(2024, 10, 21, 18, 0))); // Polska - Austria
            matchesList.add(new Match(null, null, 5L, 4L, LocalDateTime.of(2024, 10, 21, 21, 0))); // Holandia - Francja

            // sobota, 22 czerwca 2024
            matchesList.add(new Match(null, null, 24L, 11L, LocalDateTime.of(2024, 10, 22, 15, 0))); // Gruzja - Czechy
            matchesList.add(new Match(null, null, 8L, 20L, LocalDateTime.of(2024, 10, 22, 18, 0))); // Turcja - Portugalia
            matchesList.add(new Match(null, null, 12L, 18L, LocalDateTime.of(2024, 10, 22, 21, 0))); // Belgia - Rumunia

            // niedziela, 23 czerwca 2024
            matchesList.add(new Match(null, null, 3L, 14L, LocalDateTime.of(2024, 10, 23, 21, 0))); // Szkocja - Węgry
            matchesList.add(new Match(null, null, 19L, 1L, LocalDateTime.of(2024, 10, 23, 21, 0))); // Szwajcaria - Niemcy

            // poniedziałek, 24 czerwca 2024
            matchesList.add(new Match(null, null, 10L, 2L, LocalDateTime.of(2024, 10, 24, 21, 0))); // Albania - Hiszpania
            matchesList.add(new Match(null, null, 9L, 7L, LocalDateTime.of(2024, 10, 24, 21, 0))); // Chorwacja - Włochy

            // wtorek, 25 czerwca 2024
            matchesList.add(new Match(null, null, 4L, 22L, LocalDateTime.of(2024, 10, 25, 18, 0))); // Francja - Polska
            matchesList.add(new Match(null, null, 5L, 13L, LocalDateTime.of(2024, 10, 25, 18, 0))); // Holandia - Austria
            matchesList.add(new Match(null, null, 6L, 17L, LocalDateTime.of(2024, 10, 25, 21, 0))); // Anglia - Słowenia
            matchesList.add(new Match(null, null, 16L, 15L, LocalDateTime.of(2024, 10, 25, 21, 0))); // Dania - Serbia

            // środa, 26 czerwca 2024
            matchesList.add(new Match(null, null, 21L, 18L, LocalDateTime.of(2024, 10, 26, 18, 0))); // Słowacja - Rumunia
            matchesList.add(new Match(null, null, 23L, 12L, LocalDateTime.of(2024, 10, 26, 18, 0))); // Ukraina - Belgia
            matchesList.add(new Match(null, null, 11L, 20L, LocalDateTime.of(2024, 10, 26, 21, 0))); // Czechy - Turcja
            matchesList.add(new Match(null, null, 24L, 8L, LocalDateTime.of(2024, 10, 26, 21, 0))); // Gruzja - Portugalia

            matchRepo.saveAll(matchesList);
        }
    }
}
