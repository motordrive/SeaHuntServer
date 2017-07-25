package seahunt.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seahunt.entity.User;
import seahunt.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired private UserRepository userRepository;

    @PostConstruct
    public void setup() {
        userRepository.deleteAll();
    }

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
    public Long createUser(@RequestParam String name, @RequestParam String password) {
//        for (User user: userRepository.findAll()) {
//            System.out.println(user.getName());
//        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        long thing = userRepository.save(user).getId();

//        for (User user1: userRepository.findAll()) {
//            System.out.println(user1.getName());
//        }

        return thing;
    }

    @RequestMapping(value = "/findUser")
    public Boolean findUser(@RequestParam String name)
    {
        List<User> users = userRepository.findByName(name);
        return users.size() > 0;
//
//        for(int i = 0; i < ((List<User>) userRepository.findByName()).size(); i++)
//        {
//            if( (((List<User>) userRepository.findAll()).get(i).getName()).equals(name))
//            {
//                return true;
//            }
//        }
//        return false;
    }

    @RequestMapping(value = "/checkLogin")
    public Boolean checkLogin (@RequestParam String name, @RequestParam String password)
    {
        List<User> users = userRepository.findByName(name);
        if (users.size() > 0)
        {
            if ((users.get(0).getPassword()).equals(password) )
            {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/getProgress")
    public String getProgress (@RequestParam String name)
    {
        List<User> users = userRepository.findByName(name);
        if (users.size() > 0)
        {
            System.out.println("get progress" + users.get(0).getProgress());
            return users.get(0).getProgress();

        }
        return "";
    }

    @RequestMapping(value = "/setProgress")
    public void setProgress (@RequestParam String name, @RequestParam int location, @RequestParam int riddle)
    {
        List<User> users = userRepository.findByName(name);
        if (users.size() > 0)
        {
            users.get(0).setProgress(location, riddle);
            userRepository.save(users.get(0));
            System.out.println("set progress" + users.get(0).getProgress());
        }
    }
}
