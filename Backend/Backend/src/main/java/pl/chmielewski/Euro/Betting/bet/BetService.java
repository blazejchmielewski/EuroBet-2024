package pl.chmielewski.Euro.Betting.bet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.Euro.Authentication.user.User;
import pl.chmielewski.Euro.Authentication.user.UserRepository;
import pl.chmielewski.Euro.Betting.match.Match;
import pl.chmielewski.Euro.Betting.match.MatchDetails;
import pl.chmielewski.Euro.Betting.match.MatchRepo;
import pl.chmielewski.Euro.exception.UserByEmailNotFoundException;
import pl.chmielewski.Euro.request.CreateBet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetService {

    private final BetRepo betRepo;
    private final UserRepository userRepository;
    private final MatchRepo matchRepo;

    @Autowired
    public BetService(BetRepo betRepo, UserRepository userRepository, MatchRepo matchRepo) {
        this.betRepo = betRepo;
        this.userRepository = userRepository;
        this.matchRepo = matchRepo;
    }

    public void addNewBet(Long userId, CreateBet betRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Match match = matchRepo.findById(betRequest.matchId()).orElseThrow(() -> new IllegalArgumentException("Match not found: " + betRequest.matchId()));
        boolean b = betRepo.existsByMatchIdAndUserId(betRequest.matchId(), user.getId()) == 1;
        if (b) {
            Bet byMatchIdAndUserId = betRepo.findByMatchIdAndUserId(betRequest.matchId(), user.getId());
            byMatchIdAndUserId.setFirstTeamScore(betRequest.firstTeamScore());
            byMatchIdAndUserId.setSecondTeamScore(betRequest.secondTeamScore());
            betRepo.save(byMatchIdAndUserId);
        } else {
            Bet bet = new Bet(user, match,
                    betRequest.firstTeamScore(),
                    betRequest.secondTeamScore(),
                    LocalDateTime.now());
            betRepo.save(bet);
        }
    }

    public List<MatchDetails> getMatchesToBet(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserByEmailNotFoundException(email));
        List<Object[]> rawData = matchRepo.getMatchesNotBetByUser(user.getId());
        return MatchDetails.fromRawData(rawData);
    }

    public String updateBetsPoints() {
        betRepo.updatePointsForBets();
        return "Zaktualizowano statusy meczy";
    }

    public List<UserRanking> getUserRankings() {
        List<Object[]> rawResults = betRepo.getUserRankingRaw();
        System.out.println(rawResults.toString());
        return rawResults.stream()
                .map(result -> new UserRanking(
                        (String) result[0],
                        (String) result[1],
                        ((Number) result[2]).intValue(),
                        ((Number) result[3]).intValue()))
                .collect(Collectors.toList());
    }
}

