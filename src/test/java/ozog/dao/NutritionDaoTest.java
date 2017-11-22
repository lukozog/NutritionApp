package ozog.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import ozog.model.Nutrition;

public class NutritionDaoTest extends EntityDaoImplTest {

    @Autowired
    NutritionDao nutritionDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet[] datasets = new IDataSet[]{
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Nutrition.xml"))
        };
        return new CompositeDataSet(datasets);
    }

    @Test
    public void createNutrition() {
        nutritionDao.createNutrition(getNutrition());
        Assert.assertEquals(nutritionDao.getAllNutritions().size(), 2);
    }

    @Test
    public void updateNutrition() {
        nutritionDao.updateNutrition(getNutrition());
        Assert.assertEquals(nutritionDao.getNutritionById(1).getNutritionName(), "TestNutrition");
    }

    @Test
    public void deleteNutrition() {
        nutritionDao.deleteNutrition(1);
        Assert.assertEquals(nutritionDao.getAllNutritions().size(), 0);
    }

    @Test
    public void getNutritionById() {
        Assert.assertNotNull(nutritionDao.getNutritionById(1));
    }

    @Test
    public void getAllNutritions() {
        Assert.assertEquals(nutritionDao.getAllNutritions().size(), 1);
    }

    public Nutrition getNutrition() {
        Nutrition nutrition = new Nutrition();
        nutrition.setNutritionName("TestNutrition");
        nutrition.setNutritionId(1);
        nutrition.setNutritionProteins(100);
        nutrition.setNutritionCarbs(100);
        nutrition.setNutritionFat(100);
        nutrition.setNutritionCalories(1500);
        return nutrition;
    }
}
