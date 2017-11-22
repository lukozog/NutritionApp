package ozog.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import ozog.model.Meal;
import ozog.model.Nutrition;
import ozog.model.User;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MealDaoTest extends EntityDaoImplTest {

    @Autowired
    MealDao mealDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet[] datasets = new IDataSet[] {
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Meal.xml"))
        };
        return new CompositeDataSet(datasets);
    }

    @Test
    public void createMeal(){
        mealDao.createMeal(getMeal());
        Assert.assertEquals(mealDao.getAllMeals().size(),2);
    }

    @Test
    public void updateMeal(){
        mealDao.updateMeal(getMeal());
        Assert.assertEquals(mealDao.getMealById(1).getMealDate(),Instant.ofEpochMilli(Date.valueOf("2017-01-02").getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @Test
    public void deleteMeal(){
        mealDao.deleteMeal(1);
        Assert.assertEquals(mealDao.getAllMeals().size(),0);
    }

    @Test
    public void getMealsForUser(){
        List<Meal>meals=new ArrayList<>();
        Assert.assertEquals(mealDao.getMealsForUser(1),meals);
    }

    @Test
    public void getMealById(){
        Assert.assertNotNull(mealDao.getMealById(1));
    }

    @Test
    public void getAllMeals(){
        Assert.assertEquals(mealDao.getAllMeals().size(),1);
    }

    public Meal getMeal(){
        Meal meal=new Meal();
        meal.setMealId(1);
        meal.setMealName("TestMeal2");
        meal.setMealCalories(0);
        meal.setMealProteins(0);
        meal.setMealCarbs(0);
        meal.setMealFat(0);
        meal.setMealDate(Instant.ofEpochMilli(Date.valueOf("2017-01-02").getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        return meal;
    }

    private User createUser(){
        User user = new User();
        user.setUserId(1);
        user.setUserName("TestUser");
        return user;
    }

    private Nutrition createNutrition(){
        Nutrition nutrition=new Nutrition();
        nutrition.setNutritionName("TestNutrition");
        nutrition.setNutritionId(1);
        nutrition.setNutritionProteins(100);
        nutrition.setNutritionCarbs(100);
        nutrition.setNutritionFat(100);
        nutrition.setNutritionCalories(1500);
        return nutrition;
    }
}
