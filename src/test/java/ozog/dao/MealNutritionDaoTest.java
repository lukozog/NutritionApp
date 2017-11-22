package ozog.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;
import ozog.model.MealNutrition;

import java.util.ArrayList;
import java.util.List;

public class MealNutritionDaoTest extends EntityDaoImplTest{

    @Autowired
    MealNutritionDao mealNutritionDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet[] datasets = new IDataSet[] {
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("MealNutrition.xml"))
        };
        return new CompositeDataSet(datasets);
    }

    @Test
    public void createMealNutrition(){
        mealNutritionDao.createMealNutrition(getMealNutrition());
        Assert.assertEquals(mealNutritionDao.getMealNutritions().size(),2);
    }

    @Test
    public void deleteMealNutrition(){
        mealNutritionDao.deleteMealNutrition(1);
        Assert.assertEquals(mealNutritionDao.getMealNutritions().size(),0);
    }

    @Test
    public void getMealNutritions(){
        Assert.assertNotNull(mealNutritionDao.getMealNutritionById(1));
    }

    @Test
    public void getMealNutritionById(){
        List<MealNutrition>mealNutritions=new ArrayList<>();
        Assert.assertEquals(mealNutritionDao.getMealNutritions(1),mealNutritions);
    }


    public MealNutrition getMealNutrition(){
        MealNutrition mealNutrition=new MealNutrition();
        mealNutrition.setMealNutritionId(1);
        mealNutrition.setMealNutritionValue(10);
        mealNutrition.setMealNutritionCalories(0);
        mealNutrition.setMealNutritionProteins(0);
        mealNutrition.setMealNutritionCarbs(0);
        mealNutrition.setMealNutritionFat(0);
        mealNutrition.setMealNutritionName("testProduct");
        return mealNutrition;
    }
}
