package ozog.service;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ozog.dao.MealDao;
import ozog.model.Meal;
import ozog.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MealServiceTest {

    @Mock
    MealDao mealDao;

    @InjectMocks
    MealService mealService;

    @Spy
    List<Meal> meals=new ArrayList<>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        meals=getMeals();
    }

    @Test
    public void createMeal(){
        doNothing().when(mealDao).createMeal(any(Meal.class));
        mealService.createMeal(meals.get(0));
        verify(mealDao,atLeastOnce()).createMeal(any(Meal.class));
    }

    @Test
    public void updateMeal(){
        doNothing().when(mealDao).updateMeal(any(Meal.class));
        mealService.updateMeal(meals.get(0));
        verify(mealDao,atLeastOnce()).updateMeal(any(Meal.class));
    }

    @Test
    public void deleteMeal(){
        doNothing().when(mealDao).deleteMeal(anyLong());
        mealService.deleteMeal(anyLong());
        verify(mealDao,atLeastOnce()).deleteMeal(anyLong());
    }

    @Test
    public void getMealById(){
        Meal meal=meals.get(0);
        when(mealDao.getMealById(meals.get(0).getMealId())).thenReturn(meal);
        Assert.assertEquals(mealService.getMealById(meal.getMealId()).getMealName(),"TestMeal");
    }

    @Test
    public void getMealsForUser(){
        User user=meals.get(0).getUser();
        when(mealDao.getMealsForUser(user.getUserId())).thenReturn(meals);
        Assert.assertEquals(mealService.getMealsForUser(user.getUserId()),meals);
    }

    @Test
    public void getAllMeals(){
        when(mealDao.getAllMeals()).thenReturn(meals);
        Assert.assertEquals(mealService.getAllMeals(),meals);
    }



    public List<Meal> getMeals(){
        Meal meal =new Meal();
        meal.setMealId(1);
        meal.setMealName("TestMeal");
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
