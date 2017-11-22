package ozog.validators;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import ozog.model.Meal;
import ozog.model.MealNutrition;
import ozog.model.Nutrition;
import ozog.util.ValidatorErrorCodes;
import ozog.validator.MealNutritionValidator;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MealNutritionValidatorTest implements ValidatorErrorCodes{

    private static double MEAL_NUTRITION_VALUE=1;
    private static double NEGATIVE_MEAL_NUTRITION_VALUE=-1;
    private static double ZERO_MEAL_NUTRITION_VALUE=0;
    private static String MEAL_NUTRITION_NAME="mealNutrition";
    private static double MEAL_NUTRITION_CALORIES=1;
    private static double MEAL_NUTRITION_PROTEIN=1;
    private static double MEAL_NUTRITION_CARBS=1;
    private static double MEAL_NUTRITION_FAT=1;
    private static Meal MEAL=new Meal();
    private static Nutrition NUTRITION=new Nutrition();

    private Errors errors;
    private MealNutrition mealNutrition;

    @InjectMocks
    private MealNutritionValidator mealNutritionValidator;

    @Test
    public void shouldPassValidation(){
        prepareForTest(MEAL_NUTRITION_VALUE,MEAL_NUTRITION_NAME,MEAL_NUTRITION_CALORIES,MEAL_NUTRITION_PROTEIN,MEAL_NUTRITION_CARBS,MEAL_NUTRITION_FAT,MEAL,NUTRITION);
        mealNutritionValidator.validate(mealNutrition,errors);
        assertEquals(0,errors.getErrorCount());
    }

    @Test
    public void shouldFailValidationDueToZeroMealNutritionValue(){
        prepareForTest(ZERO_MEAL_NUTRITION_VALUE,MEAL_NUTRITION_NAME,MEAL_NUTRITION_CALORIES,MEAL_NUTRITION_PROTEIN,MEAL_NUTRITION_CARBS,MEAL_NUTRITION_FAT,MEAL,NUTRITION);
        mealNutritionValidator.validate(mealNutrition,errors);
        assertSingleError(TOO_SMALL_VALUE);
    }

    @Test
    public void shouldFailValidationDueToNegativeMealNutritionValue(){
        prepareForTest(NEGATIVE_MEAL_NUTRITION_VALUE,MEAL_NUTRITION_NAME,MEAL_NUTRITION_CALORIES,MEAL_NUTRITION_PROTEIN,MEAL_NUTRITION_CARBS,MEAL_NUTRITION_FAT,MEAL,NUTRITION);
        mealNutritionValidator.validate(mealNutrition,errors);
        assertSingleError(NEGATIVE_VALUE);
    }


    private void assertSingleError(String errorCode){
        assertEquals(1,errors.getErrorCount());
        assertEquals(errorCode,errors.getAllErrors().get(0).getCode());
    }

    private void prepareForTest(double mealNutritionValue, String mealNutritionName, double mealNutritionCalories, double mealNutritionProteins, double mealNutritionCarbs, double mealNutritionFat, Meal meal, Nutrition nutrition) {
        mealNutrition=new MealNutrition(mealNutritionValue,mealNutritionName,mealNutritionCalories,mealNutritionProteins,mealNutritionCarbs,mealNutritionFat,meal,nutrition);
        errors=new BeanPropertyBindingResult(mealNutrition,"mealNutrition");
    }

}
