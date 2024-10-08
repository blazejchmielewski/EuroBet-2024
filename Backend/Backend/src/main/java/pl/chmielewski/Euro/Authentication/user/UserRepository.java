package pl.chmielewski.Euro.Authentication.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    User findUserById(Long id);
    boolean existsByEmail(String email);
}
