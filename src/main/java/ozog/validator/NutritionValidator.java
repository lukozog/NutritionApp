package ozog.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ozog.model.Nutrition;
import ozog.util.ValidatorErrorCodes;

@Component
public class NutritionValidator implements Validator, ValidatorErrorCodes {

    @Override
    public boolean supports(Class<?> aClass) {
        return Nutrition.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Nutrition nutrition = (Nutrition) object;
        if (nutrition.getNutritionName().length() <= 2)
            errors.rejectValue("nutritionName", TOO_SMALL_VALUE, TOO_SMALL_VALUE);
        if (nutrition.getNutritionProteins() <= 0)
            errors.rejectValue("nutritionProteins", NEGATIVE_VALUE, NEGATIVE_VALUE);
        if (nutrition.getNutritionCarbs() <= 0)
            errors.rejectValue("nutritionCarbs", NEGATIVE_VALUE, NEGATIVE_VALUE);
        if (nutrition.getNutritionFat() <= 0)
            errors.rejectValue("nutritionFat", NEGATIVE_VALUE, NEGATIVE_VALUE);
    }
}
