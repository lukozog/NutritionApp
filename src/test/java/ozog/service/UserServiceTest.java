package ozog.service;

import org.junit.Assert;
import org.mockito.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ozog.dao.UserDao;
import ozog.model.User;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

public class UserServiceTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserService userService;

    @Spy
    List<User> users=new ArrayList<>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        users=getUserList();
    }

    @Test
    public void createUser(){
        Mockito.doNothing().when(userDao).createUser(any(User.class));
        userService.createUser(any(User.class));
        Mockito.verify(userDao,Mockito.atLeastOnce()).createUser(any(User.class));
    }

    @Test
    public void updateUser(){
        Mockito.doNothing().when(userDao).updateUser(any(User.class));
        userService.updateUser(any(User.class));
        Mockito.verify(userDao,Mockito.atLeastOnce()).updateUser(any(User.class));
    }

    @Test
    public void deleteUser(){
        Mockito.doNothing().when(userDao).deleteUser(anyLong());
        userService.deleteUser(anyLong());
        Mockito.verify(userDao,Mockito.atLeastOnce()).deleteUser(anyLong());
    }

    @Test
    public void getUserById(){
        User user=users.get(0);
        Mockito.when(userDao.getUserById(1)).thenReturn(user);
        Assert.assertEquals(userService.getUserById(1),user);
    }

    @Test
    public void getAllUsers(){
        Mockito.when(userDao.getAllUsers()).thenReturn(users);
        Assert.assertEquals(userService.getAllUsers(),users);
    }

    public List<User> getUserList(){
        User user1=new User();
        user1.setUserId(1);
        user1.setUserBirthday(Instant.ofEpochMilli(Date.valueOf("2017-01-02").getTime()).atZone(ZoneId.systemDefault()).toLocalDate());;
        user1.setUserHeight(180);
        user1.setUserWeight(90);
        user1.setUserName("Orzeszkol");
        user1.setUserSex("male");

        User user2=new User();
        user2.setUserId(2);
        user2.setUserBirthday(Instant.ofEpochMilli(Date.valueOf("2017-01-02").getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        user2.setUserHeight(80);
        user2.setUserWeight(9);
        user2.setUserName("Orzeszkol2");
        user2.setUserSex("female");


        users.add(user1);
        users.add(user2);
        return users;
    }
}
