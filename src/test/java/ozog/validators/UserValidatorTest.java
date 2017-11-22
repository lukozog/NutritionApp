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
import ozog.validator.UserValidator;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest implements ValidatorErrorCodes {

    private static String USER_NAME="user";
    private static LocalDate USER_BIRTHDAY= LocalDate.now();
    private static double USER_WEIGHT=1;
    private static double NEGATIVE_USER_WEIGHT=-1;
    private static double USER_HEIGHT=1;
    private static double NEGATIVE_USER_HEIGHT=-1;
    private static String USER_SEX="sex";
    private static String USER_MAIL="mail@mail.pl";
    private static List<Meal> MEALS=new ArrayList<>();

    private Errors errors;
    private User user;

    @InjectMocks
    private UserValidator userValidator;

    @Test
    public void shouldPassValidation(){
        prepareForTest(USER_NAME,USER_BIRTHDAY,USER_WEIGHT,USER_HEIGHT,USER_SEX,USER_MAIL,MEALS);
        userValidator.validate(user,errors);
        assertEquals(0,errors.getErrorCount());
    }

    @Test
    public void shouldFailValidationDueToEmptyUserName(){
        prepareForTest(EMPTY, USER_BIRTHDAY,USER_WEIGHT,USER_HEIGHT,USER_SEX,USER_MAIL,MEALS);
        userValidator.validate(user,errors);
        assertSingleError(EMPTY_INPUT);
    }

    @Test
    public void shouldFailValidationDueToEmptyUserSex(){
        prepareForTest(USER_NAME,USER_BIRTHDAY,USER_WEIGHT,USER_HEIGHT,EMPTY ,USER_MAIL,MEALS);
        userValidator.validate(user,errors);
        assertSingleError(EMPTY_INPUT);
    }

    @Test
    public void shouldFailValidationDueToEmptyUserMail(){
        prepareForTest(USER_NAME,USER_BIRTHDAY,USER_WEIGHT,USER_HEIGHT,USER_SEX,EMPTY,MEALS);
        userValidator.validate(user,errors);
        assertSingleError(EMPTY_INPUT);
    }

    @Test
    public void shouldFailValidationDueToNegativeUserWeight(){
        prepareForTest(USER_NAME,USER_BIRTHDAY,NEGATIVE_USER_WEIGHT,USER_HEIGHT,USER_SEX,USER_MAIL,MEALS);
        userValidator.validate(user,errors);
        assertSingleError(NEGATIVE_VALUE);
    }

    @Test
    public void shouldFailValidationDueToNegativeUserHeight(){
        prepareForTest(USER_NAME,USER_BIRTHDAY,NEGATIVE_USER_HEIGHT,USER_HEIGHT,USER_SEX,USER_MAIL,MEALS);
        userValidator.validate(user,errors);
        assertSingleError(NEGATIVE_VALUE);
    }

    private void assertSingleError(String errorCode){
        assertEquals(1,errors.getErrorCount());
        assertEquals(errorCode,errors.getAllErrors().get(0).getCode());
    }

    private void prepareForTest(String userName, LocalDate userBirthday, double userWeight, double userHeight, String userSex, String userMail, List<Meal> meals) {
        user=new User(userName,userBirthday,userWeight,userHeight,userSex,userMail,meals);
        errors=new BeanPropertyBindingResult(user,"user");
    }
}
