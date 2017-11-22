package ozog.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import ozog.model.Meal;
import ozog.model.User;
import ozog.util.ValidatorErrorCodes;
import ozog.validator.MealValidator;

import java.time.LocalDate;


import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MealValidatorTest implements ValidatorErrorCodes {

    private static String MEAL_NAME="meal";
    private static String TOO_SHORT_MEAL_NAME="aa";
    private static LocalDate MEAL_DATE=LocalDate.now();
    private static double MEAL_CALORIES=1;
    private static double MEAL_PROTEINS=1;
    private static double MEAL_CARBS=1;
    private static double MEAL_FAT=1;
    private static User USER=new User();


    private Errors errors;
    private Meal meal;

    @InjectMocks
    MealValidator mealValidator;

    @Test
    public void shouldPassAllValidations(){
        prepareForTest(MEAL_NAME,MEAL_DATE,MEAL_CALORIES,MEAL_PROTEINS,MEAL_CARBS,MEAL_FAT,USER);
        mealValidator.validate(meal,errors);
        assertEquals(0,errors.getErrorCount());
    }


    @Test
    public void shouldNotPassValidationsDueToTooShortMealName(){
        prepareForTest(TOO_SHORT_MEAL_NAME,MEAL_DATE,MEAL_CALORIES,MEAL_PROTEINS,MEAL_CARBS,MEAL_FAT,USER);
        mealValidator.validate(meal,errors);
        assertSingleError(TOO_SMALL_VALUE);
    }

    private void assertSingleError(String errorCode){
        assertEquals(1,errors.getErrorCount());
        assertEquals(errorCode,errors.getAllErrors().get(0).getCode());
    }

    private void prepareForTest(String mealName, LocalDate mealDate, double mealCalories, double mealProteins, double mealCarbs, double mealFat, User user){
        meal=new Meal(mealName,mealDate,mealCalories,mealProteins,mealCarbs,mealFat,user);
        errors=new BeanPropertyBindingResult(meal,"meal");
    }
}
