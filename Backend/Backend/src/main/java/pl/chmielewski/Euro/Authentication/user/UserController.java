package pl.chmielewski.Euro.Authentication.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chmielewski.Euro.mapper.CreateUserMapper;
import pl.chmielewski.Euro.mapper.EditUserMapper;
import pl.chmielewski.Euro.request.EditUserRequest;
import pl.chmielewski.Euro.request.RegisterRequest;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"https://euro2024-e0934.web.app", "http://localhost:4200"}, allowCredentials = "true")
public class UserController {

    private final UserService userService;
    private final CreateUserMapper registerMapper;
    private final EditUserMapper editMapper;

    public UserController(UserService userService, CreateUserMapper registerMapper, EditUserMapper editMapper) {
        this.userService = userService;
        this.registerMapper = registerMapper;
        this.editMapper = editMapper;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<User> createNewUser(@RequestBody RegisterRequest userToAdd) {
        return new ResponseEntity<>(registerMapper.apply(userToAdd), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody EditUserRequest userToEdit) {
        User userFoundById = userService.findUserById(id);
        User updatedUser = editMapper.apply(userFoundById, userToEdit);
        return new ResponseEntity<>(userService.saveUser(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/expire/{id}")
    public String expireUser(@PathVariable("id") Long id){
        return userService.expireUser(id);
    }
}
