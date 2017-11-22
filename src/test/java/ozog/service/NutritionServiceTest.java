package ozog.service;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ozog.dao.NutritionDao;
import ozog.model.Nutrition;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class NutritionServiceTest {

    @Mock
    NutritionDao nutritionDao;

    @InjectMocks
    NutritionService nutritionService;

    @Spy
    List<Nutrition> nutritions=new ArrayList<>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        nutritions=getNutritions();
    }

    @Test
    public void createNutrition(){
        Nutrition nutrition=nutritions.get(0);
        nutritionService.calculateCalories(nutrition);
        doNothing().when(nutritionDao).createNutrition(nutrition);
        nutritionService.createNutrition(nutrition);
        verify(nutritionDao,atLeastOnce()).createNutrition(nutrition);
    }

    @Test
    public void updateNutrition(){
        Nutrition nutrition=nutritions.get(0);
        nutritionService.calculateCalories(nutrition);
        doNothing().when(nutritionDao).updateNutrition(nutrition);
        nutritionService.updateNutrition(nutrition);
        verify(nutritionDao,atLeastOnce()).updateNutrition(nutrition);
    }

    @Test
    public void deleteNutrition(){
        doNothing().when(nutritionDao).deleteNutrition(anyLong());
        nutritionService.deleteNutrition(anyLong());
        verify(nutritionDao,atLeastOnce()).deleteNutrition(anyLong());
    }

    @Test
    public void getAllNutritions(){
        when(nutritionDao.getAllNutritions()).thenReturn(nutritions);
        Assert.assertEquals(nutritionService.getAllNutritions(),nutritions);
    }

    @Test
    public void getNutritionById(){
        Nutrition nutrition=getNutritions().get(0);
        when(nutritionDao.getNutritionById(nutrition.getNutritionId())).thenReturn(nutrition);
        Assert.assertEquals(nutritionService.getNutritionById(nutrition.getNutritionId()).getNutritionName(),"TestNutrition");
    }

    @Test
    public void calculateCalories(){
        Nutrition nutrition=nutritions.get(0);
        Assert.assertEquals(nutritionService.calculateCalories(nutrition).getNutritionCalories(),3400,0.0);
    }


    public List<Nutrition> getNutritions(){
        Nutrition nutrition=new Nutrition();
        nutrition.setNutritionName("TestNutrition");
        nutrition.setNutritionId(1);
        nutrition.setNutritionProteins(200);
        nutrition.setNutritionCarbs(200);
        nutrition.setNutritionFat(200);
        nutritions.add(nutrition);
        return nutritions;
    }

}
