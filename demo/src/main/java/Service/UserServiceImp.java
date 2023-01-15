package Service;

import Dao.UserDao;
import Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final EntityManager entityManager;

    @Autowired
    public UserServiceImp(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Autowired
    private UserDao userDao;


    @Override
    public User getUser(User userDetails) {
        TypedQuery<User> typedQuery = entityManager.createQuery(
                "Select u FROM User u WHERE u.password = :password AND u.username = :username", User.class);
        try {
            User user = typedQuery.setParameter("password", userDetails.getPassword()).setParameter("username", userDetails.getUsername()).getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User insertIntoDatabase(User user) {
        return userDao.save(user);
    }
}
