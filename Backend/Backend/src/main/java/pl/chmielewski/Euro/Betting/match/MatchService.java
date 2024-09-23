package pl.chmielewski.Euro.Betting.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.Euro.Betting.bet.BetRepo;
import pl.chmielewski.Euro.request.UpdateMatchResult;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepo matchRepo;
    private final BetRepo betRepo;

    @Autowired
    public MatchService(MatchRepo matchRepo, BetRepo betRepo) {
        this.matchRepo = matchRepo;
        this.betRepo = betRepo;
    }

    List<MatchDetails> getAllMatches(Long id) {
        List<Object[]> rawData = matchRepo.getMatchDetails(id);
        return MatchDetails.fromRawDataType(rawData);
    }

    List<MatchDetails> getAllMatchesToAddResult() {
        List<Object[]> rawData = matchRepo.getMatchDetailsToAddResult();
        return MatchDetails.fromRawData(rawData);
    }


    public void updateMatchResult(Long id, UpdateMatchResult updateMatchResult) {
        Optional<Match> optionalMatch = matchRepo.findById(id);
        Long winner = 0L;

        if (optionalMatch.isPresent()) {
            Match match = optionalMatch.get();

            if (updateMatchResult.firstTeamScore() > updateMatchResult.secondTeamScore()) {
                winner = match.getFirstTeam();
            } else if (updateMatchResult.firstTeamScore() < updateMatchResult.secondTeamScore()) {
                winner = match.getSecondTeam();
            }

            match.setFirstTeamScore(updateMatchResult.firstTeamScore());
            match.setSecondTeamScore(updateMatchResult.secondTeamScore());
            match.setResult(updateMatchResult.firstTeamScore() + "-" + updateMatchResult.secondTeamScore());
            match.setWinner(winner);
            betRepo.updatePointsForBets();
            matchRepo.save(match);
        }
    }
}
