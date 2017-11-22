package ozog.dao;

import ozog.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void deleteUser(long userId) {
        sessionFactory.getCurrentSession().delete(getUserById(userId));
    }

    public User getUserById(long userId) {
        return (User) sessionFactory.getCurrentSession().get(User.class, userId);
    }

    public List<User> getAllUsers() {
        return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }
}
