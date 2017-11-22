package ozog.service;

import ozog.dao.UserDao;
import ozog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(long userId) {
        userDao.deleteUser(userId);
    }

    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
