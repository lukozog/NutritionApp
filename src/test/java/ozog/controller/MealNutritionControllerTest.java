package ozog.controller;

import org.junit.Assert;
import org.mockito.*;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ozog.model.Meal;
import ozog.model.MealNutrition;
import ozog.model.Nutrition;
import ozog.model.User;
import ozog.service.MealNutritionService;
import ozog.service.MealService;
import ozog.service.NutritionService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class MealNutritionControllerTest {


    @Mock
    MealNutritionService mealNutritionService;

    @Mock
    MealService mealService;

    @Mock
    NutritionService nutritionService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    MealNutritionController mealNutritionController;

    @Spy
    ModelMap modelMap;

    @Spy
    List<MealNutrition> mealNutritions=new ArrayList<>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mealNutritions=getMealNutritions();
    }

    @Test
    public void getAllMealNutritions(){
        Assert.assertEquals(mealNutritionController.getAllMealNutritions(createUser().getUserId(),createMeal().getMealId(),modelMap),"allmealnutritions");
        Assert.assertEquals(modelMap.get("mealNutritions"),mealNutritionService.getMealNutritions(mealNutritions.get(0).getMealNutritionId()));
        Assert.assertEquals(modelMap.get("nutritions"),nutritionService.getAllNutritions());
        Assert.assertEquals(modelMap.get("userId"),createUser().getUserId());
        Assert.assertEquals(modelMap.get("mealId"),createMeal().getMealId());
    }

    @Test
    public void createMealNutrition(){
        Assert.assertEquals(mealNutritionController.createNewMealNutrition(createUser().getUserId(),createMeal().getMealId(),modelMap),"addmealnutrition");
        Assert.assertNotNull(modelMap.get("mealNutrition"));
        Assert.assertEquals(modelMap.get("userId"),createUser().getUserId());
        Assert.assertEquals(modelMap.get("mealId"),createMeal().getMealId());
        Assert.assertNotNull(modelMap.get("nutrition"));
        Assert.assertEquals(modelMap.get("nutritions"),nutritionService.getAllNutritions());
    }


    @Test
    public void createMealNutritionZeroId(){
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doNothing().when(mealNutritionService).createMealNutrition(any(MealNutrition.class));
        Assert.assertEquals(mealNutritionController.createNewMealNutrition(createUser().getUserId(), createMeal().getMealId()
                , new Nutrition(), mealNutritions.get(0),bindingResult,modelMap),"redirect:/mealNutritions/{userId}/{mealId}");
    }

    @Test
    public void createMealNutritionError(){
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.doNothing().when(mealNutritionService).createMealNutrition(any(MealNutrition.class));
        List<Nutrition> nutritions=new ArrayList<>();
        when(nutritionService.getAllNutritions()).thenReturn(nutritions);
        modelMap.addAttribute("nutritions",nutritions);
        Assert.assertEquals(modelMap.get("nutritions"),nutritionService.getAllNutritions());
        Assert.assertEquals(mealNutritionController.createNewMealNutrition(createUser().getUserId(), createMeal().getMealId()
                , new Nutrition(), mealNutritions.get(0),bindingResult,modelMap),"addmealnutrition");

    }

    @Test
    public void createMealNutritionOk(){
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(mealService.getMealById(createMeal().getMealId())).thenReturn(createMeal());
        Mockito.when(nutritionService.getNutritionById(createNutrition().getNutritionId())).thenReturn(createNutrition());
        Mockito.doNothing().when(mealNutritionService).createMealNutrition(any(MealNutrition.class));
        Assert.assertEquals(mealNutritionController.createNewMealNutrition(createUser().getUserId(), createMeal().getMealId()
                , new Nutrition(), mealNutritions.get(0),bindingResult,modelMap),"redirect:/mealNutritions/{userId}/{mealId}");
    }

    @Test
    public void deleteMealNutrition(){
        long mealNutritionId=mealNutritions.get(0).getMealNutritionId();
        doNothing().when(mealNutritionService).deleteMealNutrition(anyLong());
        Assert.assertEquals(mealNutritionController.deleteMealNutrition(createUser().getUserId(),createMeal().getMealId(),mealNutritionId)
                ,"redirect:/mealNutritions/{userId}/{mealId}");
    }



    private List<MealNutrition> getMealNutritions(){
        MealNutrition mealNutrition=new MealNutrition();
        mealNutrition.setMealNutritionId(1);
        mealNutrition.setMealNutritionValue(10);
        mealNutrition.setMealNutritionCalories(0);
        mealNutrition.setMealNutritionProteins(0);
        mealNutrition.setMealNutritionCarbs(0);
        mealNutrition.setMealNutritionFat(0);
        mealNutrition.setMealNutritionName("TestMealNutrition");
        mealNutrition.setMeal(createMeal());
        mealNutritions.add(mealNutrition);
        return mealNutritions;
    }

    private User createUser(){
        User user = new User();
        user.setUserId(1);
        user.setUserName("TestUser");
        return user;
    }

    private Meal createMeal(){
        Meal meal = new Meal();
        meal.setUser(createUser());
        meal.setMealId(1);
        meal.setMealName("TestMeal");
        return meal;
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
