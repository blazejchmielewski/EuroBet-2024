package pl.chmielewski.Euro.Authentication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chmielewski.Euro.exception.UserByIdNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserByIdNotFoundException(id));
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }



    public User updateUser(Long id, User user){
        user.setId(findUserById(id).getId());
        return userRepository.save(user);
    }

    public String deleteUser(long id){
        userRepository.delete(findUserById(id));
        return "User has been deleted with id: " + id;
    }

    public String expireUser(Long id){
        User userById = findUserById(id);
        userById.setEnabled(false);
        userRepository.save(userById);
        return "User has been expired with id: " + id;
    }

}
