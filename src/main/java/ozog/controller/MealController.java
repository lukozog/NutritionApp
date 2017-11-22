package ozog.controller;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ozog.model.Meal;
import ozog.service.MealService;
import ozog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import ozog.validator.MealValidator;


import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class MealController {

    @Autowired
    private MealService mealService;

    @Autowired
    private UserService userService;

    @InitBinder()
    protected void initiBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new MealValidator());
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }


            @Override
            public String getAsText() throws IllegalArgumentException {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
            }
        });
    }


    @RequestMapping(value = {"/meals/{userId}"}, method = RequestMethod.GET)
    public String getMealForUser(@PathVariable long userId, ModelMap model) {
        List<Meal> meals = mealService.getMealsForUser(userId);
        model.addAttribute("meals", meals);
        model.addAttribute("userId", userId);
        return "allmeals";

    }

    @RequestMapping(value = {"/meals/newMeal/{userId}"}, method = RequestMethod.GET)
    public String createMeal(@PathVariable long userId, ModelMap modelMap) {
        Meal meal = new Meal();
        modelMap.addAttribute("meal", meal);
        modelMap.addAttribute("userId", userId);
        modelMap.addAttribute("add", true);
        return "addmeal";
    }


    @RequestMapping(value = {"/meals/newMeal/{userId}"}, method = RequestMethod.POST)
    public String createMeal(@PathVariable long userId, @Valid Meal meal, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("add", true);
            return "addmeal";
        }
        meal.setUser(userService.getUserById(userId));
        mealService.createMeal(meal);
        return "redirect:/meals/{userId}";
    }

    @RequestMapping(value = {"/meals/updateMeal/{userId}/{mealId}"}, method = RequestMethod.GET)
    public String updateMeal(@PathVariable long userId, @PathVariable long mealId, ModelMap modelMap) {
        Meal meal = mealService.getMealById(mealId);
        modelMap.addAttribute("meal", meal);
        return "addmeal";
    }

    @RequestMapping(value = {"/meals/updateMeal/{userId}/{mealId}"}, method = RequestMethod.POST)
    public String updateMeal(@PathVariable long userId, @PathVariable long mealId, @Valid Meal meal,
                             BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "addmeal";
        }
        meal.setUser(userService.getUserById(userId));
        mealService.updateMeal(meal);
        return "redirect:/meals/{userId}";
    }

    @RequestMapping(value = {"/meals/deleteMeal/{userId}/{mealId}"}, method = RequestMethod.GET)
    public String deleteMeal(@PathVariable long userId, @PathVariable long mealId) {
        mealService.deleteMeal(mealId);
        return "redirect:/meals/{userId}";
    }
}
