package ozog.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import ozog.model.MealNutrition;
import ozog.model.Nutrition;
import ozog.util.ValidatorErrorCodes;
import ozog.validator.NutritionValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class NutritionValidatorTest implements ValidatorErrorCodes {

    private static String NUTRITION_NAME="nutrition";
    private static String TOO_SHORT_NUTRITION_NAME="nu";
    private static double NUTRITION_CALORIES=1;
    private static double NUTRITION_PROTEIN=1;
    private static double NEGATIVE_NUTRITION_PROTEIN=-1;
    private static double NUTRITION_CARBS=1;
    private static double NEGATIVE_NUTRITION_CARBS=-1;
    private static double NUTRITION_FAT=1;
    private static double NEGATIVE_NUTRITION_FAT=-1;
    private static List<MealNutrition> MEAL_NUTRITIONS= new ArrayList<>();

    private Errors errors;
    private Nutrition nutrition;

    @InjectMocks
    NutritionValidator nutritionValidator;

    @Test
    public void shouldPassAllValidations(){
        prepareForTest(NUTRITION_NAME,NUTRITION_CALORIES,NUTRITION_PROTEIN,NUTRITION_CARBS,NUTRITION_FAT,MEAL_NUTRITIONS);
        nutritionValidator.validate(nutrition,errors);
        assertEquals(0,errors.getErrorCount());
    }

    @Test
    public void shouldNotPassValidationsDueToTooShortMealName(){
        prepareForTest(TOO_SHORT_NUTRITION_NAME,NUTRITION_CALORIES,NUTRITION_PROTEIN,NUTRITION_CARBS,NUTRITION_FAT,MEAL_NUTRITIONS);
        nutritionValidator.validate(nutrition,errors);
        assertSingleError(TOO_SMALL_VALUE);
    }

    @Test
    public void shouldNotPassValidationsDueToNegativeProteinValue(){
        prepareForTest(NUTRITION_NAME,NUTRITION_CALORIES,NEGATIVE_NUTRITION_PROTEIN,NUTRITION_CARBS,NUTRITION_FAT,MEAL_NUTRITIONS);
        nutritionValidator.validate(nutrition,errors);
        assertSingleError(NEGATIVE_VALUE);
    }

    @Test
    public void shouldNotPassValidationsDueToNegativeCarbsValue(){
        prepareForTest(NUTRITION_NAME,NUTRITION_CALORIES,NUTRITION_PROTEIN,NEGATIVE_NUTRITION_CARBS,NUTRITION_FAT,MEAL_NUTRITIONS);
        nutritionValidator.validate(nutrition,errors);
        assertSingleError(NEGATIVE_VALUE);
    }

    @Test
    public void shouldNotPassValidationsDueToNegativeFatValue(){
        prepareForTest(NUTRITION_NAME,NUTRITION_CALORIES,NUTRITION_PROTEIN,NUTRITION_CARBS,NEGATIVE_NUTRITION_FAT,MEAL_NUTRITIONS);
        nutritionValidator.validate(nutrition,errors);
        assertSingleError(NEGATIVE_VALUE);
    }



    private void assertSingleError(String errorCode){
        assertEquals(1,errors.getErrorCount());
        assertEquals(errorCode,errors.getAllErrors().get(0).getCode());
    }

    private void prepareForTest(String nutritionName, double nutritionCalories, double nutritionProteins, double nutritionCarbs, double nutritionFat, List<MealNutrition> mealNutritions) {
        nutrition=new Nutrition(nutritionName,nutritionCalories,nutritionProteins,nutritionCarbs,nutritionFat,mealNutritions);
        errors=new BeanPropertyBindingResult(nutrition,"nutrition");
    }
}
