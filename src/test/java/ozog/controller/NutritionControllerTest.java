package ozog.controller;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ozog.model.Nutrition;
import ozog.service.NutritionService;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class NutritionControllerTest {

    @Mock
    NutritionService nutritionService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    NutritionController nutritionController;

    @Spy
    ModelMap modelMap;

    @Spy
    List<Nutrition> nutritions=new ArrayList<>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        nutritions=getNutritions();
    }

    @Test
    public void getAllNutritions(){
        when(nutritionService.getAllNutritions()).thenReturn(nutritions);
        Assert.assertEquals(nutritionController.getAllNutritions(modelMap),"allnutritions");
        Assert.assertEquals(modelMap.get("allNutritions"),nutritions);
    }

    @Test
    public void createNutrition(){
        Assert.assertEquals(nutritionController.createNutrition(modelMap),"addnutrition");
        Assert.assertEquals(modelMap.get("nutrition"),new Nutrition());
        Assert.assertEquals(modelMap.get("add"),true);
    }

    @Test
    public void createNutritionError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        Assert.assertEquals(nutritionController.createNutrition(nutritions.get(0),bindingResult,modelMap),"addnutrition");
        Assert.assertEquals(modelMap.get("add"),true);
    }

    @Test
    public void createNutritionOk(){
        when(bindingResult.hasErrors()).thenReturn(false);
        doNothing().when(nutritionService).createNutrition(any(Nutrition.class));
        Assert.assertEquals(nutritionController.createNutrition(nutritions.get(0),bindingResult,modelMap),"redirect:/nutritions");
    }

    @Test
    public void updateNutrition(){
        Nutrition nutrition=nutritions.get(0);
        when(nutritionService.getNutritionById(anyLong())).thenReturn(nutrition);
        Assert.assertEquals(nutritionController.updateNutrition(nutritions.get(0).getNutritionId(),modelMap),"addnutrition");
        Assert.assertEquals(modelMap.get("nutrition"),nutrition);
    }

    @Test
    public void updateNutritionError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        Assert.assertEquals(nutritionController.updateNutrition(nutritions.get(0).getNutritionId(),nutritions.get(0),bindingResult,modelMap),"addnutrition");
    }

    @Test
    public void updateNutritionOk(){
        when(bindingResult.hasErrors()).thenReturn(false);
        doNothing().when(nutritionService).createNutrition(any(Nutrition.class));
        Assert.assertEquals(nutritionController.updateNutrition(nutritions.get(0).getNutritionId(),nutritions.get(0),bindingResult,modelMap),"redirect:/nutritions");
    }

    @Test
    public void deleteNutrition(){
        doNothing().when(nutritionService).deleteNutrition(anyLong());
        Assert.assertEquals(nutritionController.deleteNutrition(anyLong()),"redirect:/nutritions");
    }

    public List<Nutrition> getNutritions(){
        Nutrition nutrition=new Nutrition();
        nutrition.setNutritionName("TestNutrition");
        nutrition.setNutritionId(1);
        nutrition.setNutritionProteins(200);
        nutrition.setNutritionCarbs(200);
        nutrition.setNutritionFat(200);
        nutrition.setNutritionCalories(2000);
        nutritions.add(nutrition);
        return nutritions;
    }
}
