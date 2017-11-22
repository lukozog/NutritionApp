package ozog.service;

import ozog.dao.NutritionDao;
import ozog.model.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NutritionService {

    @Autowired
    private NutritionDao nutritionDao;

    public void createNutrition(Nutrition nutrition) {
        nutritionDao.createNutrition(calculateCalories(nutrition));
    }

    public void updateNutrition(Nutrition nutrition) {
        nutritionDao.updateNutrition(calculateCalories(nutrition));
    }

    public void deleteNutrition(long nutritionId) {
        nutritionDao.deleteNutrition(nutritionId);
    }

    public Nutrition getNutritionById(long nutritionId) {
        return nutritionDao.getNutritionById(nutritionId);
    }

    public List getAllNutritions() {
        return nutritionDao.getAllNutritions();
    }

    protected Nutrition calculateCalories(Nutrition nutrition) {
        double nutritionCalories = nutrition.getNutritionFat() * 9 + (nutrition.getNutritionProteins() + nutrition.getNutritionCarbs()) * 4;
        nutrition.setNutritionCalories(nutritionCalories);
        return nutrition;
    }
}
