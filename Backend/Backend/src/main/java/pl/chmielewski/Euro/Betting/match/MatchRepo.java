package pl.chmielewski.Euro.Betting.match;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepo extends CrudRepository<Match, Long> {

    @Query(value = """
            WITH CTE AS (
                SELECT m.id AS id,
                       c1.id AS first_team_id,
                       c1.name AS first_team_name,
                       c2.id AS second_team_id,
                       c2.name AS second_team_name,
                       m.play_date_time AS data_meczu,
                       m.result AS rezultat
                FROM euro.matches m
                JOIN euro.countries c1 ON c1.id = m.first_team
                JOIN euro.countries c2 ON c2.id = m.second_team
            )
            SELECT CTE.id,
                   CTE.first_team_id,
                   CTE.first_team_name,
                   CTE.second_team_id,
                   CTE.second_team_name,
                   CTE.data_meczu,
                   CTE.rezultat,
                   CONCAT(b.first_team_score, '-', b.second_team_score) AS typowanie
            FROM CTE
            LEFT JOIN euro.bets b ON CTE.id = b.match_id AND b.user_id = :userId
            ORDER BY CTE.data_meczu
            """, nativeQuery = true)
    List<Object[]> getMatchDetails(@Param("userId") Long userId);


    @Query(value = "SELECT m.id, c1.id AS first_team_id, c1.name AS first_team_name, " +
            "c2.id AS second_team_id, c2.name AS second_team_name, " +
            "m.play_date_time, m.result " +
            "FROM euro.matches m " +
            "JOIN euro.countries c1 ON c1.id = m.first_team " +
            "JOIN euro.countries c2 ON c2.id = m.second_team " +
            "where m.play_date_time < NOW() and m.result is null Order by m.play_date_time",
            nativeQuery = true)
    List<Object[]> getMatchDetailsToAddResult();

    @Query(value = "SELECT m.id, c1.id AS first_team_id, c1.name AS first_team_name, " +
            "c2.id AS second_team_id, c2.name AS second_team_name, " +
            "m.play_date_time, m.result " +
            "FROM euro.matches m " +
            "JOIN euro.countries c1 ON c1.id = m.first_team " +
            "JOIN euro.countries c2 ON c2.id = m.second_team " +
            "WHERE m.play_date_time > NOW() Order by m.play_date_time",
            nativeQuery = true)
    List<Object[]> getMatchesNotBetByUser(@Param("userId") Long userId);

    Optional<Match> findById(Long id);
}
