package pl.chmielewski.Euro.Betting.bet;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepo extends JpaRepository<Bet, Long> {

    @Query(value = "SELECT u.firstname, u.lastname, SUM(b.points) AS total_points, " +
            "DENSE_RANK() OVER (ORDER BY SUM(b.points) DESC) AS rank " +
            "FROM euro.bets b " +
            "JOIN euro.users u ON b.user_id = u.user_id " +
            "GROUP BY u.firstname, u.lastname " +
            "ORDER BY total_points DESC",
            nativeQuery = true)
    List<Object[]> getUserRankingRaw();


    @Modifying
    @Transactional
    @Query(value = """
            UPDATE euro.bets b
                                           SET b.points =
                                               CASE
                                                   WHEN CONCAT(b.first_team_score, '-', b.second_team_score) =\s
                                                       (SELECT m.result FROM euro.matches m WHERE m.id = b.match_id) THEN 4
                                                   WHEN (b.first_team_score > b.second_team_score) AND\s
                                                        (SELECT m.first_team_score FROM euro.matches m WHERE m.id = b.match_id) > b.second_team_score THEN 1
                                                   WHEN (b.first_team_score < b.second_team_score) AND\s
                                                        (SELECT m.first_team_score FROM euro.matches m WHERE m.id = b.match_id) < b.second_team_score THEN 1
                                                   WHEN (b.first_team_score = b.second_team_score) AND\s
                                                        (SELECT m.first_team_score FROM euro.matches m WHERE m.id = b.match_id) = (SELECT m.second_team_score FROM euro.matches m WHERE m.id = b.match_id) THEN 1
                                                   ELSE 0
                                               END
            """, nativeQuery = true)
    void updatePointsForBets();

    @Query(value = "SELECT CASE WHEN EXISTS (" +
            "SELECT 1 FROM euro.bets b " +
            "WHERE b.match_id = :matchId AND b.user_id = :userId" +
            ") THEN 1 ELSE 0 END", nativeQuery = true)
    Integer existsByMatchIdAndUserId(@Param("matchId") Long matchId, @Param("userId") Long userId);

    @Query(value = "SELECT * FROM euro.bets b " +
            "WHERE b.match_id = :matchId AND b.user_id = :userId", nativeQuery = true)
    Bet findByMatchIdAndUserId(@Param("matchId") Long matchId, @Param("userId") Long userId);
}

