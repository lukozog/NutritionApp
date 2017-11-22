package ozog.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ozog.model.Mail;
import ozog.model.Meal;
import ozog.model.MealNutrition;
import ozog.model.Nutrition;
import ozog.service.MailService;
import ozog.service.MealNutritionService;
import ozog.service.MealService;
import ozog.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ozog.validator.MealNutritionValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MealNutritionController {

    @Autowired
    private MealNutritionService mealNutritionService;

    @Autowired
    private MealService mealService;

    @Autowired
    private NutritionService nutritionService;

    @InitBinder("mealNutrition")
    protected void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(new MealNutritionValidator());
    }

    @RequestMapping(value = {"/mealNutritions/{userId}/{mealId}"}, method = RequestMethod.GET)
    public String getAllMealNutritions(@PathVariable long userId,@PathVariable long mealId, ModelMap modelMap){
        modelMap.addAttribute("mealNutritions",mealNutritionService.getMealNutritions(mealId));
        modelMap.addAttribute("nutritions",nutritionService.getAllNutritions());
        modelMap.addAttribute("userId",userId);
        modelMap.addAttribute("mealId",mealId);
        return "allmealnutritions";
    }

    @RequestMapping(value = {"/mealNutrition/newMealNutrition/{userId}/{mealId}"},method = RequestMethod.GET)
    public String createNewMealNutrition(@PathVariable long userId, @PathVariable long mealId,ModelMap modelMap){
        modelMap.addAttribute("mealNutrition",new MealNutrition());
        modelMap.addAttribute("userId",userId);
        modelMap.addAttribute("mealId",mealId);
        modelMap.addAttribute("nutritions",nutritionService.getAllNutritions());
        modelMap.addAttribute("nutrition",new Nutrition());
        return "addmealnutrition";
    }

    @RequestMapping(value = {"/mealNutrition/newMealNutrition/{userId}/{mealId}"},method = RequestMethod.POST)
    public String createNewMealNutrition( @PathVariable long userId,  @PathVariable long mealId,Nutrition nutrition,
            @Valid  MealNutrition mealNutrition, BindingResult bindingResult, ModelMap modelMap){

        if(bindingResult.hasErrors()){
            modelMap.addAttribute("nutritions",nutritionService.getAllNutritions());
            return "addmealnutrition";
        }
        mealNutrition.setMeal(mealService.getMealById(mealId));
        mealNutrition.setNutrition(nutritionService.getNutritionById(nutrition.getNutritionId()));
        mealNutritionService.createMealNutrition(mealNutrition);
        mealNutritionService.sendEmail(mealId);
        return "redirect:/mealNutritions/{userId}/{mealId}";
    }

    @RequestMapping(value = "/mealNutrition/deleteNutrition/{userId}/{mealId}/{mealNutritionId}",method = RequestMethod.GET)
    public String deleteMealNutrition(@PathVariable long userId, @PathVariable long mealId,@PathVariable long mealNutritionId){
        mealNutritionService.deleteMealNutrition(mealNutritionId);
        return "redirect:/mealNutritions/{userId}/{mealId}";
    }

}
