package ozog.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ozog.model.MealNutrition;
import ozog.util.ValidatorErrorCodes;

@Component
public class MealNutritionValidator implements Validator, ValidatorErrorCodes {
    @Override
    public boolean supports(Class<?> aClass) {
        return MealNutrition.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        MealNutrition mealNutrition = (MealNutrition) object;
        if (mealNutrition.getMealNutritionValue() == 0) {
            errors.rejectValue("mealNutritionValue", TOO_SMALL_VALUE, TOO_SMALL_VALUE);
        }
        if (mealNutrition.getMealNutritionValue() < 0) {
            errors.rejectValue("mealNutritionValue", NEGATIVE_VALUE, NEGATIVE_VALUE);
        }


    }
}
