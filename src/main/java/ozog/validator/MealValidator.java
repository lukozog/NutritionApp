package ozog.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ozog.model.Meal;
import ozog.util.ValidatorErrorCodes;

@Component
public class MealValidator implements Validator, ValidatorErrorCodes {
    @Override
    public boolean supports(Class aClass) {
        return Meal.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Meal meal = (Meal) object;
        if (meal.getMealName().length() <= 2) {
            errors.rejectValue("mealName", TOO_SMALL_VALUE, TOO_SMALL_VALUE);
        }
    }
}
