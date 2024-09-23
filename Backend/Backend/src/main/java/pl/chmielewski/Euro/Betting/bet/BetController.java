package pl.chmielewski.Euro.Betting.bet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.Euro.Authentication.user.User;
import pl.chmielewski.Euro.Authentication.user.UserService;
import pl.chmielewski.Euro.Betting.match.MatchDetails;
import pl.chmielewski.Euro.exception.UserByEmailNotFoundException;
import pl.chmielewski.Euro.request.AddGoalsKing;
import pl.chmielewski.Euro.request.CreateBet;
import pl.chmielewski.Euro.response.GoalKingResponse;
import pl.chmielewski.Euro.response.ResourceResponse;

import java.util.List;

@RestController
@RequestMapping("/bet")
@CrossOrigin(origins = {"https://euro2024-e0934.web.app", "http://localhost:4200"}, allowCredentials = "true")
public class BetController {

    private final BetService betService;
    private final UserService userService;
    private final GoalsKingService goalsKingService;

    @Autowired
    public BetController(BetService betService, UserService userService, GoalsKingService goalsKingService) {
        this.betService = betService;
        this.userService = userService;
        this.goalsKingService = goalsKingService;
    }

    @PostMapping("/add/{email}")
    public ResponseEntity<ResourceResponse> addBet(@PathVariable("email") String email, @RequestBody CreateBet createBet) {
        User user = userService.findUserByEmail(email).orElseThrow(() -> new UserByEmailNotFoundException(email));

        betService.addNewBet(user.getId(), createBet);
        betService.updateBetsPoints();
        return new ResponseEntity<>(new ResourceResponse("Dodano bramki"), HttpStatus.CREATED);
    }

    @GetMapping("/matches-to-bet/{email}")
    public List<MatchDetails> getAllMatchesToBet(@PathVariable("email") String email) {
        return betService.getMatchesToBet(email);
    }

    @GetMapping("/get-user-king/{email}")
    public ResponseEntity<GoalKingResponse> getUserGoalKing(@PathVariable("email") String email){
        return new ResponseEntity<>(goalsKingService.getUserGoalKing(email), HttpStatus.OK);
    }

    @GetMapping("/update-match-status")
    public ResponseEntity<ResourceResponse> updateMatchStatus() {
        return new ResponseEntity<>(new ResourceResponse(betService.updateBetsPoints()), HttpStatus.OK);
    }

    @PostMapping("/add/king/{email}")
    public void addGoalsKing(@PathVariable("email") String email, @RequestBody AddGoalsKing addGoalsKing){
        goalsKingService.addGoalsKing(email, addGoalsKing);
    }

    @GetMapping("/ranking")
    public List<UserRanking> getUsersRanking() {
        return betService.getUserRankings();
    }
}
