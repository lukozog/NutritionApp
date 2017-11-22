package ozog.dao;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;
import ozog.model.User;

public class UserDaoTest extends EntityDaoImplTest {

    @Autowired
    UserDao userDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet[] datasets = new IDataSet[]{
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml"))
        };
        return new CompositeDataSet(datasets);
    }

    @Test
    public void createUser() {
        userDao.createUser(getUser());
        System.out.println(userDao.getAllUsers().size());
        Assert.assertEquals(userDao.getAllUsers().size(), 2);
    }

    @Test
    public void updateUser() {
        userDao.updateUser(getUser());
        Assert.assertEquals(userDao.getUserById(1).getUserName(), "lukasz");
    }

    @Test
    public void deleteUser() {
        userDao.deleteUser(1);
        Assert.assertEquals(userDao.getAllUsers().size(), 0);
    }

    @Test
    public void getAllUsers() {
        Assert.assertEquals(userDao.getAllUsers().size(), 1);
    }

    @Test
    public void getUserById() {
        Assert.assertNotNull(userDao.getUserById(1));
    }


    public User getUser() {
        User user = new User();
        user.setUserName("lukasz");
        user.setUserId(1);
        user.setUserBirthday(Instant.ofEpochMilli(Date.valueOf("2017-01-02").getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        user.setUserWeight(100);
        user.setUserWeight(200);
        user.setUserSex("male");
        return user;
    }
}
