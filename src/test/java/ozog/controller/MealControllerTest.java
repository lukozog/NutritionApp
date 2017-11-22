package ozog.controller;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ozog.model.Meal;
import ozog.model.User;
import ozog.service.MealService;
import ozog.service.UserService;

import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class MealControllerTest {

    @Mock
    MealService mealService;

    @Mock
    UserService userService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    MealController mealController;

    @Spy
    List<Meal> meals=new ArrayList<>();

    @Spy
    ModelMap modelMap;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        meals=getMeals();
    }


    @Test
    public void getMealForUser(){
        Assert.assertEquals(mealController.getMealForUser(createUser().getUserId(),modelMap),"allmeals");
        when(mealService.getMealsForUser(createUser().getUserId())).thenReturn(meals);
        Assert.assertEquals(modelMap.get("meals"),mealService.getAllMeals());
        Assert.assertEquals(modelMap.get("userId"),createUser().getUserId());
    }

    @Test
    public void createMeal(){
        User user=meals.get(0).getUser();
        Assert.assertEquals(mealController.createMeal(user.getUserId(),modelMap),"addmeal");
        Assert.assertEquals(modelMap.get("meal"),new Meal());
        Assert.assertEquals(modelMap.get("userId"),user.getUserId());
        Assert.assertEquals(modelMap.get("add"),true);
    }

    @Test
    public void createMealError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(mealService).createMeal(any(Meal.class));
        Assert.assertEquals(mealController.createMeal(createUser().getUserId(), meals.get(0),bindingResult,modelMap),"addmeal");
        Assert.assertEquals(modelMap.get("add"),true);
    }

    @Test
    public void createMealOk(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.getUserById(createUser().getUserId())).thenReturn(createUser());
        doNothing().when(mealService).createMeal(any(Meal.class));
        Assert.assertEquals(mealController.createMeal(createUser().getUserId(), meals.get(0),bindingResult,modelMap),"redirect:/meals/{userId}");
    }

    @Test
    public void updateMeal(){
        Meal meal=mealService.getMealById(meals.get(0).getMealId());
        when(mealService.getMealById(anyLong())).thenReturn(meal);
        modelMap.addAttribute("meal",meal);
        Assert.assertEquals(modelMap.get("meal"),meal);
        Assert.assertEquals(mealController.updateMeal(createUser().getUserId(),meals.get(0).getMealId(),modelMap),"addmeal");
    }

    @Test
    public void updateMealError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(mealService).updateMeal(any(Meal.class));
        Assert.assertEquals(mealController.updateMeal(createUser().getUserId(),meals.get(0).getMealId(),meals.get(0),bindingResult,modelMap),"addmeal");
    }

    @Test
    public void updateMealOk(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.getUserById(createUser().getUserId())).thenReturn(createUser());
        doNothing().when(mealService).updateMeal(any(Meal.class));
        Assert.assertEquals(mealController.updateMeal(createUser().getUserId(),meals.get(0).getMealId(),meals.get(0),bindingResult,modelMap),"redirect:/meals/{userId}");
    }

    @Test
    public void deleteMeal(){
        doNothing().when(mealService).deleteMeal(anyLong());
        Assert.assertEquals(mealController.deleteMeal(createUser().getUserId(),meals.get(0).getMealId()),"redirect:/meals/{userId}");
    }

    public List<Meal> getMeals(){
        Meal meal =new Meal();
        meal.setMealId(1);
        meal.setMealName("TestMeal");
        meal.setMealDate(Instant.ofEpochMilli(Date.valueOf("2017-01-02").getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        meal.setMealCalories(100);
        meal.setMealProteins(10);
        meal.setMealCarbs(10);
        meal.setMealFat(10);
        meal.setUser(createUser());
        meals.add(meal);
        return meals;
    }

    public User createUser(){
        User user=new User();
        user.setUserId(1);
        user.setUserName("TestUser");
        return user;
    }
}
