package pl.chmielewski.Euro.Betting.bet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.Euro.Authentication.user.User;
import pl.chmielewski.Euro.Authentication.user.UserRepository;
import pl.chmielewski.Euro.exception.UserByEmailNotFoundException;
import pl.chmielewski.Euro.request.AddGoalsKing;
import pl.chmielewski.Euro.response.GoalKingResponse;

import java.util.List;

@Service
public class GoalsKingService {

    private final GoalsKingRepo goalsKingRepo;
    private final UserRepository userRepository;

    @Autowired
    public GoalsKingService(GoalsKingRepo goalsKingRepo, UserRepository userRepository) {
        this.goalsKingRepo = goalsKingRepo;
        this.userRepository = userRepository;
    }

    public void addGoalsKing(String email, AddGoalsKing newGoalsKing) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserByEmailNotFoundException(email));
        GoalsKing goalsKing = new GoalsKing(newGoalsKing.name(), newGoalsKing.goals(), user.getId());
        goalsKingRepo.save(goalsKing);
    }

    public GoalKingResponse getUserGoalKing(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserByEmailNotFoundException(email));
        List<Object[]> userGoalsKing = goalsKingRepo.findUserGoalsKing(user.getId());
        List<GoalsKingUser> goalsKingUsers = GoalsKingUser.fromRawDataType(userGoalsKing);

        if (!goalsKingUsers.isEmpty()) {
            GoalsKingUser firstUser = goalsKingUsers.get(0);
            return new GoalKingResponse(firstUser.getName(), firstUser.getGoals());
        } else {
            return null;
        }
    }
}

