package seahunt.ctrl;

import org.jasypt.util.password.StrongPasswordEncryptor;
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
    public Long createUser(@RequestParam String name, @RequestParam String password) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password);

        User user = new User();
        user.setName(name);
        user.setPassword(encryptedPassword);
        long userId = userRepository.save(user).getId();

        return userId;
    }

    @RequestMapping(value = "/findUser")
    public Boolean findUser(@RequestParam String name)
    {
        List<User> users = userRepository.findByName(name);
        return users.size() > 0;
    }

    @RequestMapping(value = "/checkLogin")
    public Boolean checkLogin (@RequestParam String name, @RequestParam String password)
    {
        List<User> users = userRepository.findByName(name);
        if (users.size() > 0)
        {
            StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
            if (passwordEncryptor.checkPassword(password, users.get(0).getPassword()))
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

    @RequestMapping(value = "/updateUser")
    public void updateUser (@RequestParam String name, @RequestParam String newPassword)
    {
        List<User> users = userRepository.findByName(name);
        if (users.size() > 0)
        {
            StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
            String encryptedPassword = passwordEncryptor.encryptPassword(newPassword);
            users.get(0).setPassword(encryptedPassword);
            userRepository.save(users.get(0));
            System.out.println("updateUser:" + users.get(0).getPassword());
        }
    }

    @RequestMapping(value= "/resetProgress")
    public void restProgress (@RequestParam String name)
    {
        List<User> users = userRepository.findByName(name);
        if (users.size() > 0)
        {
            users.get(0).resetProgress();
            userRepository.save(users.get(0));
            System.out.println("reset progress:" + users.get(0).getProgress());
        }
    }
}
