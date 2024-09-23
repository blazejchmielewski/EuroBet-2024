package pl.chmielewski.Euro.mapper;

import org.springframework.stereotype.Component;
import pl.chmielewski.Euro.Authentication.user.UserRepository;
import pl.chmielewski.Euro.request.EditUserRequest;
import pl.chmielewski.Euro.Authentication.user.User;

import java.util.function.BiFunction;

@Component
public class EditUserMapper implements BiFunction<User, EditUserRequest, User> {

    @Override
    public User apply(User existingUser, EditUserRequest editUserRequest) {
        existingUser.setLastname(editUserRequest.lastname());
        existingUser.setFirstname(editUserRequest.firstname());
        existingUser.setEmail(editUserRequest.email());
        existingUser.setDepartment(editUserRequest.department());
        return existingUser;
    }
}
