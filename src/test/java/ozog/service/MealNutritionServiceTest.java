package ozog.service;

import org.junit.Assert;
import org.mockito.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ozog.dao.MealNutritionDao;
import ozog.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class MealNutritionServiceTest {

    @Mock
    MealNutritionDao mealNutritionDao;

    @Mock
    MealService mealService;

    @Mock
    MailService mailService;

    @InjectMocks
    MealNutritionService mealNutritionService;

    @Spy
    List<MealNutrition> mealNutritions = new ArrayList<>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mealNutritions=getMealNutrition();
    }



    @Test
    public void createMealNutrition(){
        doNothing().when(mealNutritionDao).createMealNutrition(any(MealNutrition.class));
        mealNutritionService.createMealNutrition(mealNutritions.get(0));
        verify(mealNutritionDao, atLeastOnce()).createMealNutrition(any(MealNutrition.class));
    }

    @Test
    public void deleteMealNutrition(){
        doNothing().when(mealNutritionDao).deleteMealNutrition(anyLong());
        mealNutritionService.deleteMealNutrition(anyLong());
        verify(mealNutritionDao,atLeastOnce()).deleteMealNutrition(anyLong());
    }

    @Test
    public void getMealNutritions(){
        Meal meal=mealNutritions.get(0).getMeal();
        when(mealNutritionDao.getMealNutritions(meal.getMealId())).thenReturn(mealNutritions);
        when(mealService.getMealById(anyLong())).thenReturn(meal);
        assertEquals(mealNutritionService.getMealNutritions(meal.getMealId()).size(),2);
    }


    @Test
    public void calculateValues(){
        MealNutrition mealNutritionAfter=new MealNutrition();
        mealNutritionAfter.setMealNutritionId(1);
        mealNutritionAfter.setMealNutritionValue(10);
        mealNutritionAfter.setMealNutritionCalories(200);
        mealNutritionAfter.setMealNutritionProteins(20);
        mealNutritionAfter.setMealNutritionCarbs(20);
        mealNutritionAfter.setMealNutritionFat(20);
        mealNutritionAfter.setNutrition(mealNutritions.get(0).getNutrition());
        mealNutritionAfter.setMealNutritionName("testProduct");
        mealNutritionAfter.setMeal(mealNutritions.get(0).getMeal());
        assertEquals(mealNutritionService.calculateValues(mealNutritions.get(0)),mealNutritionAfter);
    }

    @Test
    public void updateMealNutritions(){
        Meal meal=createMeal();
        when(mealService.getMealById(meal.getMealId())).thenReturn(meal);
        mealNutritionService.calculateValues(mealNutritions.get(0));
        mealNutritionService.updateMealNutritions(mealNutritions,meal.getMealId());
        assertEquals(meal.getMealCarbs(),20,0.0);
    }

    @Test
    public void getLastMealNutrition(){
        Meal meal=createMeal();
        MealNutrition mealNutrition=getMealNutrition().get(0);
        when(mealNutritionDao.getMealNutritions(meal.getMealId())).thenReturn(mealNutritions);
        when(mealService.getMealById(anyLong())).thenReturn(meal);
        mealNutritionService.updateMealNutritions(mealNutritions,meal.getMealId());
        when(mealNutritionDao.getLastMealNutritionById(meal.getMealId())).thenReturn(mealNutrition);
        assertEquals(mealNutritionService.getLastMealNutrition(meal.getMealId()),mealNutrition);
    }

    @Test
    public void sendEmail(){
        MealNutrition mealNutrition=getMealNutrition().get(0);
        when(mealService.getMealById(anyLong())).thenReturn(mealNutrition.getMeal());
        when(mealNutritionService.getLastMealNutrition(anyLong())).thenReturn(mealNutrition);
        Mail mail = new Mail();
        mail.setMailAddress(mealNutrition.getMeal().getUser().getUserMail());
        mail.setMailTopic("New calories in " + mealNutrition.getMeal().getMealName() + " meal");
        when(mealNutritionDao.getLastMealNutritionById(mealNutrition.getMeal().getMealId())).thenReturn(mealNutrition);
        mealNutritionService.mailText(mealNutrition.getMeal(),mealNutrition);
        mealNutritionService.sendEmail(mealNutrition.getMeal().getMealId());
    }

    @Test
    public void mailText(){
        Meal meal=createMeal();
        MealNutrition mealNutrition=getMealNutrition().get(0);
        String text="You've just added new nutrition to your meal?\n" +
                mealNutrition.getMealNutritionName() + ": \n" +
                "Cal: " + mealNutrition.getMealNutritionCalories() + " Proteins: " + mealNutrition.getMealNutritionProteins() +
                " Carbs: " + mealNutrition.getMealNutritionCarbs() + " Fat: " + mealNutrition.getMealNutritionFat() + "\n" +
                "As a whole value for meal there will be\n" +
                "Meal Cal: " + Math.round(meal.getMealCalories()) + " Meal Proteins: " + Math.round(meal.getMealProteins()) +
                " Meal Carbs: " + Math.round(meal.getMealCarbs()) + " Meal Fat: " + Math.round(meal.getMealFat()) + "\n" +
                "Cheers, remember we're watching your progress";
        assertEquals(mealNutritionService.mailText(meal,mealNutrition),text);
    }


    private List<MealNutrition> getMealNutrition(){
        MealNutrition mealNutrition=new MealNutrition();

        mealNutrition.setMealNutritionId(1);
        mealNutrition.setMealNutritionValue(10);
        mealNutrition.setMealNutritionCalories(0);
        mealNutrition.setMealNutritionProteins(0);
        mealNutrition.setMealNutritionCarbs(0);
        mealNutrition.setMealNutritionFat(0);
        mealNutrition.setMealNutritionName("testProduct");

        mealNutrition.setMeal(createMeal());
        mealNutrition.setNutrition(createNutrition());
        mealNutritions.add(mealNutrition);
        return mealNutritions;
    }


    public Meal createMeal(){
        Meal meal = new Meal();
        meal.setUser(new User());
        meal.setMealId(1);
        meal.setMealName("mealName");
        return meal;
    }

    public Nutrition createNutrition(){
        Nutrition nutrition=new Nutrition();
        nutrition.setNutritionName("testProduct");
        nutrition.setNutritionId(1);
        nutrition.setNutritionProteins(200);
        nutrition.setNutritionCarbs(200);
        nutrition.setNutritionFat(200);
        nutrition.setNutritionCalories(2000);
        return nutrition;
    }

}
