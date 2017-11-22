package ozog.controller;


import org.junit.Assert;
import org.mockito.*;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ozog.model.Meal;
import ozog.model.User;
import ozog.service.UserService;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;

public class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    UserController userController;

    @Spy
    ModelMap modelMap;

    @Spy
    List<User> users=new ArrayList<>();



    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        users=getUserList();

    }

    @Test
    public void listUsers(){
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        Assert.assertEquals(userController.getAllUsers(modelMap),"allusers");
        Assert.assertEquals(modelMap.get("users"),users);
        Mockito.verify(userService,Mockito.atLeastOnce()).getAllUsers();
    }

    @Test
    public void newUser(){
        Assert.assertEquals(userController.newUser(modelMap),"adduser");
        Assert.assertEquals(modelMap.get("add"),true);
        Assert.assertNotNull(modelMap.get("user"));
        Assert.assertEquals(((User)modelMap.get("user")).getMeals(),null);
    }

    @Test
    public void saveUserError(){
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.doNothing().when(userService).createUser(any(User.class));
        Assert.assertEquals(userController.saveUser(users.get(1),bindingResult,modelMap),"adduser");
    }

    @Test
    public void saveUserOk(){
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doNothing().when(userService).createUser(any(User.class));
        Assert.assertEquals(userController.saveUser(users.get(1),bindingResult,modelMap),"redirect:/users");
    }

    @Test
    public void updateUser(){
        User user=users.get(1);
        Mockito.when(userService.getUserById(anyLong())).thenReturn(user);
        Assert.assertEquals(userController.editUser(anyLong(),modelMap),"adduser");
        Assert.assertNotNull(modelMap.get("user"));
        Assert.assertEquals(((User)modelMap.get("user")).getUserId(),2);
    }

    @Test
    public void updateUserError(){
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.doNothing().when(userService).updateUser(any(User.class));
        Assert.assertEquals(userController.editUser(users.get(0).getUserId(),users.get(0),bindingResult,modelMap),"adduser");
    }

    @Test
    public void updateUserOk(){
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doNothing().when(userService).updateUser(any(User.class));
        Assert.assertEquals(userController.editUser(users.get(0).getUserId(),users.get(0),bindingResult,modelMap),"redirect:/users");
    }

    @Test
    public void deleteUser(){
        Mockito.doNothing().when(userService).deleteUser(anyLong());
        Assert.assertEquals(userController.deleteUser(1,modelMap),"redirect:/users");
    }

    public List<User> getUserList(){
        User user1=new User();
        user1.setUserId(1);
        user1.setUserBirthday(Instant.ofEpochMilli(Date.valueOf("2017-01-02").getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
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
