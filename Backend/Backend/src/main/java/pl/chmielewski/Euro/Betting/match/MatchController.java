package pl.chmielewski.Euro.Betting.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.Euro.Authentication.user.User;
import pl.chmielewski.Euro.Authentication.user.UserRepository;
import pl.chmielewski.Euro.Betting.country.Country;
import pl.chmielewski.Euro.Betting.country.CountryRepo;
import pl.chmielewski.Euro.exception.UserByEmailNotFoundException;
import pl.chmielewski.Euro.request.UpdateMatchResult;

import java.util.List;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins = {"https://euro2024-e0934.web.app", "http://localhost:4200"}, allowCredentials = "true")
public class MatchController {

    private final MatchService matchService;
    private final CountryRepo countryRepo;
    private final UserRepository userRepository;

    @Autowired
    public MatchController(MatchService matchService, CountryRepo countryRepo, UserRepository userRepository) {
        this.matchService = matchService;
        this.countryRepo = countryRepo;
        this.userRepository = userRepository;
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return countryRepo.findAll();
    }

    @GetMapping("/matches/{email}")
    public List<MatchDetails> getAllMatches(@PathVariable("email") String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserByEmailNotFoundException(email));
        return matchService.getAllMatches(user.getId());
    }

    @GetMapping("/matches-to-add")
    public List<MatchDetails> getAllMatchesWithResultToAdd() {
        return matchService.getAllMatchesToAddResult();
    }


    @PutMapping("/update/match/{id}")
    public void updateMatchResult(@PathVariable("id") Long id, @RequestBody UpdateMatchResult updateMatchResult) {
        matchService.updateMatchResult(id, updateMatchResult);
    }
}
