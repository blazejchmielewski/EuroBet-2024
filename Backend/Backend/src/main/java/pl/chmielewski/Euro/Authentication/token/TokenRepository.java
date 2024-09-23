package pl.chmielewski.Euro.Authentication.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
            select t.token
            from euro.tokens t
            join euro.users u on t.tk_us_id = u.user_id
            where u.user_id = :id
            and (t.expired = false or t.revoked = false)
            """, nativeQuery = true)
    Optional<List<String>> findAllValidTokenByUser(Long id);

    Optional<Token> findByToken(String token);

}
