package Service;

import Entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    public User getUser(User user);

    public User insertIntoDatabase(User user);
}
