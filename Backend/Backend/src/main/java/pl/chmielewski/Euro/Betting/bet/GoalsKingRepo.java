package pl.chmielewski.Euro.Betting.bet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalsKingRepo extends JpaRepository<GoalsKing, Long>{

    @Query(value = """
            select top(1) name, goals
            from euro.king_goals
            where user_id = :userId
            order by id
            """, nativeQuery = true)
    List<Object[]> findUserGoalsKing(@Param("userId") Long userId);
}
