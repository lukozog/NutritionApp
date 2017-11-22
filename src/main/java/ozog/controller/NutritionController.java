package ozog.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ozog.model.Nutrition;
import ozog.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ozog.validator.NutritionValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NutritionController {


    @Autowired
    private NutritionService nutritionService;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new NutritionValidator());
    }

    @RequestMapping(value = {"/nutritions"}, method = RequestMethod.GET)
    public String getAllNutritions(ModelMap model) {
        List<Nutrition> allNutritions = nutritionService.getAllNutritions();
        model.addAttribute("allNutritions", allNutritions);
        return "allnutritions";

    }

    @RequestMapping(value = {"/nutritions/newNutrition"}, method = RequestMethod.GET)
    public String createNutrition(ModelMap modelMap) {
        Nutrition nutrition = new Nutrition();
        modelMap.addAttribute("nutrition", nutrition);
        modelMap.addAttribute("add", true);
        return "addnutrition";
    }

    @RequestMapping(value = {"/nutritions/newNutrition"}, method = RequestMethod.POST)
    public String createNutrition(@Valid Nutrition nutrition, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("add", true);
            return "addnutrition";
        }
        nutritionService.createNutrition(nutrition);
        return "redirect:/nutritions";
    }

    @RequestMapping(value = {"/nutritions/updateNutrition/{nutritionId}"}, method = RequestMethod.GET)
    public String updateNutrition(@PathVariable long nutritionId, ModelMap modelMap) {
        Nutrition nutrition = nutritionService.getNutritionById(nutritionId);
        modelMap.addAttribute("nutrition", nutrition);
        return "addnutrition";
    }

    @RequestMapping(value = {"/nutritions/updateNutrition/{nutritionId}"}, method = RequestMethod.POST)
    public String updateNutrition(@PathVariable long nutritionId, @Valid Nutrition nutrition,
                                  BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "addnutrition";
        }
        nutritionService.updateNutrition(nutrition);
        return "redirect:/nutritions";
    }

    @RequestMapping(value = {"/nutritions/deleteNutrition/{nutritionId}"}, method = RequestMethod.GET)
    public String deleteNutrition(@PathVariable long nutritionId) {
        nutritionService.deleteNutrition(nutritionId);
        return "redirect:/nutritions";
    }
}
