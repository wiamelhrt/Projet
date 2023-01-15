package Controller;

import Entity.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MainController {
    @Autowired
    UserService userService;

    @PostMapping("/getUser")
    public User getUser(@RequestBody User user) {
        return userService.getUser(user);
    }

    @PutMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.insertIntoDatabase(user);
    }
}
