package ozog.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ozog.model.User;
import ozog.util.ValidatorErrorCodes;

@Component
public class UserValidator implements Validator, ValidatorErrorCodes {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", EMPTY_INPUT, EMPTY_INPUT);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userSex", EMPTY_INPUT, EMPTY_INPUT);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userMail", EMPTY_INPUT, EMPTY_INPUT);
        if (user.getUserWeight() <= 0) {
            errors.rejectValue("userWeight", NEGATIVE_VALUE, NEGATIVE_VALUE);
        }
        if (user.getUserHeight() <= 0) {
            errors.rejectValue("userHeight", NEGATIVE_VALUE, NEGATIVE_VALUE);
        }
    }
}
