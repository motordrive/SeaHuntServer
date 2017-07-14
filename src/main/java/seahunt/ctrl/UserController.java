package seahunt.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seahunt.entity.User;
import seahunt.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired private UserRepository userRepository;

    @RequestMapping(value = "/hello")
    public String hello() {
        System.out.println("You have hit this endpoint!");
        return "Hello Hello!";
    }

    @RequestMapping(value = "/getAllUsers")
    public List<User> getAllUsers() {

        System.out.println(userRepository.findAll());
        return (List<User>) userRepository.findAll();
    }

    @RequestMapping(value = "/users")
    public User getUser(@RequestParam String name) {
        return userRepository.findByName(name).get(0);
    }

    @RequestMapping(value = "/users/create")
    public Long createUser(@RequestParam String name, String password) {

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userRepository.save(user).getId();
    }
}
