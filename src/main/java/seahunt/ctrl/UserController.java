package seahunt.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seahunt.entity.User;
import seahunt.repository.UserRepository;

@RestController
public class UserController {

    @Autowired private UserRepository userRepository;

    @RequestMapping(value = "/users")
    public User getUser(@RequestParam String name) {
        return userRepository.findByName(name).get(0);
    }

    @RequestMapping(value = "/users/create")
    public Long createUser(@RequestParam String name) {

        User user = new User();
        user.setName(name);

        return userRepository.save(user).getId();
    }
}
